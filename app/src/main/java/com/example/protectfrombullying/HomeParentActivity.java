package com.example.protectfrombullying;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.vistrav.ask.Ask;

import smartdevelop.ir.eram.showcaseviewlib.GuideView;
import smartdevelop.ir.eram.showcaseviewlib.config.DismissType;
import smartdevelop.ir.eram.showcaseviewlib.config.Gravity;
import smartdevelop.ir.eram.showcaseviewlib.listener.GuideListener;

public class HomeParentActivity extends AppCompatActivity {

    //Declare all buttons in the home screen
    private Button quizButton;
    private Button waysToTackleButton;
    private Button talkToKidsButton;
    private Button yourKids;

    //For Showcase view on bottom navigation
    private View bottomNavigationViewYourKids;
    private View bottomNavigationViewReports;
    private View bottomNavigationViewInformation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    Intent intent = new Intent(HomeParentActivity.this, HomeParentActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                case R.id.navigation_reports:
                    Intent dashboardIntent = new Intent(HomeParentActivity.this, ReportsActivity.class);
                    startActivity(dashboardIntent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                case R.id.navigation_yourkids:
                    Intent yourKidsIntent = new Intent(HomeParentActivity.this, YourKidsActivity.class);
                    startActivity(yourKidsIntent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                case R.id.navigation_information:
                    Intent infromationIntent = new Intent(HomeParentActivity.this, WaysToTackleActivity.class);
                    startActivity(infromationIntent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
            }
            return false;
        }
    };

    //Back
    @SuppressLint("NewApi")
    @Override
    public void onBackPressed() {
        finishAffinity();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_parent);
        //Initialize
        quizButton = (Button) findViewById(R.id.button_iconQuiz);
        waysToTackleButton = (Button) findViewById(R.id.button_iconTackle);
        talkToKidsButton = (Button) findViewById(R.id.button_iconTalkToKids);
        yourKids = (Button) findViewById(R.id.button_iconKids);

        Ask.on(this)
                .forPermissions(Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE)
                .go();

        //Bottom navigation
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationViewYourKids = findViewById(R.id.navigation_yourkids);
        bottomNavigationViewReports = findViewById(R.id.navigation_reports);
        bottomNavigationViewInformation = findViewById(R.id.navigation_information);

        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeParentActivity.this, ParentQuizActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        waysToTackleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeParentActivity.this, WaysToTackleActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        talkToKidsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeParentActivity.this, ParentTalkActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        yourKids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeParentActivity.this, YourKidsActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.infoshowcase, menu);
        return true;
    }

    //Menu item help - showcase view
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.infoshowcaseicon)
        {
            new GuideView.Builder(this)
                    .setTitle("BULLY IDENTIFICATION QUIZ:")
                    .setContentText("A set of 10 questions which will help you to know if your kid is being bullied.")
                    .setTargetView(quizButton)
                    .setGravity(Gravity.center)
                    .setContentTextSize(12)
                    .setTitleTypeFace(Typeface.DEFAULT_BOLD)
                    .setTitleTextSize(14)
                    .setDismissType(DismissType.outside)
                    .setGuideListener(new GuideListener() {
                        @Override
                        public void onDismiss(View view) {
                            new GuideView.Builder(HomeParentActivity.this)
                                    .setTitle("YOUR KIDS:")
                                    .setContentText("View and add your kids.")
                                    .setTargetView(yourKids)
                                    .setGravity(Gravity.center)
                                    .setContentTextSize(12)
                                    .setTitleTypeFace(Typeface.DEFAULT_BOLD)
                                    .setTitleTextSize(14)
                                    .setDismissType(DismissType.outside)
                                    .setGuideListener(new GuideListener() {
                                        @Override
                                        public void onDismiss(View view) {
                                            new GuideView.Builder(HomeParentActivity.this)
                                                    .setTitle("WAYS TO TACKLE:")
                                                    .setContentText("Important set of information to assist you in tackling cyberbullying.")
                                                    .setTargetView(waysToTackleButton)
                                                    .setGravity(Gravity.center)
                                                    .setContentTextSize(12)
                                                    .setTitleTypeFace(Typeface.DEFAULT_BOLD)
                                                    .setTitleTextSize(14)
                                                    .setDismissType(DismissType.outside)
                                                    .setGuideListener(new GuideListener() {
                                                        @Override
                                                        public void onDismiss(View view) {
                                                            new GuideView.Builder(HomeParentActivity.this)
                                                                    .setTitle("TALK TO KIDS:")
                                                                    .setContentText("The first step to tackle bullying is to talk to the kid. This contains useful guidelines for you to follow.")
                                                                    .setTargetView(talkToKidsButton)
                                                                    .setGravity(Gravity.center)
                                                                    .setContentTextSize(12)
                                                                    .setTitleTypeFace(Typeface.DEFAULT_BOLD)
                                                                    .setTitleTextSize(14)
                                                                    .setDismissType(DismissType.outside)
                                                                    .setGuideListener(new GuideListener() {
                                                                        @Override
                                                                        public void onDismiss(View view) {
                                                                            new GuideView.Builder(HomeParentActivity.this)
                                                                                    .setTitle("YOUR KIDS:")
                                                                                    .setContentText("View and add your kids.")
                                                                                    .setTargetView(bottomNavigationViewYourKids)
                                                                                    .setGravity(Gravity.center)
                                                                                    .setContentTextSize(12)
                                                                                    .setTitleTypeFace(Typeface.DEFAULT_BOLD)
                                                                                    .setTitleTextSize(14)
                                                                                    .setDismissType(DismissType.outside)
                                                                                    .setGuideListener(new GuideListener() {
                                                                                        @Override
                                                                                        public void onDismiss(View view) {
                                                                                            new GuideView.Builder(HomeParentActivity.this)
                                                                                                    .setTitle("REPORTS:")
                                                                                                    .setContentText("View all the reports/evidences for all your kids.")
                                                                                                    .setTargetView(bottomNavigationViewReports)
                                                                                                    .setGravity(Gravity.center)
                                                                                                    .setContentTextSize(12)
                                                                                                    .setTitleTypeFace(Typeface.DEFAULT_BOLD)
                                                                                                    .setTitleTextSize(14)
                                                                                                    .setDismissType(DismissType.outside)
                                                                                                    .setGuideListener(new GuideListener() {
                                                                                                        @Override
                                                                                                        public void onDismiss(View view) {
                                                                                                            new GuideView.Builder(HomeParentActivity.this)
                                                                                                                    .setTitle("INFORMATION:")
                                                                                                                    .setContentText("Important set of information to assist you in tackling cyberbullying.")
                                                                                                                    .setTargetView(bottomNavigationViewInformation)
                                                                                                                    .setGravity(Gravity.center)
                                                                                                                    .setContentTextSize(12)
                                                                                                                    .setTitleTypeFace(Typeface.DEFAULT_BOLD)
                                                                                                                    .setTitleTextSize(14)
                                                                                                                    .setDismissType(DismissType.outside)
                                                                                                                    .build()
                                                                                                                    .show();
                                                                                                        }
                                                                                                    })
                                                                                                    .build()
                                                                                                    .show();
                                                                                        }
                                                                                    })
                                                                                    .build()
                                                                                    .show();
                                                                        }
                                                                    })
                                                                    .build()
                                                                    .show();
                                                        }
                                                    })
                                                    .build()
                                                    .show();
                                        }
                                    })
                                    .build()
                                    .show();
                        }
                    })
                    .build()
                    .show();
        }

        return super.onOptionsItemSelected(item);
    }
}
