package com.example.androidapp;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button falseButton;
    private Button trueButton;
    private ImageButton nextButton;
    private ImageButton previousButton;
    private TextView questionTextView;
    private int currentQuestionIndex = 0;

    private Question[] questionBank = new Question[] {
        new Question(R.string.question_declaration, true),
        new Question(R.string.question_declaration1, false),
        new Question(R.string.question_declaration2, true),
        new Question(R.string.question_declaration3, false),
        new Question(R.string.question_declaration4, true)
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        falseButton = findViewById(R.id.false_button);
        trueButton = findViewById(R.id.true_button);
        nextButton = findViewById(R.id.next_button);
        previousButton = findViewById(R.id.previous_button);
        questionTextView = findViewById(R.id.answer_text_view);



        falseButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        previousButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.false_button:
                checkAnswer(false);
                break;
            case R.id.true_button:
                checkAnswer(true);
                break;
            case R.id.next_button:
                currentQuestionIndex = (currentQuestionIndex + 1) % questionBank.length;
                updateQuestion();
                break;
            case R.id.previous_button:
                if (currentQuestionIndex > 0) {
                    currentQuestionIndex = (currentQuestionIndex - 1) % questionBank.length;
                } else {
                    currentQuestionIndex = questionBank.length - 1;
                }
                updateQuestion();
                break;
        }

    }

    private void updateQuestion() {
        Log.d("Current", "onClick: " + currentQuestionIndex);
        questionTextView.setText(questionBank[currentQuestionIndex].getAnswerResId());
    }

    private void checkAnswer(boolean userChoice) {
        boolean answerIsTrue = questionBank[currentQuestionIndex].getAnswerTrue();
        int toastMessageId;

        if (userChoice == answerIsTrue) {
            toastMessageId = R.string.correct_answer;
        } else {
            toastMessageId = R.string.wrong_answer;
        }

        Toast.makeText(MainActivity.this, toastMessageId, Toast.LENGTH_SHORT).show();
    }
}