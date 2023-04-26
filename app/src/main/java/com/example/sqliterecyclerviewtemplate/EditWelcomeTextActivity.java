package com.example.sqliterecyclerviewtemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;


public class EditWelcomeTextActivity extends AppCompatActivity {


    EditText welcome_text_input;
    Button edit_button;
    String welcome_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_welcome_text);


        setTitle("EditWelcomeTextActivity");

        Intent returnIntent = getIntent();
        welcome_text = returnIntent.getStringExtra("WELCOME_TEXT");
        welcome_text_input = findViewById(R.id.welcome_text_input);
        welcome_text_input.setText(welcome_text);

        edit_button = findViewById(R.id.edit_button);
        edit_button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            welcome_text = welcome_text_input.getText().toString();

            if(welcome_text.isEmpty())
            {
                welcome_text = "DEFAULT WELCOME TEXT.";
            }

            MyDatabaseHelper db_welcome_text = new MyDatabaseHelper(EditWelcomeTextActivity.this);
            db_welcome_text.updateWelcomeText(welcome_text);
//            returnIntent.putExtra("WELCOME_TEXT",welcome_text);
//            setResult(EditWelcomeTextActivity.RESULT_OK, returnIntent);
//            finish();
        }
    });

    }
}