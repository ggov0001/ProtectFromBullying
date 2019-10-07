package com.example.protectfrombullying;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;

public class TreeHoleActivity extends AppCompatActivity {

    private Button play, stop, record;
    private MediaRecorder myAudioRecorder;
    private String outputFile;
    private StorageReference mStorageRef;
    private Uri downloadUrl;

    private VideoView animationVideoStart;
    private VideoView animationVideoEnd;
    private ImageView animationImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_hole);

        FirebaseApp.initializeApp(TreeHoleActivity.this);
        mStorageRef = FirebaseStorage.getInstance().getReference();

        //play = (Button) findViewById(R.id.play);
        stop = (Button) findViewById(R.id.stop);
        record = (Button) findViewById(R.id.record);
        animationVideoStart = (VideoView) findViewById(R.id.videoView_treeHoleStart);
        animationVideoEnd = (VideoView) findViewById(R.id.videoView_treeHoleEnd);
        animationImage = (ImageView) findViewById(R.id.imageView_treeHole);

        stop.setEnabled(false);
        animationImage.setVisibility(View.VISIBLE);

     //   play.setEnabled(false);
        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording1.3gp";

        myAudioRecorder = new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myAudioRecorder.setOutputFile(outputFile);

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    animationImage.setVisibility(View.INVISIBLE);
                    animationVideoEnd.setVisibility(View.INVISIBLE);
                    animationVideoStart.setVisibility(View.VISIBLE);

                    myAudioRecorder.prepare();
                    myAudioRecorder.start();
                } catch (IllegalStateException ise) {
                        ise.printStackTrace();
                } catch (IOException ioe) {
                        ioe.printStackTrace();
                }

                Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.first);
                animationVideoStart.setVideoURI(uri);
                animationVideoStart.start();
                animationVideoStart.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.setLooping(true);
                    }
                });

                record.setEnabled(false);
                stop.setEnabled(true);
                Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_LONG).show();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationImage.setVisibility(View.INVISIBLE);
                myAudioRecorder.stop();
                myAudioRecorder.release();
                myAudioRecorder = null;
                record.setEnabled(true);
                stop.setEnabled(false);
//                play.setEnabled(true);

                animationVideoStart.stopPlayback();
                goToChatScreen();
            }
        });

//        play.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final MediaPlayer mediaPlayer = new MediaPlayer();
//                try {
//                    mediaPlayer.setDataSource(String.valueOf(downloadUrl));
//                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                        @Override
//                        public void onPrepared(MediaPlayer mp) {
//                            mediaPlayer.start();
//                        }
//                    });
//
//                    mediaPlayer.prepare();
////                    mediaPlayer.start();
//                    Toast.makeText(getApplicationContext(), "Playing Audio", Toast.LENGTH_LONG).show();
//                } catch (Exception e) {
//
//                }
//            }
//        });


    }

    public void goToChatScreen()
    {

        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.second);
        animationVideoEnd.setVisibility(View.VISIBLE);
        animationVideoEnd.setVideoURI(uri);
        animationVideoEnd.start();
        animationVideoEnd.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                animationVideoEnd.stopPlayback();
                Toast.makeText(getApplicationContext(), "Audio Recorded successfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(TreeHoleActivity.this, ChatScreenActivity.class);
                intent.putExtra("outputFile", outputFile);
                intent.putExtra("kidId", getIntent().getStringExtra("kidId"));
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

    }

}
