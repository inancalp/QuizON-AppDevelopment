package com.example.sqliterecyclerviewtemplate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowQuestionsActivity extends AppCompatActivity {


    MyDatabaseHelper db;
    ArrayList question_id, question, answer_a, answer_b, answer_c, answer_d;
    long quiz_id;
    String quiz_title;
    RecyclerView questions_recyclerview;
    QuestionsAdapter questionsAdapter;
    TextView quiz_title_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_questions);

        Intent intent = getIntent();


        // Change can be done to simplify things?
        quiz_id = Long.parseLong(intent.getStringExtra("QUIZ_ID"));
        quiz_title = intent.getStringExtra("QUIZ_TITLE");

        quiz_title_view = findViewById(R.id.quiz_title_view);
        quiz_title_view.setText(quiz_title);

        Log.d("ShowQuestionsActivityLog", "quiz_id: " + quiz_id);
        Log.d("ShowQuestionsActivityLog", "quiz_title: " + quiz_title);

        questions_recyclerview = findViewById(R.id.questions_recyclerview);
        db = new MyDatabaseHelper(ShowQuestionsActivity.this);
        question_id = new ArrayList<>();
        question = new ArrayList<>();
        answer_a = new ArrayList<>();
        answer_b = new ArrayList<>();
        answer_c = new ArrayList<>();
        answer_d = new ArrayList<>();

        storeQuestions();

        questionsAdapter = new QuestionsAdapter(ShowQuestionsActivity.this, question_id, question, answer_a, answer_b, answer_c, answer_d);
        questions_recyclerview.setAdapter(questionsAdapter);
        questions_recyclerview.setLayoutManager(new LinearLayoutManager(ShowQuestionsActivity.this));
    }

    void storeQuestions()
    {
        Cursor cursor = db.readData_questionsTable(quiz_id);
        if(cursor.getCount() == 0)
        {
            Toast.makeText(this, "No Data to retrieve for the Quiz.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while(cursor.moveToNext())
            {
                question_id.add(cursor.getString(0));
                question.add(cursor.getString(2));
                answer_a.add(cursor.getString(3));
                answer_b.add(cursor.getString(4));
                answer_c.add(cursor.getString(5));
                answer_d.add(cursor.getString(6));
            }
        }
    }
}