package com.example.sqliterecyclerviewtemplate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class UserSettingsActivity extends AppCompatActivity {

    private Button light_theme_button, dark_theme_button, small_font_button, medium_font_button, large_font_button;
    private TextView set_font_textview, select_theme_textview;
    private SharedPreferences preferences; //SharedPreferences object.
    Spinner font_size_spinner, theme_spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);
        Intent intent = getIntent();

        light_theme_button = findViewById(R.id.light_theme_button);
        dark_theme_button = findViewById(R.id.dark_theme_button);
        small_font_button = findViewById(R.id.small_font_button);
        medium_font_button = findViewById(R.id.medium_font_button);
        large_font_button = findViewById(R.id.large_font_button);
        set_font_textview = findViewById(R.id.set_font_textview);
        select_theme_textview = findViewById(R.id.select_theme_textview);

        //
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        // (!) ContextCompat.getColor() is used to retrieve the color within the values/color.xml file properly
        set_font_textview.setTextColor(ContextCompat.getColor(this, R.color.black));
        set_font_textview.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_200));

        small_font_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String selectedBackgroundColor = "#FF0000";
                String selectedTextColor = "#FFFFFF";
                set_font_textview.setBackgroundColor(Color.parseColor(selectedBackgroundColor));
                set_font_textview.setTextColor(Color.parseColor(selectedTextColor));

                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("background_color", selectedBackgroundColor);
                editor.putString("text_color", selectedTextColor);
                editor.apply();
            }
        });




    }


}