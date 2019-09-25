package com.tony.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {
    public static final String EXTRA_ANSWER_IS_TRUE = "com.tony.android.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "com.tony.android.geoquiz.answer_shwon";
    private boolean mAnswerIsTrue;

    private TextView mAnswerTextView;
    private Button mShowAnswer;

    private void setExtraAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
        //putting extra to be passed back to main activity on whether user cheated or not
    }

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        //setting view to activity cheat layout
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        //getting answer extra from main activity
        setExtraAnswerShownResult(false);
        //passing that user did not cheat
        mAnswerTextView = (TextView)findViewById(R.id.answerTextView);
        mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAnswerIsTrue){
                    mAnswerTextView.setText(R.string.true_button);
                }else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                setExtraAnswerShownResult(true);
            }
            //when user presses show answer it shows the right answer and sets if user cheated to true
        });
    }
}
