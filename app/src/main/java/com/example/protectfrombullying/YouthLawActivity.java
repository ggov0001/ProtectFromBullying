package com.example.protectfrombullying;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.roughike.swipeselector.SwipeItem;
import com.roughike.swipeselector.SwipeSelector;

public class YouthLawActivity extends AppCompatActivity {

    private SwipeSelector swipeSelector_crime1;
    private SwipeSelector swipeSelector_crime2;
    private SwipeSelector swipeSelector_crime3;
    private SwipeSelector swipeSelector_crime4;
    private SwipeSelector swipeSelector_crime5;
    private SwipeSelector swipeSelector_crime6;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(YouthLawActivity.this, HomeParentActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                case R.id.navigation_reports:
                    Intent dashboardIntent = new Intent(YouthLawActivity.this, ReportsActivity.class);
                    startActivity(dashboardIntent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                case R.id.navigation_yourkids:
                    Intent yourKidsIntent = new Intent(YouthLawActivity.this, YourKidsActivity.class);
                    startActivity(yourKidsIntent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                case R.id.navigation_information:
                    Intent infromationIntent = new Intent(YouthLawActivity.this, WaysToTackleActivity.class);
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
        setContentView(R.layout.activity_youth_law);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        swipeSelector_crime1 = (SwipeSelector) findViewById(R.id.swipeSelector_crime1);
        swipeSelector_crime2 = (SwipeSelector) findViewById(R.id.swipeSelector_crime2);
        swipeSelector_crime3 = (SwipeSelector) findViewById(R.id.swipeSelector_crime3);
        swipeSelector_crime4 = (SwipeSelector) findViewById(R.id.swipeSelector_crime4);
        swipeSelector_crime5 = (SwipeSelector) findViewById(R.id.swipeSelector_crime5);
        swipeSelector_crime6 = (SwipeSelector) findViewById(R.id.swipeSelector_crime6);

        swipeSelector_crime1.setItems(
                // The first argument is the value for that item, and should in most cases be unique for the
                // current SwipeSelector, just as you would assign values to radio buttons.
                // You can use the value later on to check what the selected item was.
                // The value can be any Object, here we're using ints.
                new SwipeItem(0,"Nude or Sexual Images" , ""),
                new SwipeItem(1, "", "Sending or posting nude or sexual images of someone without their consent or threatening to."),
                new SwipeItem(2, "", "This is called IMAGE_BASED ABUSE and it is a CRIME IN VICTORIA and is also against NATIONAL LAW.")
        );

        swipeSelector_crime2.setItems(
                // The first argument is the value for that item, and should in most cases be unique for the
                // current SwipeSelector, just as you would assign values to radio buttons.
                // You can use the value later on to check what the selected item was.
                // The value can be any Object, here we're using ints.
                new SwipeItem(0,"Stalking" , ""),
                new SwipeItem(1, "", "Contacting someone by text or email or through an online messaging service."),
                new SwipeItem(2, "", "Saying things about someone online or in emails or texts."),
                new SwipeItem(3, "", "Logging on to someone’s online accounts to access information or change anything."),
                new SwipeItem(4, "", "Tracking someone’s use of the internet or email."),
                new SwipeItem(5, "", "Threatening someone or using abusive or offensive words to them."),
                new SwipeItem(6, "", "Sending someone offensive material or posting it where they can see it.")
        );

        swipeSelector_crime3.setItems(
                // The first argument is the value for that item, and should in most cases be unique for the
                // current SwipeSelector, just as you would assign values to radio buttons.
                // You can use the value later on to check what the selected item was.
                // The value can be any Object, here we're using ints.
                new SwipeItem(0,"Making Threats" , ""),
                new SwipeItem(1, "", "Using a phone or the internet to scare someone by threatening to kill or seriously harm them."),
                new SwipeItem(2, "", "Threatening to kill or seriously injure someone or to destroy or damage their property."),
                new SwipeItem(3, "", "Threatening rape or sexual assault.")
        );

        swipeSelector_crime4.setItems(
                // The first argument is the value for that item, and should in most cases be unique for the
                // current SwipeSelector, just as you would assign values to radio buttons.
                // You can use the value later on to check what the selected item was.
                // The value can be any Object, here we're using ints.
                new SwipeItem(0,"Build trust with your children" , ""),
                new SwipeItem(1, "", "Set time limits, explain your reasons for them, and discuss rules for online safety and Internet use."),
                new SwipeItem(2, "", "Ask your children to contribute to establishing the rules; then they\'ll be more inclined to follow them.")
        );

        swipeSelector_crime5.setItems(
                // The first argument is the value for that item, and should in most cases be unique for the
                // current SwipeSelector, just as you would assign values to radio buttons.
                // You can use the value later on to check what the selected item was.
                // The value can be any Object, here we're using ints.
                new SwipeItem(0,"Encouraging Suicide" , ""),
                new SwipeItem(1, "", "Tries to persuade someone to commit suicide."),
                new SwipeItem(2, "", "Send or post anything that encourages or helps someone to commit suicide."),
                new SwipeItem(3, "", "Encourage someone to commit suicide."),
                new SwipeItem(4, "", "Help someone commit or attempt to commit suicide.")
        );

        swipeSelector_crime6.setItems(
                // The first argument is the value for that item, and should in most cases be unique for the
                // current SwipeSelector, just as you would assign values to radio buttons.
                // You can use the value later on to check what the selected item was.
                // The value can be any Object, here we're using ints.
                new SwipeItem(0,"Other Cases" , ""),
                new SwipeItem(1, "", "Log on to someone’s online accounts without permission to access information, to change anything without permission, or to commit a serious offence."),
                new SwipeItem(2, "", "Commit a serious offence."),
                new SwipeItem(3, "", "Maliciously ‘publish’ (e.g. post online) untrue things about someone which damages their reputation (defames them)."),
                new SwipeItem(4, "", "Encourage hatred, serious contempt or revulsion or severe ridicule towards someone because of their race or religion."),
                new SwipeItem(5, "", "Damage someone’s reputation because they say untrue things about them.")
        );

    }

}
