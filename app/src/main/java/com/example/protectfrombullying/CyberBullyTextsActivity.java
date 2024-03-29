package com.example.protectfrombullying;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class CyberBullyTextsActivity extends AppCompatActivity {

    private TextView cyberBullyScreenHeading;

//    private int[] value = {4, 5, 6, 7};
//    private String[] label = {"Text Message", "Instagram", "Facebook Messenger", "Whatsapp"} ;

    private BarChart barChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cyber_bully_texts);


        cyberBullyScreenHeading = (TextView) findViewById(R.id.textView_cyberbullytextheading);
        cyberBullyScreenHeading.setText("Cyberbully Messages received by " + getIntent().getStringExtra("kidName") + " from various social media platforms!");

        barChart = (BarChart) findViewById(R.id.bargraph_cyberbullytexts);
        barChart.setDescription(null);
        barChart.setPinchZoom(false);
        barChart.setScaleEnabled(false);
        barChart.setDrawBarShadow(false);
        barChart.setDrawGridBackground(false);

        getBarData();
    }

    private void getBarData()
    {
        GetCyberTextsDataAsyncTask getCyberTextsDataAsyncTask = new GetCyberTextsDataAsyncTask();
        getCyberTextsDataAsyncTask.execute();
    }

    //GET method to get the number of texts from each social media
    private class GetCyberTextsDataAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String kidId = getIntent().getStringExtra("kidId");
            return getTheKidReport(kidId);
        }

        @Override
        protected void onPostExecute (String report){
            //Parse JSON and get the data
            setBarData(report);
        }
    }

    private String getTheKidReport(String kidsId)
    {

     //   kidsId = "5Cvk5aotND";
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

    //Set the data on the pie chart
    private void setBarData(String report){

        int[] value = {0,0,0,0};
        String[] label = {"Text Message", "Instagram", "Facebook Messenger", "Whatsapp"} ;

        try {
            JSONObject jsonObject = new JSONObject(report);

            //Messages
            if(jsonObject.has("Text Message"))
            {
                value[0] = jsonObject.getInt("Text Message");
            }

//            if(jsonObject.has("com.samsung.android.messaging"))
//            {
//                value[0] = jsonObject.getInt("com.samsung.android.messaging");
//            }
//
//            if(jsonObject.has("com.android.mms"))
//            {
//                value[0] = jsonObject.getInt("com.android.mms");
//            }

            //Instagram
            if(jsonObject.has("Instagram"))
            {
                value[1] = jsonObject.getInt("Instagram");
            }

            //Facebook messenger
            if(jsonObject.has("Facebook Messenger"))
            {
                value[2] = jsonObject.getInt("Facebook Messenger");
            }

            //Whatsapp
            if(jsonObject.has("Whatsapp"))
            {
                value[3] = jsonObject.getInt("Whatsapp");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Put the values in the JSON to a Hashmap for sorting values and showing the map appropriately.
        Map<String, Integer> unsortMap = new HashMap<String, Integer>();
        unsortMap.put(label[0], value[0]);
        unsortMap.put(label[1], value[1]);
        unsortMap.put(label[2], value[2]);
        unsortMap.put(label[3], value[3]);

        //Sort
        List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Entry<String, Integer>>()
        {
            public int compare(Entry<String, Integer> o1,
                               Entry<String, Integer> o2)
            {

                    return o2.getValue().compareTo(o1.getValue());
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Entry<String, Integer> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        List<Integer> sortedValues = new ArrayList<Integer>(sortedMap.values());
        List<String> sortedKey = new ArrayList<String>(sortedMap.keySet());

        //BAR CHART IMPLEMENTATION
        ArrayList<BarEntry> barEntries = new ArrayList<>();

        //change it based on the values gotten from GET
        barEntries.add(new BarEntry(1, sortedValues.get(0)));
        barEntries.add(new BarEntry(2, sortedValues.get(1)));
        barEntries.add(new BarEntry(3, sortedValues.get(2)));
        barEntries.add(new BarEntry(4, sortedValues.get(3)));

        BarDataSet barDataSet = new BarDataSet(barEntries,"Social Media Platforms");
        barDataSet.setColors(ColorTemplate.PASTEL_COLORS);
        BarData theData = new BarData(barDataSet);
        theData.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, com.github.mikephil.charting.data.Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return String.valueOf((int)(value)) ;
            }
        });
        barChart.setData(theData);
        //barChart.invalidate();

        barChart.getAxisLeft().setAxisMinimum(0);
        barChart.getAxisRight().setAxisMinimum(0);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        //barChart.setMaxVisibleValueCount(10);
        barChart.setExtraOffsets(30,30,30,60);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(false);
        barChart.getAxisLeft().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return String.valueOf((int) Math.floor(value));
            }
        });
        barChart.getAxisRight().setEnabled(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelRotationAngle(-45);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum(barEntries.size()+1);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(sortedKey));
        barChart.getXAxis().setAxisMaximum(0 + barChart.getBarData().getGroupWidth(0.4f, 0.02f) * 5);

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.setTypeface(Typeface.DEFAULT);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setTextColor(Color.BLACK);
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setGranularityEnabled(true);
        leftAxis.setGranularity(1f);
        leftAxis.setAxisMaximum(leftAxis.getAxisMaximum()+1);

        barChart.invalidate();

    }

}
