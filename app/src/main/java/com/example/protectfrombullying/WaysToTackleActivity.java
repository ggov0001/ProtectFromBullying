package com.example.protectfrombullying;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class WaysToTackleActivity extends AppCompatActivity {

    private Button generalInformation;
    private Button legalHelp;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(WaysToTackleActivity.this, HomeActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_dashboard:
                    Intent dashboardIntent = new Intent(WaysToTackleActivity.this, DummyActivity.class);
                    startActivity(dashboardIntent);
                    return true;
                case R.id.navigation_notifications:
                    Intent notificationIntent = new Intent(WaysToTackleActivity.this, DummyActivity.class);
                    startActivity(notificationIntent);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ways_to_tackle);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        generalInformation = (Button) findViewById(R.id.button_generalInformation);
        legalHelp = (Button) findViewById(R.id.button_legalHelp);


        generalInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WaysToTackleActivity.this, GeneralInformationActivity.class);
                startActivity(intent);
            }
        });

        legalHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WaysToTackleActivity.this, LegalHelpActivity.class);
                startActivity(intent);
            }
        });


    }
}
