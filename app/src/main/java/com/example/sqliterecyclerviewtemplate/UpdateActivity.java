package com.example.sqliterecyclerviewtemplate;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {


    EditText title_input2, author_input2, pages_input2;
    Button update_button;
    String id, title, author, pages;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_update);
//
//        title_input2 = findViewById(R.id.title_input2);
//        author_input2 = findViewById(R.id.author_input2);
//        pages_input2 = findViewById(R.id.pages_input2);
//        update_button = findViewById(R.id.update_button);
//
//
//        // call getAndSetIntentData
//        getAndSetIntentData();
//
//        update_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                title = title_input2.getText().toString();
//                author = author_input2.getText().toString();
//                pages = pages_input2.getText().toString();
//
//                MyDatabaseHelper db = new MyDatabaseHelper(UpdateActivity.this);
//                Toast.makeText(UpdateActivity.this, "id: " + id , Toast.LENGTH_SHORT).show();
//                db.updateData(id, title, author, pages);
//            }
//        });
//
//
//    }
//
//    protected void getAndSetIntentData()
//    {
//        if(getIntent().hasExtra("id")
//                && getIntent().hasExtra("title")
//                && getIntent().hasExtra("author")
//                && getIntent().hasExtra("pages"))
//        {
//
//            // Getting Data Here
//            id = getIntent().getStringExtra("id");
//            title = getIntent().getStringExtra("title");
//            author = getIntent().getStringExtra("author");
//            pages = getIntent().getStringExtra("pages");
//
//
//            // Setting Data Here
//            title_input2.setText(title);
//            author_input2.setText(author);
//            pages_input2.setText(pages);
//        }
//        else
//        {
//            Toast.makeText(this, "No Data...", Toast.LENGTH_SHORT).show();
//        }
//    }



}