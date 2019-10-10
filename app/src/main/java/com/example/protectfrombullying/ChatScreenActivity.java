package com.example.protectfrombullying;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ChatScreenActivity extends AppCompatActivity {

    private TextView question1;
    private TextView question2;
    private TextView question3;
    private TextView answer1;
    private TextView answer2;
    private TextView answer3;
    private TextView finalmessage1;
    private TextView finalmessage2;
    private TextView finalmessage3;

    private ImageView bot1;
    private ImageView bot2;
    private ImageView bot3;
    private ImageView bot4;

    private Button yesButton;
    private Button noButton;
    private Button goHomeButton;

    private AVLoadingIndicatorView typingAvi1;
    private AVLoadingIndicatorView typingAvi2;
    private AVLoadingIndicatorView typingAvi3;

    private StorageReference mStorageRef;
    private String fileName;
    private Uri downloadUrl;

    private int currentQuestion = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);

        FirebaseApp.initializeApp(ChatScreenActivity.this);
        mStorageRef = FirebaseStorage.getInstance().getReference();

        question1 = (TextView) findViewById(R.id.chat_question1);
        question2 = (TextView) findViewById(R.id.chat_question2);
        question3 = (TextView) findViewById(R.id.chat_question3);

        answer1 = (TextView) findViewById(R.id.chat_answer1);
        answer2 = (TextView) findViewById(R.id.chat_answer2);
        answer3 = (TextView) findViewById(R.id.chat_answer3);

        finalmessage1 = (TextView) findViewById(R.id.chat_finalmessage1);
        finalmessage2 = (TextView) findViewById(R.id.chat_finalmessage2);
        finalmessage3 = (TextView) findViewById(R.id.chat_finalmessage3);

        bot1 = (ImageView) findViewById(R.id.imageView_bot1);
        bot2 = (ImageView) findViewById(R.id.imageView_bot2);
        bot3 = (ImageView) findViewById(R.id.imageView_bot3);
        bot4 = (ImageView) findViewById(R.id.imageView_bot4);

        yesButton = (Button) findViewById(R.id.button_yesChat);
        noButton = (Button) findViewById(R.id.button_noChat);
        goHomeButton = (Button) findViewById(R.id.button_gohome);

        typingAvi1 = (AVLoadingIndicatorView) findViewById(R.id.avi_1);
        typingAvi2 = (AVLoadingIndicatorView) findViewById(R.id.avi_2);
        typingAvi3 = (AVLoadingIndicatorView) findViewById(R.id.avi_3);

        //Yes option chosen as the answer
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (currentQuestion) {
                    case 1: //If the 'Yes' button is clicked for question 1
                            //Set answer and exit message visible
                            answer1.setVisibility(View.VISIBLE);
                            answer1.setText("YES");
                            typingAvi1.show();

                            new Handler().postDelayed(new Runnable() {

                                @Override
                                public void run() {
                                    typingAvi1.hide();

                                    finalmessage1.setVisibility(View.VISIBLE);
                                    bot2.setVisibility(View.VISIBLE);
                                    goHomeButton.setVisibility(View.VISIBLE);

                                    //Set invisible
                                    yesButton.setVisibility(View.INVISIBLE);
                                    noButton.setVisibility(View.INVISIBLE);

                                    //Increment current question
                                    currentQuestion++;
                                }

                            }, 2000);

                            break;


                    case 2: //If the 'Yes' button is clicked for question 2
                        //Set answer and exit message visible
                        answer2.setVisibility(View.VISIBLE);
                        answer2.setText("YES");

                        typingAvi2.show();

                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                typingAvi2.hide();

                                bot3.setVisibility(View.VISIBLE);
                                finalmessage2.setVisibility(View.VISIBLE);
                                goHomeButton.setVisibility(View.VISIBLE);

                                //Set two buttons invisible
                                yesButton.setVisibility(View.INVISIBLE);
                                noButton.setVisibility(View.INVISIBLE);

                                //Increment current question
                                currentQuestion++;
                            }

                        }, 2000);



                        break;

                    case 3: //If the 'Yes' button is clicked for question 3
                        //Set answer and exit message visible
                        answer3.setVisibility(View.VISIBLE);
                        answer3.setText("YES");

                        typingAvi3.show();

                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                typingAvi3.hide();

                                bot4.setVisibility(View.VISIBLE);
                                finalmessage3.setVisibility(View.VISIBLE);
                                goHomeButton.setVisibility(View.VISIBLE);

                                //Set two buttons invisible
                                yesButton.setVisibility(View.INVISIBLE);
                                noButton.setVisibility(View.INVISIBLE);
                            }

                        }, 2000);

                        saveVideo();

                        break;

                    default:
                        break;

                }
            }
        });

        //No option chosen as the answer
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch(currentQuestion)
                {
                    case 1: //If the 'No' button is clicked for question 1
                            //Set Visible
                            answer1.setVisibility(View.VISIBLE);
                            answer1.setText("NO");

                            typingAvi1.show();
                        yesButton.setVisibility(View.INVISIBLE);
                        noButton.setVisibility(View.INVISIBLE);

                            new Handler().postDelayed(new Runnable() {

                                @Override
                                public void run() {
                                    typingAvi1.hide();
                                    yesButton.setVisibility(View.VISIBLE);
                                    noButton.setVisibility(View.VISIBLE);

                                    bot2.setVisibility(View.VISIBLE);
                                    question2.setVisibility(View.VISIBLE);

                                    //Increment current question
                                    currentQuestion++;
                                }

                            }, 2000);


                            break;


                    case 2: //If the 'No' button is clicked for question 2
                            //Set Visible
                            answer2.setVisibility(View.VISIBLE);
                            answer2.setText("NO");

                            typingAvi2.show();
                        yesButton.setVisibility(View.INVISIBLE);
                        noButton.setVisibility(View.INVISIBLE);

                            new Handler().postDelayed(new Runnable() {

                                @Override
                                public void run() {
                                    typingAvi2.hide();
                                    yesButton.setVisibility(View.VISIBLE);
                                    noButton.setVisibility(View.VISIBLE);

                                    bot3.setVisibility(View.VISIBLE);
                                    question3.setVisibility(View.VISIBLE);

                                    //Increment current question
                                    currentQuestion++;
                                }

                            }, 2000);


                            break;

                    case 3: //If the 'No' button is clicked for question 3
                            //Set Visible
                            answer3.setVisibility(View.VISIBLE);
                            answer3.setText("NO");

                            typingAvi3.show();
                        yesButton.setVisibility(View.INVISIBLE);
                        noButton.setVisibility(View.INVISIBLE);

                            new Handler().postDelayed(new Runnable() {

                                @Override
                                public void run() {
                                    typingAvi3.hide();
                                    yesButton.setVisibility(View.VISIBLE);
                                    noButton.setVisibility(View.VISIBLE);

                                    bot4.setVisibility(View.VISIBLE);
                                    finalmessage3.setVisibility(View.VISIBLE);
                                    finalmessage3.setText("Okay, but please try talking to your parents. You can come back and talk to me any time. Have a good day!");
                                    goHomeButton.setVisibility(View.VISIBLE);

                                    //Set two buttons invisible
                                    yesButton.setVisibility(View.INVISIBLE);
                                    noButton.setVisibility(View.INVISIBLE);
                                }

                            }, 2000);


                            break;

                    default:
                        break;

                }
            }
        });

        goHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatScreenActivity.this, HomeKidActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        startChat();
    }

    public void startChat()
    {
        //Show the following entities in the screen
        question1.setVisibility(View.VISIBLE);
        bot1.setVisibility(View.VISIBLE);
        yesButton.setVisibility(View.VISIBLE);
        noButton.setVisibility(View.VISIBLE);

        //Hide everything else
        typingAvi1.hide();
        typingAvi2.hide();
        typingAvi3.hide();
        question2.setVisibility(View.INVISIBLE);
        question3.setVisibility(View.INVISIBLE);
        answer1.setVisibility(View.INVISIBLE);
        answer2.setVisibility(View.INVISIBLE);
        answer3.setVisibility(View.INVISIBLE);
        bot2.setVisibility(View.INVISIBLE);
        bot3.setVisibility(View.INVISIBLE);
        bot4.setVisibility(View.INVISIBLE);
        finalmessage1.setVisibility(View.INVISIBLE);
        finalmessage2.setVisibility(View.INVISIBLE);
        finalmessage3.setVisibility(View.INVISIBLE);
        goHomeButton.setVisibility(View.INVISIBLE);
    }

    public void saveVideo()
    {

        Uri file = Uri.fromFile(new File(getIntent().getStringExtra("outputFile")));

        //Current date and time
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        fileName = getIntent().getStringExtra("kidId") + "-" + formatter.format(date) +".3gp";
        final StorageReference riversRef = mStorageRef.child("recordings").child(fileName);

        riversRef.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                downloadUrl = uri;
                                Toast.makeText(getApplicationContext(), "Taken Care of!", Toast.LENGTH_LONG).show();
                                PostAudioURIAsync postAudioURIAsync = new PostAudioURIAsync();
                                postAudioURIAsync.execute();
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });
    }

    private class PostAudioURIAsync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            postAudioURI(getIntent().getStringExtra("kidId"));
            return null;
        }

    }

    //Rest method to post the report
    public void postAudioURI(String kidId) {

        //    kidId = "5Cvk5aotND";
        URL url = null;
        HttpURLConnection conn = null;

        try {
            url = new URL("https://nobully-247.appspot.com/firebase");
            conn = (HttpURLConnection) url.openConnection(); //opening the connection
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);

            conn.setRequestMethod("POST"); //Connection method is set to POST

            conn.setDoOutput(true); //output to true
            conn.setRequestProperty("Content-Type", "application/json"); //headers


            //post data
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("kidID", kidId);
            jsonParam.put("filename", fileName);
            jsonParam.put("firebase_uri", downloadUrl);

            Log.i("JSON", jsonParam.toString());

            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
            //os.writeBytes(URLEncoder.encode(String.valueOf(jsonParam), "UTF-8"));
            os.writeBytes(jsonParam.toString());
            os.flush();
            os.close();

            Log.i("error", new Integer(conn.getResponseCode()).toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ChatScreenActivity.this, HomeKidActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

}

