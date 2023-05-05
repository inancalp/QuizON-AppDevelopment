package com.example.sqliterecyclerviewtemplate;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


// I HAD TO IMPLEMENT OnItemSelectedListener for to be able to use the spinner. Which includes new methods to use. (can be seen at the bottom of the MainActivity Class)
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    //variables goes out onCreate method.
    RecyclerView my_recyclerview;
    QuizAdapter my_customAdapter;
    FloatingActionButton add_quiz_button, edit_text_button;
    DatabaseHelper db_quiz, db_welcome_text;
    ArrayList<String> quiz_id, quiz_title;
    TextView welcome_textview;
    String welcome_text;
    Integer EDIT_WELCOME_REQUEST_CODE = 1;

    Switch theme_switch;
    boolean night_mode;

    String text_size;
    Spinner select_text_size_spinner;

    SharedPreferences shared_preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // custom toolbar is in use instead.
        getSupportActionBar().hide();

        my_recyclerview = findViewById(R.id.questions_recyclerview);
        add_quiz_button = findViewById(R.id.add_quiz_button);
        edit_text_button = findViewById(R.id.edit_text_button);
        welcome_textview = findViewById(R.id.welcome_textview);
        theme_switch = findViewById(R.id.theme_switch);
        select_text_size_spinner = findViewById(R.id.select_text_size_spinner);


        // to save the theme & font state of the app.
        shared_preferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        night_mode = shared_preferences.getBoolean("NIGHT", false);

        if(night_mode)
        {
            theme_switch.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else{

        }

        theme_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(night_mode)
                {
                    // we change the mode into LIGHT MODE here.
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                    // saving the THEME STATE here.
                    editor = shared_preferences.edit();
                    editor.putBoolean("NIGHT", false);
                }
                else
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = shared_preferences.edit();
                    editor.putBoolean("NIGHT", true);
                }

                // we need to apply the changes to save the app states for later use.
                editor.apply();
            }
        });


        // CODE BELOW FOR SPINNER WAS USED AS IT IS EXPLAINED WITHIN THE DOCUMENTATION.
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.font_size, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        select_text_size_spinner.setAdapter(adapter);

        // this one activates the method defined below for the spinner.
        select_text_size_spinner.setOnItemSelectedListener(this);


        // Here I use the Shared Preferences to get the implemented text size, if nothing is selected before, default size is Medium.
        // Then I emit setSelection which activates the onItemSelected method.
        text_size = shared_preferences.getString("TEXT_SIZE", "Medium");

        if(text_size.equals("Small")) {
            select_text_size_spinner.setSelection(0);
        } else if(text_size.equals("Medium")) {
            select_text_size_spinner.setSelection(1);
        } else if(text_size.equals("Large")) {
            select_text_size_spinner.setSelection(2);
        }



        edit_text_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                welcome_text = welcome_textview.getText().toString();
                Intent intent = new Intent(MainActivity.this, EditWelcomeTextActivity.class);
                intent.putExtra("WELCOME_TEXT", welcome_text);
                intent.putExtra("TEXT_SIZE", text_size);
                startActivity(intent);
            }
        });


        add_quiz_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // will change the activity to AddActivity
                Intent intent = new Intent(MainActivity.this, AddQuizActivity.class);
                intent.putExtra("TEXT_SIZE", text_size);
                startActivity(intent);
            }
        });



        // part-3
        db_quiz = new DatabaseHelper(MainActivity.this);
        quiz_id = new ArrayList<>();
        quiz_title = new ArrayList<>();
        storeDataInArray();


        // WELCOME TEXT
        retrieveWelcomeText();
        welcome_textview.setText(welcome_text);





    }


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
        db_welcome_text = new DatabaseHelper(MainActivity.this);
        Cursor cursor = db_welcome_text.readAllData_welcomeText();

        if(cursor.getCount() == 0)
        {
            Toast.makeText(this, "No data in WELCOME_TEXT_TABLE.", Toast.LENGTH_SHORT).show();
            Log.d("WELCOME_TEXT_TABLE", "No data in WELCOME_TEXT_TABLE.");
        }
        else
        {
            cursor.moveToFirst();
            welcome_text = cursor.getString(1);
        }
    }


    // Method implementations for the SPINNER: onItemSelected // onNothingSelected
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        text_size = parent.getSelectedItem().toString();

        if(text_size.equals("Small"))
            welcome_textview.setTextAppearance(R.style.SMALL_TEXT);
        if(text_size.equals("Medium"))
            welcome_textview.setTextAppearance(R.style.MEDIUM_TEXT);
        if(text_size.equals("Large"))
            welcome_textview.setTextAppearance(R.style.LARGE_TEXT);

        editor = shared_preferences.edit();
        editor.putString("TEXT_SIZE", text_size);
        editor.apply();


        // (!)
        // I HAVE MOVED THE ADAPTER HERE SINCE WHEN THE TEXT SIZE HAS CHANGED, I WANTED TO RECREATE THE RECYCLERVIEW.
        // storeDataInArray method is important to call before the customAdapter.
        my_customAdapter = new QuizAdapter(MainActivity.this, quiz_id, quiz_title, text_size);
        my_recyclerview.setAdapter(my_customAdapter);
        my_recyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //
    }
}