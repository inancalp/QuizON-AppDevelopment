package com.example.sqliterecyclerviewtemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText title_input;
    EditText author_input;
    EditText pages_input;
    Button add_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // initialize the components
        title_input = findViewById(R.id.title_input);
        author_input = findViewById(R.id.author_input);
        pages_input = findViewById(R.id.pages_input);
        add_button = findViewById(R.id.add_button);


        // call the onClick event listener for the "add_button"
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // call the addBook() method from MyDatabaseHelper class.
                    // to be able to call the method, need to create a object for the class for sure.
                MyDatabaseHelper db = new MyDatabaseHelper(AddActivity.this);
                // with the slides it is:
                //MyDatabaseHelper db = new MyDatabaseHelper(getContext());

                // we need to get the text as requested type
                    // trim is a good idea to clear the content.
                db.addBook(title_input.getText().toString().trim(),
                        author_input.getText().toString().trim(),
                        Integer.valueOf(pages_input.getText().toString().trim()));
            }
        });
    }
}