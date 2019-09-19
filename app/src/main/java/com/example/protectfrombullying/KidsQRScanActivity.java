package com.example.protectfrombullying;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.zxing.Result;

import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class KidsQRScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView scannerView;

    private KidsDatabase database;
    //boolean isDone = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initialize database
        database = Room.databaseBuilder(getApplicationContext(), KidsDatabase.class, "KidsDatabase").fallbackToDestructiveMigration().build();

        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);

    }

    @Override
    public void handleResult(Result result) {
//        HomeKidActivity.check.setText(result.getText());
        final String[] splitIdAndName = result.getText().split("/");

        String kidId = splitIdAndName[1];
        String kidName = splitIdAndName[0];
        final Kids kids = new Kids(kidId, kidName);

        AddKidAsync addKidAsync = new AddKidAsync();
        addKidAsync.execute(kids);
        //if(isDone)
            onBackPressed();

    }

    @Override
    public void onResume() {
        super.onResume();
        scannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        scannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        scannerView.stopCamera();           // Stop camera on pause
    }

    //Inserting Value to database
    private class AddKidAsync extends AsyncTask<Kids, Void, String> {

        @Override
        protected String doInBackground(Kids... kids) {

            database.kidsDAO().insert(kids[0]);

            return null;
        }

        @Override
        protected void onPostExecute(String args) {
            //Toast.makeText(getApplicationContext(), "Added!", Toast.LENGTH_LONG).show();
            ViewKidAsync viewKidAsync = new ViewKidAsync();
            viewKidAsync.execute();
        }
    }

    //View database
    private class ViewKidAsync extends AsyncTask<String, Void, List<Kids>> {

        @Override
        protected List<Kids> doInBackground(String... args) {

            List<Kids> check;
            check = database.kidsDAO().getAll();

            return check;
        }

        @Override
        protected void onPostExecute(List<Kids> kids) {
            //HomeKidActivity.check.setText(kids.get(0).getKidName());
            Toast.makeText(getApplicationContext(), "Thanks! Welcome " + kids.get(0).getKidName() + ".", Toast.LENGTH_LONG).show();
            //isDone = true;
            Intent intent = new Intent(KidsQRScanActivity.this, HomeKidActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }
}
