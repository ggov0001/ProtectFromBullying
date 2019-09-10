package com.example.protectfrombullying;

import android.Manifest;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.util.List;

public class KidQRScanActivity extends AppCompatActivity {

    private SurfaceView surfaceView;
    private CameraSource cameraSource;
    private TextView textView;
    private BarcodeDetector barcodeDetector;

    private KidsDatabase database;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(KidQRScanActivity.this, HomeActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_dashboard:
                    Intent dashboardIntent = new Intent(KidQRScanActivity.this, DummyActivity.class);
                    startActivity(dashboardIntent);
                    return true;
                case R.id.navigation_notifications:
                    Intent notificationIntent = new Intent(KidQRScanActivity.this, DummyActivity.class);
                    startActivity(notificationIntent);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_qrscan);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        surfaceView = (SurfaceView) findViewById(R.id.camerapreview);
        textView = (TextView) findViewById(R.id.qrtext);

        barcodeDetector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build();

        cameraSource = new CameraSource.Builder(this, barcodeDetector).setRequestedPreviewSize(640, 480).build();

        database = Room.databaseBuilder(getApplicationContext(), KidsDatabase.class, "KidsDatabase").fallbackToDestructiveMigration().build();


        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }

                try {
                    cameraSource.start(holder);
                }
                catch (IOException ie)
                {
                    ie.printStackTrace();
                }

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrcodes = detections.getDetectedItems();
                final String getValue = qrcodes.valueAt(0).rawValue;
                final String[] splitIdAndName = getValue.split("/");

                String kidId = splitIdAndName[1];
                String kidName = splitIdAndName[0];
                final Kids kids = new Kids(kidId, kidName);

                if(qrcodes.size()!=0)
                {
                    textView.post(new Runnable() {
                        @Override
                        public void run() {
                            Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                            vibrator.vibrate(1000);

                            AddKidAsync addKidAsync = new AddKidAsync();
                            addKidAsync.execute(kids);
//                            ViewKidAsync viewKidAsync = new ViewKidAsync();
//                            viewKidAsync.execute();
                        }
                    });
                }

            }
        });

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
            Toast.makeText(getApplicationContext(), "Added!", Toast.LENGTH_LONG).show();
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
            textView.setText(kids.get(0).getKidName()+"/"+kids.get(0).getKidId());
            Toast.makeText(getApplicationContext(), "Thanks! Welcome" + " + " + kids.get(0).getKidName() + ".", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(KidQRScanActivity.this, KidHomeActivity.class);
            startActivity(intent);
        }
    }

}
