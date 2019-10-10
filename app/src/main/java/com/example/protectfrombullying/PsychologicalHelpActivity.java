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

public class PsychologicalHelpActivity extends AppCompatActivity {

    private SwipeSelector swipeSelector_psychological1;
    private SwipeSelector swipeSelector_psychological2;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(PsychologicalHelpActivity.this, HomeParentActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                case R.id.navigation_reports:
                    Intent dashboardIntent = new Intent(PsychologicalHelpActivity.this, ReportsActivity.class);
                    startActivity(dashboardIntent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                case R.id.navigation_yourkids:
                    Intent notificationIntent = new Intent(PsychologicalHelpActivity.this, YourKidsActivity.class);
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
        setContentView(R.layout.activity_psychological_help);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        swipeSelector_psychological1 = (SwipeSelector) findViewById(R.id.swipeSelector_psychological1);
        swipeSelector_psychological2 = (SwipeSelector) findViewById(R.id.swipeSelector_psychological2);

        swipeSelector_psychological1.setItems(
                // The first argument is the value for that item, and should in most cases be unique for the
                // current SwipeSelector, just as you would assign values to radio buttons.
                // You can use the value later on to check what the selected item was.
                // The value can be any Object, here we're using ints.
                new SwipeItem(0,"TREATMENT" , ""),
                new SwipeItem(1, "", "There are three main components of treatment for victims of chronic bullying:"),
                new SwipeItem(2, "", "Realisation and acknowledgement of the damage and humiliation that has occurred."),
                new SwipeItem(3, "", "Dealing with the events associated with the bullying."),
                new SwipeItem(4, "", "Making sense of what has happened, as for any trauma victim.")
        );

        swipeSelector_psychological2.setItems(
                // The first argument is the value for that item, and should in most cases be unique for the
                // current SwipeSelector, just as you would assign values to radio buttons.
                // You can use the value later on to check what the selected item was.
                // The value can be any Object, here we're using ints.
                new SwipeItem(0,"SEEKING HELP" , ""),
                new SwipeItem(1, "", "Talk to your child’s teacher or school psychologist if the issue involves another student at school."),
                new SwipeItem(2, "", "Psychologists are highly trained and qualified professionals, skilled in diagnosing and treating a range of mental health concerns, including the impacts of bullying."),
                new SwipeItem(3, "", "If you are referred to a psychologist by your GP, you might be eligible for a . Ask your psychologist or GP for details."),
                new SwipeItem(4, "", "Call 1800 333 497 ask your GP or another health professional to refer you.")
        );
    }

}
