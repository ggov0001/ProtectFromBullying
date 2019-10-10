package com.example.protectfrombullying;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KidsRecordings extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecordingsAdapter recordingsAdapter;

    private List<String> recordingNames;
    private List<String> recordingUri;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(KidsRecordings.this, HomeParentActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                case R.id.navigation_reports:
                    Intent dashboardIntent = new Intent(KidsRecordings.this, ReportsActivity.class);
                    startActivity(dashboardIntent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                case R.id.navigation_yourkids:
                    Intent yourKidsIntent = new Intent(KidsRecordings.this, YourKidsActivity.class);
                    startActivity(yourKidsIntent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                case R.id.navigation_information:
                    Intent infromationIntent = new Intent(KidsRecordings.this, WaysToTackleActivity.class);
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
        setContentView(R.layout.activity_kids_recordings);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        recordingNames = new ArrayList<>();
        recordingUri = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_recordings);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        GetRecordingsAysnc getRecordingsAsync = new GetRecordingsAysnc();
        getRecordingsAsync.execute();
    }

    //Get all recordings async
    private class GetRecordingsAysnc extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... context) {

            return getRecordingsFromServer(getIntent().getStringExtra("kidId"));
        }

        @Override
        protected void onPostExecute(String responseFromServer) {
            try {
                JSONArray jArray = new JSONArray(responseFromServer);

                for(int i = 0; i < jArray.length(); i++)
                {
                    JSONObject entriesInJson = jArray.getJSONObject(i);
                    recordingNames.add(entriesInJson.getString("filename"));
                    recordingUri.add(entriesInJson.getString("uri"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //set the values in the recycler view
            RecordingsAdapter recordingsAdapter = new RecordingsAdapter(KidsRecordings.this, recordingNames, recordingUri, getIntent().getStringExtra("kidName"));
            recyclerView.setAdapter(recordingsAdapter);
        }
    }

    //Get all recordings
    private String getRecordingsFromServer(String kidId)
    {
        //kidsId = "5Cvk5aotND";
        String baseUrl = "https://nobully-247.appspot.com/firebase?kidID=";

        URL url = null;
        HttpURLConnection conn = null;
        String result = "";

        //Making HTTP Request
        try{
            url = new URL(baseUrl +  "\'" + kidId + "\'");
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

            Log.i("error", new Integer(conn.getResponseCode()).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            conn.disconnect();
        }
        return result;
    }

}
