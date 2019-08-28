package com.example.protectfrombullying;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    //Declare all buttons in the home screen
    private Button quizButton;
    private Button waysToTackleButton;
    private Button talkToKidsButton;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_dashboard:
                    Intent dashboardIntent = new Intent(HomeActivity.this, DummyActivity.class);
                    startActivity(dashboardIntent);
                    return true;
                case R.id.navigation_notifications:
                    Intent notificationIntent = new Intent(HomeActivity.this, DummyActivity.class);
                    startActivity(notificationIntent);
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        quizButton = (Button) findViewById(R.id.button_iconQuiz);
        waysToTackleButton = (Button) findViewById(R.id.button_iconTackle);
        talkToKidsButton = (Button) findViewById(R.id.button_iconTalkToKids);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ParentQuizActivity.class);
                startActivity(intent);
            }
        });

        waysToTackleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, WaysToTackleActivity.class);
                startActivity(intent);
            }
        });

        talkToKidsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ParentTalkActivity.class);
                startActivity(intent);
            }
        });


    }
}
