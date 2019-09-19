package com.example.protectfrombullying;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class UserTypeActivity extends AppCompatActivity {

    //Image
    private ImageView imageView_Parent;
    private ImageView imageView_Student;

    private Animation forImage;

    //Database
    private KidsDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type);

        imageView_Parent = (ImageView) findViewById(R.id.imageView_Parent);
        imageView_Student = (ImageView) findViewById(R.id.imageView_Student);
        forImage = AnimationUtils.loadAnimation(this, R.anim.usertype);
        //Initialize database
        database = Room.databaseBuilder(getApplicationContext(), KidsDatabase.class, "KidsDatabase").fallbackToDestructiveMigration().build();

        imageView_Student.startAnimation(forImage);
        imageView_Parent.startAnimation(forImage);

        imageView_Parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParentLoggedInAsync parentLoggedInAsync = new ParentLoggedInAsync();
                parentLoggedInAsync.execute();
            }
        });

        imageView_Student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KidLoggedInAsync KidLoggedInAsync = new KidLoggedInAsync();
                KidLoggedInAsync.execute();
            }
        });


    }

    //Inserting Value to database
    private class ParentLoggedInAsync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

//            LoggedIn loggedIn = new LoggedIn(1, 1);
//            database.loggedInDAO().insert(loggedIn);
            return null;
        }

        @Override
        protected void onPostExecute(String args) {
            Toast.makeText(getApplicationContext(), "Welcome!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(UserTypeActivity.this, HomeParentActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }

    //Inserting Value to database
    private class KidLoggedInAsync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

//            LoggedIn loggedIn = new LoggedIn(0, 1);
//            database.loggedInDAO().insert(loggedIn);
            return null;
        }

        @Override
        protected void onPostExecute(String args) {
            Toast.makeText(getApplicationContext(), "Welcome!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(UserTypeActivity.this, HomeKidActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }
}
