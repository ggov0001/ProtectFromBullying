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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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
                kidsList.add(new Kids(kidId, kidName));
            }
            kidsAdapter = new KidsAdapter(context, kidsList);
            recyclerView.setAdapter(kidsAdapter);
        }
    }

}
