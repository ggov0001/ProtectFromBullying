package com.example.protectfrombullying;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.roughike.swipeselector.SwipeItem;
import com.roughike.swipeselector.SwipeSelector;

public class GeneralLegalInformationActivity extends AppCompatActivity {

    private SwipeSelector swipeSelector_legal1;
    private SwipeSelector swipeSelector_legal2;
    private SwipeSelector swipeSelector_legal3;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(GeneralLegalInformationActivity.this, HomeParentActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                case R.id.navigation_reports:
                    Intent dashboardIntent = new Intent(GeneralLegalInformationActivity.this, ReportsActivity.class);
                    startActivity(dashboardIntent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                case R.id.navigation_yourkids:
                    Intent yourKidsIntent = new Intent(GeneralLegalInformationActivity.this, YourKidsActivity.class);
                    startActivity(yourKidsIntent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                case R.id.navigation_information:
                    Intent infromationIntent = new Intent(GeneralLegalInformationActivity.this, WaysToTackleActivity.class);
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
        setContentView(R.layout.activity_general_legal_information);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        swipeSelector_legal1 = (SwipeSelector) findViewById(R.id.swipeSelector_legal1);
        swipeSelector_legal2 = (SwipeSelector) findViewById(R.id.swipeSelector_legal2);
        swipeSelector_legal3 = (SwipeSelector) findViewById(R.id.swipeSelector_legal3);

        swipeSelector_legal1.setItems(
                // The first argument is the value for that item, and should in most cases be unique for the
                // current SwipeSelector, just as you would assign values to radio buttons.
                // You can use the value later on to check what the selected item was.
                // The value can be any Object, here we're using ints.
                new SwipeItem(0,"Help From eSafety Commission" , ""),
                new SwipeItem(1, "", "Help getting the cyberbully material removed from the service it is on."),
                new SwipeItem(2, "", "Work with your school, parents, legal guardian or the police to help stop the cyberbullying.")
        );

        swipeSelector_legal2.setItems(
                // The first argument is the value for that item, and should in most cases be unique for the
                // current SwipeSelector, just as you would assign values to radio buttons.
                // You can use the value later on to check what the selected item was.
                // The value can be any Object, here we're using ints.
                new SwipeItem(0,"Collect Evidence" , ""),
                new SwipeItem(1, "", "Before blocking someone or deleting bullying material, take screenshots and collect evidence including dates and times."),
                new SwipeItem(2, "", "You need a record of how long it has been going on if the bullying behaviour continues "),
                new SwipeItem(3, "", "You also need evidence if you want to REPORT it!"),
                new SwipeItem(4, "", "Be aware that possessing or sharing sexualised images of people under 18 may be a crime, even if you have just taken a screenshot for evidence purposes")
        );

        swipeSelector_legal3.setItems(
                // The first argument is the value for that item, and should in most cases be unique for the
                // current SwipeSelector, just as you would assign values to radio buttons.
                // You can use the value later on to check what the selected item was.
                // The value can be any Object, here we're using ints.
                new SwipeItem(0,"Report" , ""),
                new SwipeItem(1, "", "Many social media services, games, apps and websites make it easy to report content posted by other people. Visit eSafety website."),
                new SwipeItem(2, "", "If serious cyberbullying is affecting your child and you need help to get the material removed from a social media service or other platform, eSafety can help."),
                new SwipeItem(3, "", "You can also make a cyberbullying report to eSafety Commissioner to gain help")
        );

    }

}
