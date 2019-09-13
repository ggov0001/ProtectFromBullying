package com.example.protectfrombullying;

import android.arch.persistence.room.Room;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class KidHomeActivity extends AppCompatActivity {

    //Declare all buttons in the home screen
    private Button qrcodeScannerButton;

    private static TextView check;

    private KidsDatabase database;

    //Receiver
    Receiver receiver;

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
        //Initialize database
        database = Room.databaseBuilder(getApplicationContext(), KidsDatabase.class, "KidsDatabase").fallbackToDestructiveMigration().build();

        //Button on click listener
        qrcodeScannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KidHomeActivity.this, KidsQRScanActivity.class);
                startActivity(intent);
            }
        });

        receiver = new Receiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.protectfrombullying");
        registerReceiver(receiver, intentFilter);
    }

    public class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String pack = intent.getStringExtra("package");
            String title = intent.getStringExtra("title");
            String text = intent.getStringExtra("text");

            Log.i("receive Package",pack);
            Log.i("receive Title",title);
            Log.i("receive Title",title);
            Log.i("receive Text",text);

            Toast.makeText(getApplicationContext(), "Received from " + pack, Toast.LENGTH_LONG).show();
        }
    }

}
