package com.example.protectfrombullying;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class YourKidsActivity extends AppCompatActivity {

    private KidsDatabase database;

    private RecyclerView recyclerView;
    private KidsAdapter kidsAdapter;

    List<Kids> kidsList;
    List<Kids> allRecords;




    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(YourKidsActivity.this, HomeActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_dashboard:
                    Intent dashboardIntent = new Intent(YourKidsActivity.this, DummyActivity.class);
                    startActivity(dashboardIntent);
                    return true;
                case R.id.navigation_notifications:
                    Intent notificationIntent = new Intent(YourKidsActivity.this, DummyActivity.class);
                    startActivity(notificationIntent);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_kids);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        database = Room.databaseBuilder(getApplicationContext(), KidsDatabase.class, "KidsDatabase").fallbackToDestructiveMigration().build();



        kidsList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ViewKids viewKids = new ViewKids();
        viewKids.execute(this);

//        kidsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String name = kidName.getText().toString();
//                GetInfoFromNameAsync getInfoFromNameAsync = new GetInfoFromNameAsync();
//                getInfoFromNameAsync.execute(name);
//            }
//        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.addthekids, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.addkidsicon)
        {
            Intent intent = new Intent(YourKidsActivity.this, AddKidScreenActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
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
                int id = allRecords.get(i).id;
                kidsList.add(new Kids(id, kidId, kidName));
            }
            kidsAdapter = new KidsAdapter(context, kidsList);
            recyclerView.setAdapter(kidsAdapter);
        }
    }

    public void dialogBoxKidInfo(String kidName, String kidId)
    {
        Button okDialogBoxButton = (Button) findViewById(R.id.button_okqrcodedialog);
        ImageView qrCodeDialog = (ImageView) findViewById(R.id.imageview_qrcodedialog);
        TextView kidNameDialog = (TextView)findViewById(R.id.textView_kidnamedialog);

        final AlertDialog.Builder alert = new AlertDialog.Builder(YourKidsActivity.this);
        View viewForDialogBox = getLayoutInflater().inflate(R.layout.kids_dialoginfo, null);

        String contentsOfQRCode = kidName + "/" + kidId;
        new QRCodeImageDownloader(qrCodeDialog).execute("https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=" + contentsOfQRCode);
        kidNameDialog.setText(kidName);

        alert.setView(viewForDialogBox);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        okDialogBoxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    //Fetch the entire information from name
    private class GetInfoFromNameAsync extends  AsyncTask<String, Void, Kids> {
        @Override
        protected Kids doInBackground(String... params) {

            Kids kids = new Kids();
            kids = database.kidsDAO().findByName(params[0]);

            return kids;
        }

        @Override
        protected void onPostExecute(Kids kids) {
            //Kids upon select and Ok button in the dialog box
            Button okDialogBoxButton = (Button) findViewById(R.id.button_okqrcodedialog);;
            ImageView qrCodeDialog = (ImageView) findViewById(R.id.imageview_qrcode);;
            TextView kidNameDialog = (TextView)findViewById(R.id.textView_kidnamedialog);;

            String contentsOfQRCode = kids.getKidName() + "/" + kids.getKidId();
            final AlertDialog.Builder alert = new AlertDialog.Builder(YourKidsActivity.this);
            View viewForDialogBox = getLayoutInflater().inflate(R.layout.kids_dialoginfo, null);

            new QRCodeImageDownloader(qrCodeDialog).execute("https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=" + contentsOfQRCode);
            kidNameDialog.setText(kids.getKidName());

            alert.setView(viewForDialogBox);
            final AlertDialog alertDialog = alert.create();
            alertDialog.setCanceledOnTouchOutside(false);

            okDialogBoxButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });

            alertDialog.show();
        }
    }

}
