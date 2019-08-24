package com.example.protectfrombullying;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class UserTypeActivity extends AppCompatActivity {

    //Image
    private ImageView imageView_Parent;
    private ImageView imageView_Student;

    private Animation forImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type);

        imageView_Parent = (ImageView) findViewById(R.id.imageView_Parent);
        imageView_Student = (ImageView) findViewById(R.id.imageView_Student);
        forImage = AnimationUtils.loadAnimation(this, R.anim.usertype);

        imageView_Parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserTypeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        imageView_Student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserTypeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        imageView_Student.startAnimation(forImage);
        imageView_Parent.startAnimation(forImage);
    }
}
