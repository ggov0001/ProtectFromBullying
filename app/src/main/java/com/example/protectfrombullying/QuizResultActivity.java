package com.example.protectfrombullying;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class QuizResultActivity extends AppCompatActivity {

    private TextView percentageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        percentageTextView = (TextView) findViewById(R.id.textView_quizPercentage);

        Intent intent = getIntent();
        Float scoreForTheQuiz = intent.getFloatExtra("Result",0);
        percentageTextView.setText(scoreForTheQuiz + "%");

    }
}
