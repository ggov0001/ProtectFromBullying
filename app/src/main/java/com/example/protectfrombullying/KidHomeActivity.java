package com.example.protectfrombullying;

import android.arch.persistence.room.Room;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class KidHomeActivity extends AppCompatActivity {

    //Declare all buttons in the home screen
    private Button qrcodeScannerButton;


    private KidsDatabase database;

    //Receiver
    Receiver receiver;

    //For The notification
    private String pack;
    private String title;
    private String text;
    private String ticker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_home);

        qrcodeScannerButton = (Button) findViewById(R.id.button_qrcode);
        //Initialize database
        database = Room.databaseBuilder(getApplicationContext(), KidsDatabase.class, "KidsDatabase").fallbackToDestructiveMigration().build();

        //Button on click listener
        qrcodeScannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KidHomeActivity.this, KidsQRScanActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        receiver = new Receiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.protectfrombullying");
        registerReceiver(receiver, intentFilter);
    }

    //Logic to see if the kid is added
   /* private class CheckIfKidIsCreated extends AsyncTask<String, Void, List<Kids> > {

        @Override
        protected List<Kids> doInBackground(String... strings) {
            return database.kidsDAO().getAll();
        }

        @Override
        protected void onPostExecute(List<Kids> kidsList) {
            if(kidsList.isEmpty())
            {
                Intent intent = new Intent(KidHomeActivity.this, KidsQRScanActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Already Added!" + pack, Toast.LENGTH_LONG).show();

            }
        }
    } */

    public class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            pack = intent.getStringExtra("package");
            title = intent.getStringExtra("title");
            text = intent.getStringExtra("text");
            ticker = intent.getStringExtra("ticker");

            Log.i("receive Package",pack);
            Log.i("receive Title",title);
            Log.i("receive Text",text);

            //Wrangle the text(message) from instagram. Received as "username: textabc def.." change it to "textabc def..."
            if(pack.equals("com.instagram.android")) {
                String split[] = text.split(": ");
                text = split[1];
            }

            Toast.makeText(getApplicationContext(), "Received from " + pack, Toast.LENGTH_LONG).show();

            if(pack.equals("com.google.android.apps.messaging") || pack.equals("com.samsung.android.messaging") ||
            pack.equals("com.android.mms") || pack.equals("com.instagram.android") || pack.equals("com.facebook.orca") ||
            pack.equals("com.whatsapp"))
            {
                GetKidInfoAsync getKidInfoAsync = new GetKidInfoAsync();
                getKidInfoAsync.execute();
            }

        }

        private class GetKidInfoAsync extends AsyncTask<String, Void, List<Kids> > {

            @Override
            protected List<Kids> doInBackground(String... strings) {
                return database.kidsDAO().getAll();
            }

            @Override
            protected void onPostExecute(List<Kids> kidInfo) {
                PostNotificationContentAsync postNotificationContentAsync = new PostNotificationContentAsync();
                postNotificationContentAsync.execute(kidInfo.get(0).getKidId());
            }
        }

        private class PostNotificationContentAsync extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... kidId) {

                postNotificationContent(kidId[0]);
                return kidId[0];
            }

            @Override
            protected void onPostExecute(String kidId) {
                Toast.makeText(getApplicationContext(), "Done Posting!", Toast.LENGTH_LONG).show();
            }

        }


        //Rest method to post the report
        public void postNotificationContent(String kidId) {

            URL url = null;
            HttpURLConnection conn = null;

            try {
                url = new URL("https://nobully-247.appspot.com/api");
                conn = (HttpURLConnection) url.openConnection(); //opening the connection
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);

                conn.setRequestMethod("POST"); //Connection method is set to POST

                conn.setDoOutput(true); //output to true
                conn.setRequestProperty("Content-Type", "application/json"); //headers


                //Current date and time
                Date date = Calendar.getInstance().getTime();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");


                //post data
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("kidID", kidId);
                jsonParam.put("platform", pack);
                jsonParam.put("sender", title);
                jsonParam.put("text", text);
                jsonParam.put("datetime", formatter.format(date));

                Log.i("JSON", jsonParam.toString());

                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                //os.writeBytes(URLEncoder.encode(String.valueOf(jsonParam), "UTF-8"));
                os.writeBytes(jsonParam.toString());
                os.flush();
                os.close();

                Log.i("error", new Integer(conn.getResponseCode()).toString());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conn.disconnect();
            }
        }

    }

}
