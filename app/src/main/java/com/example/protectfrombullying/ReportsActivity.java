package com.example.protectfrombullying;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReportsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button cyberBullyReport;
    private Button bullyIdentitiesReport;
    private Spinner kidsSpinner;
    private TextView totalCyberBullyTexts;

    //getting and populating spinner
    List<Kids> kidsList;
    List<Kids> allRecords;

    //For sending the name and id of kid selected from the spinner to the report screen
    String kidName, kidId;

    private KidsDatabase database;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(ReportsActivity.this, HomeParentActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                case R.id.navigation_reports:
                    Intent dashboardIntent = new Intent(ReportsActivity.this, ReportsActivity.class);
                    startActivity(dashboardIntent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                case R.id.navigation_yourkids:
                    Intent yourKidsIntent = new Intent(ReportsActivity.this, YourKidsActivity.class);
                    startActivity(yourKidsIntent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                case R.id.navigation_information:
                    Intent infromationIntent = new Intent(ReportsActivity.this, WaysToTackleActivity.class);
                    startActivity(infromationIntent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        cyberBullyReport = (Button) findViewById(R.id.button_iconCyberbullyTexts);
        bullyIdentitiesReport = (Button) findViewById(R.id.button_bullyIdentities);

        kidsSpinner = (Spinner) findViewById(R.id.spinner_kids);
        kidsSpinner.setOnItemSelectedListener(this);

        totalCyberBullyTexts = (TextView) findViewById(R.id.textview_cyberbullytextsreceived);
        totalCyberBullyTexts.setSingleLine(false);

        database = Room.databaseBuilder(getApplicationContext(), KidsDatabase.class, "KidsDatabase").fallbackToDestructiveMigration().build();

        kidsList = new ArrayList<>();


        cyberBullyReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportsActivity.this, CyberBullyTextsActivity.class);
                intent.putExtra("kidId", kidId);
                intent.putExtra("kidName", kidName);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        bullyIdentitiesReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportsActivity.this, BullyIdentitiesActivity.class);
                intent.putExtra("kidId", kidId);
                intent.putExtra("kidName", kidName);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        //Get all the kdis to add in the spinner
        ViewKids viewKids = new ViewKids();
        viewKids.execute(this);
    }

    //Display all the steps
    private class ViewKids extends AsyncTask<Context, Void, Context> {

        @Override
        protected Context doInBackground(Context... context) {
            allRecords  = database.kidsDAO().getAll();
            return context[0];
        }

        @Override
        protected void onPostExecute(Context context) {
            for(int i = 0; i < allRecords.size(); i++)
            {
                String kidName = allRecords.get(i).kidName;
                String kidId = allRecords.get(i).kidId;
                kidsList.add(new Kids(kidId, kidName));
            }

            //set the values in the spinner
            String[] kids = new String[kidsList.size()];

            for(int iterator = 0; iterator < kids.length; iterator++)
            {
                kids[iterator] = kidsList.get(iterator).getKidName();
            }

            ArrayAdapter<String> adapter;
            adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, kids);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            kidsSpinner.setAdapter(adapter);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //For sending it along with intent
        kidName = kidsList.get(position).getKidName();
        kidId = kidsList.get(position).getKidId();

        //Get the total number of texts
        GetCyberTextsDataAsyncTask getCyberTextsDataAsyncTask = new GetCyberTextsDataAsyncTask();
        getCyberTextsDataAsyncTask.execute();
    }

    //GET method to get the number of texts from each social media
    private class GetCyberTextsDataAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            return getTheKidReport(kidId);
        }

        @Override
        protected void onPostExecute (String report){
            //Set the total to the circle textveiw
            int[] numberOfTexts = {0,0,0,0};
            int totalTexts = 0;
            try {
                JSONObject jsonObject = new JSONObject(report);

                //Messages - Different phones have different package names for message.
                if(jsonObject.has("com.google.android.apps.messaging"))
                {
                    numberOfTexts[0] = jsonObject.getInt("com.google.android.apps.messaging");
                }

                if(jsonObject.has("com.samsung.android.messaging"))
                {
                    numberOfTexts[0] = jsonObject.getInt("com.samsung.android.messaging");
                }

                if(jsonObject.has("com.android.mms"))
                {
                    numberOfTexts[0] = jsonObject.getInt("com.android.mms");
                }

                //Instagram
                if(jsonObject.has("com.instagram.android"))
                {
                    numberOfTexts[1] = jsonObject.getInt("com.instagram.android");
                }

                //Facebook messenger
                if(jsonObject.has("com.facebook.orca"))
                {
                    numberOfTexts[2] = jsonObject.getInt("com.facebook.orca");
                }

                //Whatsapp
                if(jsonObject.has("com.whatsapp"))
                {
                    numberOfTexts[3] = jsonObject.getInt("com.whatsapp");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            for(int i = 0;i < 4; i++)
            {
                totalTexts += numberOfTexts[i];
            }

            totalCyberBullyTexts.setText("Cyberbully Texts \n" + "Received: \n" + totalTexts);
        }
    }


    private String getTheKidReport(String kidsId)
    {
        //kidsId = "5Cvk5aotND";
        String baseUrl = "https://nobully-247.appspot.com/api?kidID=";

        URL url = null;
        HttpURLConnection conn = null;
        String result = "";

        kidsId = "\"" + kidsId + "\"" + "&getID=0";

        //Making HTTP Request
        try{
            url = new URL(baseUrl + kidsId);
            conn = (HttpURLConnection) url.openConnection(); //opening the connection

            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);

            conn.setRequestMethod("GET"); //Connection method is set to Get

            conn.setRequestProperty("Content-Type","application/json");
            conn.setRequestProperty("Accept","application/json"); //adding http headers to set response to json

            Scanner inputStream = new Scanner(conn.getInputStream());

            while(inputStream.hasNextLine())
            {
                result += inputStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            conn.disconnect();
        }
        return result;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
