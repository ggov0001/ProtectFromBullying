package com.example.protectfrombullying;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class QuizResultActivity extends AppCompatActivity {

    //TextView declare
    private TextView percentageTextView;
    private TextView bullySeverity;
    private TextView quizSuggestion;

    private ImageView warningImage;

    private StringBuilder suggestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        //Initialize
        percentageTextView = (TextView) findViewById(R.id.textView_quizPercentage);
        bullySeverity = (TextView) findViewById(R.id.textView_quizSeverity);
        quizSuggestion = (TextView) findViewById(R.id.textView_quizSuggestion);
        warningImage = (ImageView) findViewById(R.id.imageView_warningImage);

        Intent intent = getIntent();
        Float scoreForTheQuiz = intent.getFloatExtra("Result",0);
        percentageTextView.setText(scoreForTheQuiz + "%");

        if(scoreForTheQuiz == 0.0)
        {
            bullySeverity.setText("NO");
            suggestion = new StringBuilder();
            suggestion.append("* Please pay attention to changes in your child's behavior");
            suggestion.append("\n");
            suggestion.append("* It is always necessary to prevent bullying by developing a good communication with your kid.");
            quizSuggestion.setText(suggestion.toString());
        }
        else if(scoreForTheQuiz > 0 && scoreForTheQuiz <= 20)
        {
            bullySeverity.setText("LITTLE");
            bullySeverity.setTextColor(Color.parseColor("#fdf542"));
            suggestion = new StringBuilder();
            suggestion.append("* Please pay attention to changes in your child's behavior and re-attend the quiz when necessary.");
            suggestion.append("\n");
            suggestion.append("* Make it rewarding for children to develop the habit of telling what happens at school each day.");
            quizSuggestion.setText(suggestion.toString());
        }
        else if(scoreForTheQuiz > 20 && scoreForTheQuiz <= 40)
        {
            bullySeverity.setText("MILD");
            bullySeverity.setTextColor(Color.parseColor("#fbd808"));
            suggestion = new StringBuilder();
            suggestion.append("* Please try to confirm what's bothering your kid with some leading questions in a calm, cheerful way.");
            suggestion.append("\n");
            suggestion.append("* Start with 'How was your school today?'.");
            quizSuggestion.setText(suggestion.toString());
        }
        else if(scoreForTheQuiz > 40 && scoreForTheQuiz <= 60)
        {
            bullySeverity.setText("MODERATE");
            bullySeverity.setTextColor(Color.parseColor("#ff9005"));
            suggestion = new StringBuilder();
            suggestion.append("* Please stay CALM but you need to talk to your kid!!");
            suggestion.append("\n");
            suggestion.append("* Ask your child a couple of questions with curiosity rather than anxiety like 'I saw this happen/heard about this happening. It sounded like it might be unpleasant for you. Can you tell me more about it?'");
            suggestion.append("\n");
            suggestion.append("* If you feel hard try to talk to your kids' school, friends.");
            quizSuggestion.setText(suggestion.toString());
        }
        else if(scoreForTheQuiz > 60 && scoreForTheQuiz <= 80)
        {
            bullySeverity.setText("STRONG");
            bullySeverity.setTextColor(Color.parseColor("#f9530b"));
            suggestion = new StringBuilder();
            suggestion.append("* Please stay CALM but you need to talk to your kid!!");
            suggestion.append("\n");
            suggestion.append("* Ask your child a couple of questions with curiosity rather than anxiety like 'I saw this happen/heard about this happening. It sounded like it might be unpleasant for you. Can you tell me more about it?'");
            suggestion.append("\n");
            suggestion.append("* If you feel hard try to talk to your kids' school, friends.");
            quizSuggestion.setText(suggestion.toString());
        }
        else if(scoreForTheQuiz > 80 && scoreForTheQuiz <= 100)
        {
            bullySeverity.setText("VERY STRONG");
            bullySeverity.setTextColor(Color.parseColor("#ff0000"));
            warningImage.setImageResource(R.drawable.iconswarning);
            suggestion = new StringBuilder();
            suggestion.append("* Please stay CALM but you need to talk to your kid!!");
            suggestion.append("\n");
            suggestion.append("* Ask your child a couple of questions with curiosity rather than anxiety like 'I saw this happen/heard about this happening. It sounded like it might be unpleasant for you. Can you tell me more about it?'");
            suggestion.append("\n");
            suggestion.append("* If you feel hard try to talk to your kids' school, friends.");
            quizSuggestion.setText(suggestion.toString());
        }
    }
}
