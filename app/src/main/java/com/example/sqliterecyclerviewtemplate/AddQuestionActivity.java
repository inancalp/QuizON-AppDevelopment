package com.example.sqliterecyclerviewtemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


public class AddQuestionActivity extends AppCompatActivity {

    TextView quiz_title_textview;
    EditText question_view, answer_a_view, answer_b_view, answer_c_view, answer_d_view;
    Button add_question_button, store_quiz_button;
    long quiz_id;

    ArrayList<Question> questions = new ArrayList<Question>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        Intent intent = getIntent();
        quiz_id = intent.getLongExtra("QUIZ_ID", -1);
        Log.d("AddQuestion", "quiz_id = " + quiz_id);

        quiz_title_textview = findViewById(R.id.quiz_title_textview);
        quiz_title_textview.setText("Quiz with ID: " + quiz_id);

        question_view = findViewById(R.id.question_view);
        answer_a_view = findViewById(R.id.answer_a_view);
        answer_b_view = findViewById(R.id.answer_b_view);
        answer_c_view = findViewById(R.id.answer_c_view);
        answer_d_view = findViewById(R.id.answer_d_view);
        add_question_button = findViewById(R.id.add_question_button);
        store_quiz_button = findViewById(R.id.store_quiz_button);

        add_question_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                questions.add(new Question(
                        question_view.getText().toString(),
                        answer_a_view.getText().toString(),
                        answer_b_view.getText().toString(),
                        answer_c_view.getText().toString(),
                        answer_d_view.getText().toString()
                ));

                question_view.setText("");
                answer_a_view.setText("");
                answer_b_view.setText("");
                answer_c_view.setText("");
                answer_d_view.setText("");
            }
        });

        store_quiz_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                for(Question question : questions)
//                {
//                    Log.d("Questions", question.getQuestion().toString());
//                    Log.d("Questions", question.getAnswerA().toString());
//                    Log.d("Questions", question.getAnswerB().toString());
//                    Log.d("Questions", question.getAnswerC().toString());
//                    Log.d("Questions", question.getAnswerD().toString());
//                }

                MyDatabaseHelper db = new MyDatabaseHelper(AddQuestionActivity.this);
                db.addQuestions(questions, quiz_id);
            }
        });

    }
}