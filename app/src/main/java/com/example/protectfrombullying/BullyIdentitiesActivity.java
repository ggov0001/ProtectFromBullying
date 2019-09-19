package com.example.protectfrombullying;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BullyIdentitiesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    private BarChart barChart;
    private Spinner socialMediaPlatform;
    private TextView bullyIdentitiesHeading;

    private String reportFromServer;

    List<BullyIdentitiesPlatform> cyberBullyingOnFacebooklist;
    List<BullyIdentitiesPlatform> cyberBullyingOnWhatsapplist;
    List<BullyIdentitiesPlatform> cyberBullyingOnInstagramlist;
    List<BullyIdentitiesPlatform> cyberBullyingOnMessageslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bully_identities);

        //Initialize list
        cyberBullyingOnFacebooklist = new ArrayList<BullyIdentitiesPlatform>(5);
        cyberBullyingOnWhatsapplist = new ArrayList<BullyIdentitiesPlatform>(5);
        cyberBullyingOnInstagramlist = new ArrayList<BullyIdentitiesPlatform>(5);
        cyberBullyingOnMessageslist = new ArrayList<BullyIdentitiesPlatform>(5);

        //Heading set
        bullyIdentitiesHeading = (TextView) findViewById(R.id.textView_bullyIdentitiesHeading);
        bullyIdentitiesHeading.setText("Bully Identities (with number of texts sent) - who cyberbullied " + getIntent().getStringExtra("kidName") + " !");

        //Barchart
        barChart = (BarChart) findViewById(R.id.bargraph_BullyIdentitiestexts);
        barChart.setDescription(null);
        barChart.setPinchZoom(false);
        barChart.setScaleEnabled(false);
        barChart.setDrawBarShadow(false);
        barChart.setDrawGridBackground(false);

        //Spinner
        socialMediaPlatform = (Spinner) findViewById(R.id.spinner_platform);
        socialMediaPlatform.setOnItemSelectedListener(this);


        //GET
        GetCyberTextsDataAsyncTask getCyberTextsDataAsyncTask = new GetCyberTextsDataAsyncTask();
        getCyberTextsDataAsyncTask.execute();


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch(position)
        {
            case 0: //Whatsapp
                    setBarChart(cyberBullyingOnWhatsapplist, "Whatsapp");
                    break;
            case 1:  //Messages
                    setBarChart(cyberBullyingOnMessageslist, "Messages");
                    break;
            case 2: //Instagram
                    setBarChart(cyberBullyingOnInstagramlist, "Instagram");
                    break;
            case 3: //Facebook Messenger
                    setBarChart(cyberBullyingOnFacebooklist, "Facebook Messenger");
                    break;

            default: break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //GET method to get the number of texts from each social media
    private class GetCyberTextsDataAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            reportFromServer = getTheKidReport(getIntent().getStringExtra("kidId"));

            return reportFromServer;
        }

        @Override
        protected void onPostExecute (String reportFromServer){

            try {
                JSONArray jArray = new JSONArray(reportFromServer);

                for(int i = 0; i < jArray.length(); i++)
                {
                    JSONObject entriesInJson = jArray.getJSONObject(i);

                    //Platform: Whatsapp
                    if(entriesInJson.getString("platform").equals("com.whatsapp"))
                    {
                        BullyIdentitiesPlatform bullyIdentitiesPlatform = new BullyIdentitiesPlatform();
                        bullyIdentitiesPlatform.setPlatform("Whatsapp");
                        bullyIdentitiesPlatform.setSenderIdentity(entriesInJson.getString("sender"));
                        bullyIdentitiesPlatform.setNumberOfMessagesSent(entriesInJson.getInt("num_msg"));

                        //Save and Display top 5
                        if(cyberBullyingOnWhatsapplist.size() <= 5)
                            cyberBullyingOnWhatsapplist.add(bullyIdentitiesPlatform);
                    }

                    //Platform message
                    if(entriesInJson.getString("platform").equals("com.google.android.apps.messaging") ||
                            entriesInJson.getString("platform").equals("com.samsung.android.messaging")||
                            entriesInJson.getString("platform").equals("com.android.mms"))
                    {
                        BullyIdentitiesPlatform bullyIdentitiesPlatform = new BullyIdentitiesPlatform();
                        bullyIdentitiesPlatform.setPlatform("Messages");
                        bullyIdentitiesPlatform.setSenderIdentity(entriesInJson.getString("sender"));
                        bullyIdentitiesPlatform.setNumberOfMessagesSent(entriesInJson.getInt("num_msg"));

                        //Save and Display top 5
                        if(cyberBullyingOnMessageslist.size() <= 5)
                            cyberBullyingOnMessageslist.add(bullyIdentitiesPlatform);
                    }

                    //Platform: Instagram
                    if(entriesInJson.getString("platform").equals("com.instagram.android"))
                    {
                        BullyIdentitiesPlatform bullyIdentitiesPlatform = new BullyIdentitiesPlatform();
                        bullyIdentitiesPlatform.setPlatform("Instagram");
                        bullyIdentitiesPlatform.setSenderIdentity(entriesInJson.getString("sender"));
                        bullyIdentitiesPlatform.setNumberOfMessagesSent(entriesInJson.getInt("num_msg"));

                        //Save and Display top 5
                        if(cyberBullyingOnInstagramlist.size() <= 5)
                            cyberBullyingOnInstagramlist.add(bullyIdentitiesPlatform);
                    }

                    //Platform: Facebook Messenger
                    if(entriesInJson.getString("platform").equals("com.facebook.orca"))
                    {
                        BullyIdentitiesPlatform bullyIdentitiesPlatform = new BullyIdentitiesPlatform();
                        bullyIdentitiesPlatform.setPlatform("Facebook Messenger");
                        bullyIdentitiesPlatform.setSenderIdentity(entriesInJson.getString("sender"));
                        bullyIdentitiesPlatform.setNumberOfMessagesSent(entriesInJson.getInt("num_msg"));

                        //Save and Display top 5
                        if(cyberBullyingOnFacebooklist.size() <= 5)
                            cyberBullyingOnFacebooklist.add(bullyIdentitiesPlatform);
                    }

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Spinner populating the social media platforms
            String[] socialMedia = new String[] {"Whatsapp", "Messages", "Instagram", "Facebook Messenger"};

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(BullyIdentitiesActivity.this,
                    android.R.layout.simple_spinner_item,socialMedia);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            socialMediaPlatform.setAdapter(adapter);

        }
    }

    private String getTheKidReport(String kidsId)
    {

        //kidsId = "5ULJOuEGM0";

        URL url = null;
        HttpURLConnection conn = null;
        String result = "";

        //Making HTTP Request
        try{
            url = new URL("https://nobully-247.appspot.com/api?kidID=" + "\"" + kidsId + "\"" + "&getID=1");
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

    public void setBarChart(List<BullyIdentitiesPlatform> platFormList, String platform)
    {
        //Senders List
        List<String> senderList = new ArrayList<String>();

        //BAR CHART IMPLEMENTATION
        ArrayList<BarEntry> barEntries = new ArrayList<>();


        for(int iterator = 0; iterator < platFormList.size() ; iterator++)
        {
            barEntries.add(new BarEntry(iterator+1, platFormList.get(iterator).getNumberOfMessagesSent()));
            senderList.add(platFormList.get(iterator).getSenderIdentity());
        }

        BarDataSet barDataSet = new BarDataSet(barEntries,"");
        barDataSet.setColors(new int[]{Color.parseColor("#ff0000"), Color.parseColor("#f9530b"), Color.parseColor("#ff9005"), Color.parseColor("#fbd808"), Color.parseColor("#f3f781")});

        BarData theData = new BarData(barDataSet);
        barChart.setData(theData);
        //barChart.invalidate();

        barChart.getAxisLeft().setAxisMinimum(0);
        barChart.getAxisRight().setAxisMinimum(0);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        //barChart.setMaxVisibleValueCount(10);
        barChart.setExtraOffsets(30,30,30,40);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelRotationAngle(-45);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum(barEntries.size()+1);

        xAxis.setValueFormatter(new IndexAxisValueFormatter(senderList));
        barChart.getXAxis().setAxisMaximum(0 + barChart.getBarData().getGroupWidth(0.4f, 0.02f) * 5);

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.setTypeface(Typeface.DEFAULT);
        //leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setTextColor(Color.BLACK);
        leftAxis.setDrawGridLines(false);
        barChart.getAxisRight().setEnabled(false);

        barChart.invalidate();
    }

}
