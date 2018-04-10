package com.deipayan.android.quizoccer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {


    Button mTrueButton;
    Button mFalseButton;
    TextView mQuestionView;
    ProgressBar mProgressBar;
    TextView mScoreView;

    private int mIndex;
    private int mQuestion;
    private int mScore;
    private boolean checker = false;


    private TrueFalse[] mQuestionBank = {
            new TrueFalse(R.string.Question_1, true),
            new TrueFalse(R.string.Question_2, false),
            new TrueFalse(R.string.Question_3, false),
            new TrueFalse(R.string.Question_4, false),
            new TrueFalse(R.string.Question_5, true),
            new TrueFalse(R.string.Question_6, true),
            new TrueFalse(R.string.Question_7, true),
            new TrueFalse(R.string.Question_8, false),
            new TrueFalse(R.string.Question_9, false),
            new TrueFalse(R.string.Question_10, true),
            new TrueFalse(R.string.Question_11, false),
            new TrueFalse(R.string.Question_12, false),
            new TrueFalse(R.string.Question_13, false),
            new TrueFalse(R.string.Question_14, true),
            new TrueFalse(R.string.Question_15, false)
    };

    private final int progress_increment = 100 / mQuestionBank.length + 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        mProgressBar = findViewById(R.id.progress_bar);
        mQuestionView = findViewById(R.id.question_view);
        mScoreView = findViewById(R.id.score);

        mQuestion = mQuestionBank[mIndex].getQuestionID();
        mQuestionView.setText(mQuestion);


        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);

                updateQuestion();


            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                updateQuestion();

            }
        });


    }

    private void updateQuestion() {
        mIndex = (mIndex + 1) % 15;
        if (mIndex == 0) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Game Over");
            alert.setCancelable(false);
            alert.setMessage("You have scored " + mScore + " Points!!");
            alert.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            alert.show();

        }


        mQuestionView.setText(mQuestionBank[mIndex].getQuestionID());
        mProgressBar.incrementProgressBy(progress_increment);
        mScoreView.setText("Score: " + mScore + "/15");

    }

    private void checkAnswer(boolean userSelection) {
        boolean correct = mQuestionBank[mIndex].isAnswer();
        if (correct == userSelection) {
            mScore++;
        } else {
            Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
        }


    }

}
