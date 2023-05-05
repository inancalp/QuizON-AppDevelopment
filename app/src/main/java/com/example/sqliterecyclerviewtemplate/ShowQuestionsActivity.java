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


    private DatabaseHelper db;
//  private   ArrayList question_id, question, answer_a, answer_b, answer_c, answer_d, correct_answer;
    private ArrayList<Question> questions = new ArrayList<>();
    private long quiz_id;
    private String quiz_title;
    private RecyclerView questions_recyclerview;
    private QuestionsAdapter questionsAdapter;
    private TextView quiz_title_view;


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
        db = new DatabaseHelper(ShowQuestionsActivity.this);

        storeQuestions();

//        questionsAdapter = new QuestionsAdapter(ShowQuestionsActivity.this, question_id, question, answer_a, answer_b, answer_c, answer_d, correct_answer);
        questionsAdapter = new QuestionsAdapter(ShowQuestionsActivity.this, questions, quiz_id);
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
                questions.add(new Question(
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7)
                ));
            }
        }
    }
}