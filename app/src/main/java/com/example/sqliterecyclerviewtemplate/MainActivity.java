package com.example.sqliterecyclerviewtemplate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    //variables goes out onCreate method.
    RecyclerView my_recyclerview;
    CustomAdapter my_customAdapter;
    FloatingActionButton add_quiz_button, edit_text_button;
    MyDatabaseHelper db_quiz, db_welcome_text;
    ArrayList<String> quiz_id, quiz_title;
    TextView welcome_textview;
    String welcome_text;
    Integer EDIT_WELCOME_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_recyclerview = findViewById(R.id.questions_recyclerview);
        add_quiz_button = findViewById(R.id.add_quiz_button);
        edit_text_button = findViewById(R.id.edit_text_button);
        welcome_textview = findViewById(R.id.welcome_textview);

        edit_text_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                welcome_text = welcome_textview.getText().toString();
                Intent intent = new Intent(MainActivity.this, EditWelcomeTextActivity.class);
                intent.putExtra("WELCOME_TEXT", welcome_text);
                startActivity(intent);
            }
        });


        // new onclickListener will auto-add the onClick method onto the listener.
//        add_quiz_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // will change the activity to AddActivity
//                Intent intent = new Intent(MainActivity.this, AddQuizActivity.class);
//                startActivity(intent);
//            }
//        });

        add_quiz_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // will change the activity to AddActivity
                Intent intent = new Intent(MainActivity.this, AddQuizActivity.class);
                startActivity(intent);
            }
        });



        // part-3
        db_quiz = new MyDatabaseHelper(MainActivity.this);
        quiz_id = new ArrayList<>();
        quiz_title = new ArrayList<>();
        storeDataInArray();


        // WELCOME TEXT
        retrieveWelcomeText();
        welcome_textview.setText(welcome_text);



        // storeDataInArray method is important to call before the customAdapter.
        my_customAdapter = new CustomAdapter(MainActivity.this, quiz_id, quiz_title);
        my_recyclerview.setAdapter(my_customAdapter);

        my_recyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == EDIT_WELCOME_REQUEST_CODE)
//        {
//            if (resultCode == RESULT_OK)
//            {
//                String result_welcome_text = data.getStringExtra("WELCOME_TEXT");
//                if(result_welcome_text != "")
//                {
//                    welcome_textview.setText(result_welcome_text);
//                }
//            }
//        }
//    }

    void storeDataInArray()
    {
        Cursor cursor = db_quiz.readAllData_quizTable();
        
        if(cursor.getCount() == 0)
        {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while(cursor.moveToNext())
            {
                // i = 0 first col, i = 1 second col ...
                quiz_id.add(cursor.getString(0));
                quiz_title.add(cursor.getString(1));
            }
        }

    }

    void retrieveWelcomeText()
    {
        db_welcome_text = new MyDatabaseHelper(MainActivity.this);
        Cursor cursor = db_welcome_text.readAllData_welcomeText();

        if(cursor.getCount() == 0)
        {
            Toast.makeText(this, "No data in WELCOME_TEXT_TABLE.", Toast.LENGTH_SHORT).show();
            Log.d("WELCOME_TEXT_TABLE", "No data in WELCOME_TEXT_TABLE.");
        }
        else
        {
            Toast.makeText(this, "THERE IS DATA in WELCOME_TEXT_TABLE.", Toast.LENGTH_SHORT).show();
            cursor.moveToFirst();
            welcome_text = cursor.getString(1);
            Toast.makeText(this, welcome_text, Toast.LENGTH_SHORT).show();
        }
    }
}