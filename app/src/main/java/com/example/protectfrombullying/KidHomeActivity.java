package com.example.protectfrombullying;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class KidHomeActivity extends AppCompatActivity {

    //Declare all buttons in the home screen
    private Button qrcodeScannerButton;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(KidHomeActivity.this, KidHomeActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_dashboard:
                    Intent dashboardIntent = new Intent(KidHomeActivity.this, DummyActivity.class);
                    startActivity(dashboardIntent);
                    return true;
                case R.id.navigation_notifications:
                    Intent notificationIntent = new Intent(KidHomeActivity.this, DummyActivity.class);
                    startActivity(notificationIntent);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_home);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        qrcodeScannerButton = (Button) findViewById(R.id.button_qrcode);
        qrcodeScannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KidHomeActivity.this, KidQRScanActivity.class);
                startActivity(intent);
            }
        });
    }

}
