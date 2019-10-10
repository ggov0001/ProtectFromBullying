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

public class CounsellingActivity extends AppCompatActivity {

    private SwipeSelector counselling1;
    private SwipeSelector counselling2;
    private SwipeSelector counselling3;
    private SwipeSelector counselling4;
    private SwipeSelector counselling5;
    private SwipeSelector counselling6;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(CounsellingActivity.this, HomeParentActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                case R.id.navigation_reports:
                    Intent dashboardIntent = new Intent(CounsellingActivity.this, ReportsActivity.class);
                    startActivity(dashboardIntent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                case R.id.navigation_yourkids:
                    Intent yourKidsIntent = new Intent(CounsellingActivity.this, YourKidsActivity.class);
                    startActivity(yourKidsIntent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                case R.id.navigation_information:
                    Intent infromationIntent = new Intent(CounsellingActivity.this, WaysToTackleActivity.class);
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
        setContentView(R.layout.activity_counselling);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        counselling1 = (SwipeSelector) findViewById(R.id.swipeSelector_counselling1);
        counselling2 = (SwipeSelector) findViewById(R.id.swipeSelector_counselling2);
        counselling3 = (SwipeSelector) findViewById(R.id.swipeSelector_counselling3);
        counselling4 = (SwipeSelector) findViewById(R.id.swipeSelector_counselling4);
        counselling5 = (SwipeSelector) findViewById(R.id.swipeSelector_counselling5);
        counselling6 = (SwipeSelector) findViewById(R.id.swipeSelector_counselling6);

        counselling1.setItems(
                // The first argument is the value for that item, and should in most cases be unique for the
                // current SwipeSelector, just as you would assign values to radio buttons.
                // You can use the value later on to check what the selected item was.
                // The value can be any Object, here we're using ints.
                new SwipeItem(0,"Kids Helpline" , ""),
                new SwipeItem(1, "", "1800 551 800 (available 24/7)")
        );

        counselling2.setItems(
                // The first argument is the value for that item, and should in most cases be unique for the
                // current SwipeSelector, just as you would assign values to radio buttons.
                // You can use the value later on to check what the selected item was.
                // The value can be any Object, here we're using ints.
                new SwipeItem(0,"eHeadspace" , ""),
                new SwipeItem(1, "", "1800 650 890 (available 9am to 1am, everyday)")
        );

        counselling3.setItems(
                // The first argument is the value for that item, and should in most cases be unique for the
                // current SwipeSelector, just as you would assign values to radio buttons.
                // You can use the value later on to check what the selected item was.
                // The value can be any Object, here we're using ints.
                new SwipeItem(0,"Lifeline " , ""),
                new SwipeItem(1, "", "13 11 14 (available 24/7)")
        );

        counselling4.setItems(
                // The first argument is the value for that item, and should in most cases be unique for the
                // current SwipeSelector, just as you would assign values to radio buttons.
                // You can use the value later on to check what the selected item was.
                // The value can be any Object, here we're using ints.
                new SwipeItem(0,"QLife " , ""),
                new SwipeItem(1, "", "1800 184 527 (available 3pm to midnight, everyday)")
        );

        counselling5.setItems(
                // The first argument is the value for that item, and should in most cases be unique for the
                // current SwipeSelector, just as you would assign values to radio buttons.
                // You can use the value later on to check what the selected item was.
                // The value can be any Object, here we're using ints.
                new SwipeItem(0,"1800RESPECT" , ""),
                new SwipeItem(1, "", "1800 737 732 (available 24/7))")
        );

        counselling6.setItems(
                // The first argument is the value for that item, and should in most cases be unique for the
                // current SwipeSelector, just as you would assign values to radio buttons.
                // You can use the value later on to check what the selected item was.
                // The value can be any Object, here we're using ints.
                new SwipeItem(0,"Mensline Australia" , ""),
                new SwipeItem(1, "", "1300 78 99 78 (available 24/7, for male kids)")
        );
    }

}
