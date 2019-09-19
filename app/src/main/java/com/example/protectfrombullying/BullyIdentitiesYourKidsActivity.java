package com.example.protectfrombullying;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class BullyIdentitiesYourKidsActivity extends AppCompatActivity {

    private KidsDatabase database;
    private RecyclerView recyclerView;

    List<Kids> kidsList;
    List<Kids> allRecords;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(BullyIdentitiesYourKidsActivity.this, HomeParentActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                case R.id.navigation_reports:
                    Intent dashboardIntent = new Intent(BullyIdentitiesYourKidsActivity.this, ReportsActivity.class);
                    startActivity(dashboardIntent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                case R.id.navigation_settings:
                    Intent notificationIntent = new Intent(BullyIdentitiesYourKidsActivity.this, SettingsActivity.class);
                    startActivity(notificationIntent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bully_identities_your_kids);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        database = Room.databaseBuilder(getApplicationContext(), KidsDatabase.class, "KidsDatabase").fallbackToDestructiveMigration().build();

        kidsList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewBullyIdentities);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
            //set the values in the recycler view
            BullyIdentitiesKidsAdapter bullyIdentitiesKidsAdapter = new BullyIdentitiesKidsAdapter(context, kidsList);
            recyclerView.setAdapter(bullyIdentitiesKidsAdapter);
        }
    }

}
