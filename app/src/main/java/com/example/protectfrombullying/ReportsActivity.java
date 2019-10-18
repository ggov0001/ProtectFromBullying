package com.example.protectfrombullying;

import android.app.AlertDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.example.protectfrombullying.R.id.edittext_email;

public class ReportsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button cyberBullyReport;
    private Button bullyIdentitiesReport;
    private Button emailReport;
    private Button recordings;
    private Spinner kidsSpinner;
    private TextView totalCyberBullyTexts;

    //getting and populating spinner
    List<Kids> kidsList;
    List<Kids> allRecords;

    //For sending the name and id of kid selected from the spinner to the report screen
    String kidName, kidId;

    //To save emailid for the future
    private Parent parentInfo ;
    private List<Parent> parentList;

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
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);


        cyberBullyReport = (Button) findViewById(R.id.button_iconCyberbullyTexts);
        bullyIdentitiesReport = (Button) findViewById(R.id.button_bullyIdentities);
        emailReport = (Button) findViewById(R.id.button_email);
        recordings = (Button) findViewById(R.id.button_recordings);

        kidsSpinner = (Spinner) findViewById(R.id.spinner_kids);
        kidsSpinner.setOnItemSelectedListener(this);

        totalCyberBullyTexts = (TextView) findViewById(R.id.textview_cyberbullytextsreceived);
        totalCyberBullyTexts.setSingleLine(false);

        parentInfo = new Parent();
        parentList = new ArrayList<Parent>();

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

        emailReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog alerDialogForEmail;
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ReportsActivity.this)
                        //set icon
                        .setIcon(android.R.drawable.ic_dialog_email)
                        //set title
                        .setTitle("EMAIL ID")
                        //set message
                        .setMessage("Please create a new email ID or use the previously used email ID and click 'Confirm' to send the email..");

                final LayoutInflater inflater = LayoutInflater.from(ReportsActivity.this);
                View viewForDialogBox = inflater.inflate(R.layout.parent_dialoginfo, null);
                final EditText emailIdSet = (EditText) viewForDialogBox.findViewById(edittext_email);

                if(parentInfo != null)
                    emailIdSet.setText(parentInfo.getEmailId());
                else
                    emailIdSet.setText("");
                alertDialog.setView(viewForDialogBox);

           //     alertDialog.setView(input);

                //set positive button
                alertDialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        //set negative button
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                alerDialogForEmail = alertDialog.create();

                alerDialogForEmail.show();

                //Listener for 'Confirm'
                alerDialogForEmail.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!(emailIdSet.getText().toString().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")))
                        {
                            emailIdSet.setError("Please enter a valid email address.");
                            emailIdSet.requestFocus();
                        }
                        else {
                            alerDialogForEmail.cancel();
                            updateEmail(emailIdSet);
                        }
                    }
                });


            }
        });

        recordings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportsActivity.this, KidsRecordings.class);
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

            GetEmailIDOfParent getEmailIDOfParent = new GetEmailIDOfParent();
            getEmailIDOfParent.execute();
        }
    }

    //Get email id
    private class GetEmailIDOfParent extends AsyncTask<String, Void, List<Parent>> {
        @Override
        protected List<Parent> doInBackground(String... strings) {
            parentList = database.parentDAO().getAll();
          return parentList;
        }
        @Override
        protected void onPostExecute(List<Parent> parentList)
        {
            if(parentList.size() != 0)
            {
                parentInfo = parentList.get(0);
            }

        }
    }

    private void updateEmail(EditText emailIdEditText)
    {
//        if(!(emailIdEditText.getText().toString().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"))) {
//            emailIdEditText.setError("Please enter a valid email address.");
//            emailIdEditText.requestFocus();
//        }
//        else
//        {
            if(parentList.size() == 0)
            {
                AddEmailIdtoDatabase addEmailIdtoDatabase = new AddEmailIdtoDatabase();
                addEmailIdtoDatabase.execute(emailIdEditText.getText().toString());
            }
            else
            {
                UpdateEmailInDatabase updateEmailInDatabase = new UpdateEmailInDatabase();
                updateEmailInDatabase.execute(emailIdEditText.getText().toString());
            }
        //}
    }

    //Add email id
    private class AddEmailIdtoDatabase extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... emailIDEntered) {
            parentInfo.setEmailId(emailIDEntered[0]);
            database.parentDAO().insert(parentInfo);

            return emailIDEntered[0];
        }

        @Override
        protected void onPostExecute(String emailId) {
            SendEmailAsync sendEmailAsync = new SendEmailAsync();
            sendEmailAsync.execute(emailId);
        }
    }

    //Update email id
    private class UpdateEmailInDatabase extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... emailIDEntered) {
            if(!emailIDEntered[0].equals(parentInfo))
            {
                parentInfo.setEmailId(emailIDEntered[0]);
                database.parentDAO().updateParentEmailId(parentInfo);
            }
            return emailIDEntered[0];
        }
        @Override
        protected void onPostExecute(String emailId) {
            SendEmailAsync sendEmailAsync = new SendEmailAsync();
            sendEmailAsync.execute(emailId);
        }
    }

    //Send to email
    private class SendEmailAsync extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... emailIDEntered) {
            sendEmail(emailIDEntered[0]);
            return null;
        }
        @Override
        protected void onPostExecute(String emailId) {

            Toast.makeText(ReportsActivity.this,"Email Sent!",Toast.LENGTH_LONG).show();
        }
    }

    //Send email
    private void sendEmail(String emailId)
    {
        //kidsId = "5Cvk5aotND";
        String baseUrl = "https://nobully-247.appspot.com/email?kidID=";

        URL url = null;
        HttpURLConnection conn = null;

        String parameters = "\"" + kidId + "\"" + "&email=" + emailId + "&kidNAME=" + kidName;

        //Making HTTP Request
        try{
            url = new URL(baseUrl + parameters);
            conn = (HttpURLConnection) url.openConnection(); //opening the connection

            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);

            conn.setRequestMethod("GET"); //Connection method is set to Get

            conn.setRequestProperty("Content-Type","application/json");
            conn.setRequestProperty("Accept","application/json"); //adding http headers to set response to json

            Log.i("error", new Integer(conn.getResponseCode()).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            conn.disconnect();
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
            return getTheKidReport();
        }

        @Override
        protected void onPostExecute (String report){
            //Set the total to the circle textveiw
            int[] numberOfTexts = {0,0,0,0};
            int totalTexts = 0;
            try {
                JSONObject jsonObject = new JSONObject(report);

                //Messages - Different phones have different package names for message.
                if(jsonObject.has("Text Message"))
                {
                    numberOfTexts[0] = jsonObject.getInt("Text Message");
                }

//                if(jsonObject.has("com.samsung.android.messaging"))
//                {
//                    numberOfTexts[0] = jsonObject.getInt("com.samsung.android.messaging");
//                }
//
//                if(jsonObject.has("com.android.mms"))
//                {
//                    numberOfTexts[0] = jsonObject.getInt("com.android.mms");
//                }

                //Instagram
                if(jsonObject.has("Instagram"))
                {
                    numberOfTexts[1] = jsonObject.getInt("Instagram");
                }

                //Facebook messenger
                if(jsonObject.has("Facebook Messenger"))
                {
                    numberOfTexts[2] = jsonObject.getInt("Facebook Messenger");
                }

                //Whatsapp
                if(jsonObject.has("Whatsapp"))
                {
                    numberOfTexts[3] = jsonObject.getInt("Whatsapp");
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


    private String getTheKidReport()
    {
        //kidsId = "5Cvk5aotND";
        String baseUrl = "https://nobully-247.appspot.com/api?kidID=";

        URL url = null;
        HttpURLConnection conn = null;
        String result = "";

         String parameters = "\"" + kidId + "\"" + "&getID=0";

        //Making HTTP Request
        try{
            url = new URL(baseUrl + parameters);
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
