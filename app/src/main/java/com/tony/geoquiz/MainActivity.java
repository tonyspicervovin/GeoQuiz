package com.tony.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private Button mCheatButton;

    //declaring variables

    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_oceans,true),
            new TrueFalse(R.string.question_mideast,false),
            new TrueFalse(R.string.question_africa,false),
            new TrueFalse(R.string.question_americas,true),
            new TrueFalse(R.string.question_asia,true),
    };
    // Creating list of questions with answer
    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getmQuestion();
        mQuestionTextView.setText(question);
    }
    // setting the question to the new position provided
    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].ismTrueQuestion();

        int messageResId = 0;
        if (mIsCheater){
            messageResId = R.string.judgement_toast;
        }else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }
    //checking if answer is true
    private int mCurrentIndex = 0;

    private boolean mIsCheater;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (data == null){
            return;
        }
        mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);

        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);


        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
            //if user is right pass true to our check answer function
        });
        mFalseButton = (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               checkAnswer(false);

            }
        // if user is wrong pass false to checkanswer function
        });



        mNextButton = (Button)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mCurrentIndex = (mCurrentIndex+1) % mQuestionBank.length;
                mIsCheater = false;
                updateQuestion();
            }
        });


     mCheatButton = (Button)findViewById(R.id.cheat_button);
     mCheatButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent i = new Intent(MainActivity.this, CheatActivity.class);
             boolean answerIsTrue = mQuestionBank[mCurrentIndex].ismTrueQuestion();
             i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answerIsTrue);
             startActivityForResult(i, 0);
         }
     });
        updateQuestion();
    }
    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
    //logs when events happen
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.activity_quiz, menu);
        return true;
    }
}
