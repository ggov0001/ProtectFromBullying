package com.example.protectfrombullying;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ParentQuizFragment extends Fragment {

    //Fragemnt View
    private View parentQuizScreen;

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
    private int scoreForTheQuiz = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Initializing
        parentQuizScreen = inflater.inflate(R.layout.fragment_parent_quiz, container, false);

        quizQuestion = (TextView) parentQuizScreen.findViewById(R.id.textView_quizQuestion);
        questionNumberDisplay =(TextView) parentQuizScreen.findViewById(R.id.textView_numberAnswered);

        never = (Button) parentQuizScreen.findViewById(R.id.button_never);
        rarely = (Button) parentQuizScreen.findViewById(R.id.button_rarely);
        sometimes = (Button) parentQuizScreen.findViewById(R.id.button_sometimes);
        often = (Button) parentQuizScreen.findViewById(R.id.button_often);
        veryoften = (Button) parentQuizScreen.findViewById(R.id.button_veryoften);

        //DUMMY DELETE
        final TextView score = (TextView) parentQuizScreen.findViewById(R.id.textView_score);

        //if(questionNumber == 0)
        //{
            askQuestion(questionNumber);
        //}

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

        return inflater.inflate(R.layout.fragment_parent_quiz, null);
    }

    private void askQuestion(int questionNumber)
    {
        questionNumberDisplay.setText(questionNumber);
        quizQuestion.setText(questionsForQuiz[questionNumber]);
    }
}
