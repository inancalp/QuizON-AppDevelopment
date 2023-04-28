package com.example.sqliterecyclerviewtemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddQuizActivity extends AppCompatActivity {

    EditText quiz_title_input;
    Button add_button;
    long quiz_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quiz);

        // initialize the components
        quiz_title_input = findViewById(R.id.quiz_title_input);
        add_button = findViewById(R.id.add_button);


        // call the onClick event listener for the "add_button"
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper db = new MyDatabaseHelper(AddQuizActivity.this);
                quiz_id = db.addQuiz(quiz_title_input.getText().toString().trim());
                Log.d("quiz_id", "quiz_id = " + quiz_id);
//                db.addQuiz(quiz_title_input.getText().toString().trim(),
//                        author_input.getText().toString().trim(),
//                        Integer.valueOf(pages_input.getText().toString().trim()));

                Intent intent = new Intent(AddQuizActivity.this, AddQuestionActivity.class);
                intent.putExtra("QUIZ_ID", quiz_id);
                startActivity(intent);
            }
        });
    }


}