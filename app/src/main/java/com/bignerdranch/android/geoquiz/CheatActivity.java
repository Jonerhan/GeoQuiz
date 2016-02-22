package com.bignerdranch.android.geoquiz;


import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {


    public static final String EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown";
    public static final String KEY_CHEATER = "cheater";

    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private Button mShowAnswer;
    private Boolean mIsCheater;

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        if (savedInstanceState != null) {
            setAnswerShownResult(savedInstanceState.getBoolean(KEY_CHEATER, false));
            mIsCheater = savedInstanceState.getBoolean(KEY_CHEATER);
            mAnswerIsTrue = savedInstanceState.getBoolean(EXTRA_ANSWER_IS_TRUE, false);
            mAnswerTextView = (TextView)findViewById(R.id.answer_text_view);
            if ((mAnswerIsTrue == true) && (mIsCheater == true)) {
                mAnswerTextView.setText(R.string.true_button);
            } else if ((mAnswerIsTrue == false) && (mIsCheater == true)) {
                mAnswerTextView.setText(R.string.false_button);
            }

        } else {
            mIsCheater = false;
            setAnswerShownResult(false);
        }


        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        mAnswerTextView = (TextView)findViewById(R.id.answer_text_view);
        mShowAnswer = (Button) findViewById(R.id.show_answer_button);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);// the answer was seen
                mIsCheater = true;
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(KEY_CHEATER, mIsCheater);// save the cheat status of the user onSaveInstanceState issuance
        savedInstanceState.putBoolean(EXTRA_ANSWER_IS_TRUE, mAnswerIsTrue);// get whether ? was True/false onSaveInstanceState issuance
    }

}