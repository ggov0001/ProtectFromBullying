package com.example.protectfrombullying;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    //button selected color
    private boolean selected = true;

    //Quiz questions
    private String questionsForQuiz[] = {
            "Is your kid losing his things these days?",
            "Is your kid's demand for money increasing and you have caught them stealing money from your wallet/cash lockers?",
            "When asked about school, the answers aren't specific but just one word replies like \'OK\', \'NO\' etc.?",
            "Is your kid sleeping properly? Does he report frequent nightmares or is waking up in the middle of the night?",
            "Is your kid tired, shows no spirit?",
            "How often does your kid suffer from unexplained headache, stomachache, vomiting, poor appetite, body wasting?",
            "How often is your kid inexplicably irritable?",
            "Is your child nervous or neurotic when receiving text messages or Internet notifications?",
            "Is your kid hiding all phone calls and online activities from you?",
            "Is your kid's academic performance degrading drastically?"
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



        final Intent sendQuizResult = new Intent(ParentQuizActivity.this, QuizResultActivity.class);


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
                    sendQuizResult.putExtra("Result", scoreForTheQuiz);
                    startActivity(sendQuizResult);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
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
                    sendQuizResult.putExtra("Result", scoreForTheQuiz);
                    startActivity(sendQuizResult);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
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
                    sendQuizResult.putExtra("Result", scoreForTheQuiz);
                    startActivity(sendQuizResult);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
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
                    sendQuizResult.putExtra("Result", scoreForTheQuiz);
                    startActivity(sendQuizResult);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
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
                    sendQuizResult.putExtra("Result", scoreForTheQuiz);
                    startActivity(sendQuizResult);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
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
