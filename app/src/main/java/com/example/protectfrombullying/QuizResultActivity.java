package com.example.protectfrombullying;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class QuizResultActivity extends AppCompatActivity {

    //TextView declare
    private TextView percentageTextView;
    private TextView bullySeverity;
    private TextView quizSuggestion;

    private ImageView warningImage;
    private Button waysToTackleButton;
    private Button communicateWithKids;
    private Button retakeQuiz;

    //For suggestion
    private StringBuilder suggestion;

    //Bottom navigation
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(QuizResultActivity.this, HomeParentActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_reports:
                    Intent dashboardIntent = new Intent(QuizResultActivity.this, ReportsActivity.class);
                    startActivity(dashboardIntent);
                    return true;
                case R.id.navigation_yourkids:
                    Intent yourKidsIntent = new Intent(QuizResultActivity.this, YourKidsActivity.class);
                    startActivity(yourKidsIntent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                case R.id.navigation_information:
                    Intent infromationIntent = new Intent(QuizResultActivity.this, WaysToTackleActivity.class);
                    startActivity(infromationIntent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
            }
            return false;
        }
    };


    //Back
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(QuizResultActivity.this, HomeParentActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Initialize
        percentageTextView = (TextView) findViewById(R.id.textView_quizPercentage);
        bullySeverity = (TextView) findViewById(R.id.textView_quizSeverity);
        quizSuggestion = (TextView) findViewById(R.id.textView_quizSuggestion);
        warningImage = (ImageView) findViewById(R.id.imageView_warningImage);

        waysToTackleButton = (Button) findViewById(R.id.button_iconwaystotackle);
        communicateWithKids = (Button) findViewById(R.id.button_iconCommunicateWithKids);
        retakeQuiz = (Button) findViewById(R.id.button_retakeQuiz);

        //Get the score from ParentQuizActivity
        Intent intent = getIntent();
        Float scoreForTheQuiz = intent.getFloatExtra("Result",0);
        percentageTextView.setText(scoreForTheQuiz + "%");

        //Logic
        if(scoreForTheQuiz == 0.0)
        {
            bullySeverity.setText("NO");
            suggestion = new StringBuilder();
            suggestion.append("1. Please pay attention to changes in your child's behavior");
            suggestion.append("\n");
            suggestion.append("2. It is always necessary to prevent bullying by developing a good communication with your kid.");
            quizSuggestion.setText(suggestion.toString());
        }
        else if(scoreForTheQuiz > 0 && scoreForTheQuiz <= 20)
        {
            bullySeverity.setText("LITTLE");
            bullySeverity.setTextColor(Color.parseColor("#fdf542"));
            suggestion = new StringBuilder();
            suggestion.append("1. Please pay attention to changes in your child's behavior and re-attend the quiz when necessary.");
            suggestion.append("\n");
            suggestion.append("2. Make it rewarding for children to develop the habit of telling what happens at school each day.");
            quizSuggestion.setText(suggestion.toString());
        }
        else if(scoreForTheQuiz > 20 && scoreForTheQuiz <= 40)
        {
            bullySeverity.setText("MILD");
            bullySeverity.setTextColor(Color.parseColor("#fbd808"));
            suggestion = new StringBuilder();
            suggestion.append("1. Please try to confirm what's bothering your kid with some leading questions in a calm, cheerful way.");
            suggestion.append("\n");
            suggestion.append("2. Start with 'How was your school today?'.");
            quizSuggestion.setText(suggestion.toString());
        }
        else if(scoreForTheQuiz > 40 && scoreForTheQuiz <= 60)
        {
            bullySeverity.setText("MODERATE");
            bullySeverity.setTextColor(Color.parseColor("#ff9005"));
            suggestion = new StringBuilder();
            suggestion.append("1. Please stay CALM but you need to talk to your kid!!");
            suggestion.append("\n");
            suggestion.append("2. Ask your child a couple of questions with curiosity rather than anxiety like 'I saw this happen/heard about this happening. It sounded like it might be unpleasant for you. Can you tell me more about it?'");
            suggestion.append("\n");
            suggestion.append("3. If you feel hard try to talk to your kids' school, friends.");
            quizSuggestion.setText(suggestion.toString());
        }
        else if(scoreForTheQuiz > 60 && scoreForTheQuiz <= 80)
        {
            bullySeverity.setText("STRONG");
            bullySeverity.setTextColor(Color.parseColor("#f9530b"));
            suggestion = new StringBuilder();
            suggestion.append("1. Please stay CALM but you need to talk to your kid!!");
            suggestion.append("\n");
            suggestion.append("2. Ask your child a couple of questions with curiosity rather than anxiety like 'I saw this happen/heard about this happening. It sounded like it might be unpleasant for you. Can you tell me more about it?'");
            suggestion.append("\n");
            suggestion.append("3. If you feel hard try to talk to your kids' school, friends.");
            quizSuggestion.setText(suggestion.toString());
        }
        else if(scoreForTheQuiz > 80 && scoreForTheQuiz <= 100)
        {
            bullySeverity.setText("VERY STRONG");
            bullySeverity.setTextColor(Color.parseColor("#ff0000"));
            warningImage.setImageResource(R.drawable.iconswarning);
            suggestion = new StringBuilder();
            suggestion.append("1. Please stay CALM but you need to talk to your kid!!");
            suggestion.append("\n");
            suggestion.append("2. Ask your child a couple of questions with curiosity rather than anxiety like 'I saw this happen/heard about this happening. It sounded like it might be unpleasant for you. Can you tell me more about it?'");
            suggestion.append("\n");
            suggestion.append("3. If you feel hard try to talk to your kids' school, friends.");
            quizSuggestion.setText(suggestion.toString());
        }

        waysToTackleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizResultActivity.this, WaysToTackleActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });


        communicateWithKids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizResultActivity.this, ParentTalkActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        retakeQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizResultActivity.this, ParentQuizActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }
}
