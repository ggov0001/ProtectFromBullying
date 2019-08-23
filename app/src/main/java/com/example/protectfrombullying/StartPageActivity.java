package com.example.protectfrombullying;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StartPageActivity extends AppCompatActivity {

    private static int timeOut = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent goToMainActivityIntent = new Intent(StartPageActivity.this, UserTypeActivity.class);
                startActivity(goToMainActivityIntent);
                finish();
            }
        }, timeOut);
    }
}
