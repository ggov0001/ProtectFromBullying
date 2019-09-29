package com.example.protectfrombullying;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.roughike.swipeselector.SwipeItem;
import com.roughike.swipeselector.SwipeSelector;

public class PreventionActivity extends AppCompatActivity {

    private SwipeSelector swipeSelector1;
    private SwipeSelector swipeSelector2;
    private SwipeSelector swipeSelector3;
    private SwipeSelector swipeSelector4;
    private SwipeSelector swipeSelector5;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(PreventionActivity.this, HomeParentActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                case R.id.navigation_reports:
                    Intent dashboardIntent = new Intent(PreventionActivity.this, ReportsActivity.class);
                    startActivity(dashboardIntent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                case R.id.navigation_yourkids:
                    Intent yourKidsIntent = new Intent(PreventionActivity.this, YourKidsActivity.class);
                    startActivity(yourKidsIntent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                case R.id.navigation_information:
                    Intent infromationIntent = new Intent(PreventionActivity.this, WaysToTackleActivity.class);
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
        setContentView(R.layout.activity_prevention);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        swipeSelector1 = (SwipeSelector) findViewById(R.id.swipeSelector1);
        swipeSelector2 = (SwipeSelector) findViewById(R.id.swipeSelector2);
        swipeSelector3 = (SwipeSelector) findViewById(R.id.swipeSelector3);
        swipeSelector4 = (SwipeSelector) findViewById(R.id.swipeSelector4);
        swipeSelector5 = (SwipeSelector) findViewById(R.id.swipeSelector5);

        swipeSelector1.setItems(
                // The first argument is the value for that item, and should in most cases be unique for the
                // current SwipeSelector, just as you would assign values to radio buttons.
                // You can use the value later on to check what the selected item was.
                // The value can be any Object, here we're using ints.
                new SwipeItem(0,"Keep the computer in a common area of the home." , ""),
                new SwipeItem(1, "", "Do not allow it in your children\'s bedroom."),
                new SwipeItem(2, "", "Monitor their online usage.")
        );

        swipeSelector2.setItems(
                // The first argument is the value for that item, and should in most cases be unique for the
                // current SwipeSelector, just as you would assign values to radio buttons.
                // You can use the value later on to check what the selected item was.
                // The value can be any Object, here we're using ints.
                new SwipeItem(0,"Learn how various social networking apps and sites work." , ""),
                new SwipeItem(1, "", "Become familiar with Snapchat, Facebook, Instagram, Whatsapp and Twitter."),
                new SwipeItem(2, "", "Ask your children if they will show you their profile pages.")
        );

        swipeSelector3.setItems(
                // The first argument is the value for that item, and should in most cases be unique for the
                // current SwipeSelector, just as you would assign values to radio buttons.
                // You can use the value later on to check what the selected item was.
                // The value can be any Object, here we're using ints.
                new SwipeItem(0,"Talk regularly and specifically with your children about online issues." , ""),
                new SwipeItem(1, "", "Let them know they can come to you for help if anything is inappropriate, upsetting, or dangerous.")
        );

        swipeSelector4.setItems(
                // The first argument is the value for that item, and should in most cases be unique for the
                // current SwipeSelector, just as you would assign values to radio buttons.
                // You can use the value later on to check what the selected item was.
                // The value can be any Object, here we're using ints.
                new SwipeItem(0,"Build trust with your children." , ""),
                new SwipeItem(1, "", "Set time limits, explain your reasons for them, and discuss rules for online safety and Internet use."),
                new SwipeItem(2, "", "Ask your children to contribute to establishing the rules; then they\'ll be more inclined to follow them.")
        );

        swipeSelector5.setItems(
                // The first argument is the value for that item, and should in most cases be unique for the
                // current SwipeSelector, just as you would assign values to radio buttons.
                // You can use the value later on to check what the selected item was.
                // The value can be any Object, here we're using ints.
                new SwipeItem(0,"Tell your children not to respond to any cyberbullying threats or comments online." , ""),
                new SwipeItem(1, "", "Also, do not delete any of the messages."),
                new SwipeItem(2, "", "Instead, print out all the messages, including the email addresses or social media handles of the cyberbully."),
                new SwipeItem(3, "", "You will need the messages to verify and prove there is cyberbullying.")
        );

    }
}
