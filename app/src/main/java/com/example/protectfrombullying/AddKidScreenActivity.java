package com.example.protectfrombullying;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddKidScreenActivity extends AppCompatActivity {

    private EditText firstName;
    private ImageView qrCode;

    private Button generateButton;
    private Button doneButton;
    private Button cancelButton;

    String idGenerated;
    String contentsOfQRCode;

    private KidsDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kid_screen);

        firstName = (EditText) findViewById(R.id.register_firstName);

        generateButton = (Button) findViewById(R.id.button_generateqrcode);
        doneButton = (Button) findViewById(R.id.button_doneqrcode);
        cancelButton = (Button) findViewById(R.id.button_cancelqrcode);

        qrCode = (ImageView) findViewById(R.id.imageview_qrcode);

        database = Room.databaseBuilder(getApplicationContext(), KidsDatabase.class, "KidsDatabase").fallbackToDestructiveMigration().build();


        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(firstName.getText().toString().matches("^[a-zA-Z]{3,10}(?: [a-zA-Z]+){0,2}$")))
                {
                    firstName.setError("Name can only have alphabets with a minimum of 3 and a maximum of 10.");
                    firstName.requestFocus();
                }
                else
                {
                    idGenerated = getAlphaNumericString(10);
                    contentsOfQRCode = firstName.getText().toString() + "/" + idGenerated;
                    new QRCodeImageDownloader(qrCode).execute("https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=" + contentsOfQRCode);
                }
            }

        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!(firstName.getText().toString().matches("^[a-zA-Z]{3,10}(?: [a-zA-Z]+){0,2}$")))
                {
                    firstName.setError("Name can only have alphabets with a minimum of 3 and a maximum of 10.");
                    firstName.requestFocus();
                }
                else
                {
                    AddKid addKid = new AddKid();
                    addKid.execute();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddKidScreenActivity.this, YourKidsActivity.class);
                startActivity(intent);
            }
        });

    }

    // function to generate a random string of length n
    public String getAlphaNumericString(int n)
    {
        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    //Inserting Value to database
    private class AddKid extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            Kids kids = new Kids(idGenerated, firstName.getText().toString());
            database.kidsDAO().insert(kids);

            return null;
        }

        @Override
        protected void onPostExecute(String args) {
            Toast.makeText(getApplicationContext(), "Added!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(AddKidScreenActivity.this, YourKidsActivity.class);
            startActivity(intent);
        }
    }

}
