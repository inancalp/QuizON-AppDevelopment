package com.example.sqliterecyclerviewtemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddQuizActivity extends AppCompatActivity {

    EditText quiz_title_input;
    Button add_button;
    long quiz_id;
    String quiz_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quiz);
        setTitle("Add Quiz Activity");
        // initialize the components
        quiz_title_input = findViewById(R.id.quiz_title_input);
        add_button = findViewById(R.id.add_button);


        // call the onClick event listener for the "add_button"
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db = new DatabaseHelper(AddQuizActivity.this);
//                quiz_id = db.assignQuizId();
                quiz_title = quiz_title_input.getText().toString();

                if(quiz_title.isEmpty())
                {
                    Toast.makeText(AddQuizActivity.this, "Error: Quiz Title is Empty.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d("AddQuizActivity", "quiz_id: " + quiz_id);

                if(db.quizTitleInUse(quiz_title))
                {
                    Toast.makeText(AddQuizActivity.this, "Error: Quiz Title is already In Use.", Toast.LENGTH_SHORT).show();
                    return;
                }


                Intent intent = new Intent(AddQuizActivity.this, AddQuestionActivity.class);
//                intent.putExtra("QUIZ_ID", quiz_id);
                intent.putExtra("QUIZ_TITLE", quiz_title);

                startActivity(intent);
            }
        });
    }


}