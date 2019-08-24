package com.example.protectfrombullying;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ParentQuizActivity extends AppCompatActivity {

    //Text View that displays question.
    private TextView quizQuestion;
    private TextView questionNumberDisplay;

    //Buttons - the answers
    private Button never;
    private Button rarely;
    private Button sometimes;
    private Button often;
    private Button veryoften;

    //Quiz questions
    private String questionsForQuiz[] = {
            "Is your kid's things often missing these days?",
            "Is your kid's demands for money increasing and you have caught them stealing money from your wallets/cash lockers?",
            "When asked about school, the answers aren't specific but just one word a reply like \'OK\', \'NO\' etc.?",
            "Is your kid's sleep quality poor, he reports of frequent nightmares or is waking up in the middle of the night?",
            "Is your kid Fatigue, tired, shows no spirit?",
            "How often does your kid get unexplained headache, stomachache, vomiting, poor appetite, body wasting?",
            "How often is your kid inexplicably irritable?",
            "Does your kid avoids showing his/her body to you?",
            "Is your kid washing his clothes, shoes without your knowledge?",
            "Is your kid's grades falling?"
    };

    private int questionNumber = 0;
    private float scoreForTheQuiz = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_quiz);

        //Initializing
        quizQuestion = (TextView) findViewById(R.id.textView_quizQuestion);
        questionNumberDisplay =(TextView) findViewById(R.id.textView_numberAnswered);

        never = (Button) findViewById(R.id.button_never);
        rarely = (Button) findViewById(R.id.button_rarely);
        sometimes = (Button) findViewById(R.id.button_sometimes);
        often = (Button) findViewById(R.id.button_often);
        veryoften = (Button) findViewById(R.id.button_veryoften);

        //DUMMY DELETE
        final TextView score = (TextView) findViewById(R.id.textView_score);



        if(questionNumber == 0)
        {
            askQuestion(questionNumber);
        }

        //Button never on click
        never.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questionNumber != 9)
                {
                    scoreForTheQuiz += 0;
                    questionNumber += 1;
                    askQuestion(questionNumber);
                }
                else
                {
                    scoreForTheQuiz += 0;
                    score.setText(scoreForTheQuiz + "%");
                }
            }
        });

        //Button rarely on click
        rarely.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questionNumber != 9)
                {
                    scoreForTheQuiz += 2.5;
                    questionNumber += 1;
                    askQuestion(questionNumber);
                }
                else
                {
                    scoreForTheQuiz += 2.5;
                    score.setText(scoreForTheQuiz + "%");
                }
            }
        });

        //Button sometimes on click
        sometimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questionNumber != 9)
                {
                    scoreForTheQuiz += 5;
                    questionNumber += 1;
                    askQuestion(questionNumber);
                }
                else
                {
                    scoreForTheQuiz += 5;
                    score.setText(scoreForTheQuiz + "%");
                }
            }
        });

        //Button often on click
        often.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questionNumber != 9)
                {
                    scoreForTheQuiz += 7.5;
                    questionNumber += 1;
                    askQuestion(questionNumber);
                }
                else
                {
                    scoreForTheQuiz += 7.5;
                    score.setText(scoreForTheQuiz + "%");
                }
            }
        });

        //Button very often on click
        veryoften.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questionNumber != 9)
                {
                    scoreForTheQuiz += 10;
                    questionNumber += 1;
                    askQuestion(questionNumber);
                }
                else
                {
                    scoreForTheQuiz += 10;
                    score.setText(scoreForTheQuiz + "%");
                }
            }
        });

    }

    public void askQuestion(int questionNumber)
    {
        questionNumberDisplay.setText(String.valueOf(questionNumber));
        quizQuestion.setText(questionsForQuiz[questionNumber]);
    }
}
