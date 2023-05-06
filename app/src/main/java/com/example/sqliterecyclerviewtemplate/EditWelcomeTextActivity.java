package com.example.sqliterecyclerviewtemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class EditWelcomeTextActivity extends AppCompatActivity {


    EditText welcome_text_input;
    Button edit_button;
    String welcome_text;

    String text_size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_welcome_text);
        getSupportActionBar().hide();

        Intent return_intent = getIntent();
        welcome_text = return_intent.getStringExtra("WELCOME_TEXT");
        text_size = return_intent.getStringExtra("TEXT_SIZE");

        welcome_text_input = findViewById(R.id.welcome_text_input);
        welcome_text_input.setText(welcome_text);
        edit_button = findViewById(R.id.edit_button);

        if(text_size.equals(getResources().getString(R.string.font_small)))
        {
            welcome_text_input.setTextAppearance(R.style.SMALL_TEXT);
            edit_button.setTextAppearance(R.style.SMALL_TEXT);
        }
        if(text_size.equals(getResources().getString(R.string.font_medium)))
        {
            welcome_text_input.setTextAppearance(R.style.MEDIUM_TEXT);
            edit_button.setTextAppearance(R.style.MEDIUM_TEXT);
        }
        if(text_size.equals(getResources().getString(R.string.font_large)))
        {
            welcome_text_input.setTextAppearance(R.style.LARGE_TEXT);
            edit_button.setTextAppearance(R.style.LARGE_TEXT);
        }

        edit_button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            welcome_text = welcome_text_input.getText().toString();

            if(welcome_text.isEmpty())
            {
                welcome_text = "DEFAULT WELCOME TEXT.";
            }

            DatabaseHelper db_welcome_text = new DatabaseHelper(EditWelcomeTextActivity.this);
            db_welcome_text.updateWelcomeText(welcome_text);

            Intent intent = new Intent(EditWelcomeTextActivity.this, MainActivity.class);
            startActivity(intent);
        }
    });

    }
}