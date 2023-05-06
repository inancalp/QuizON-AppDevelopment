package com.example.sqliterecyclerviewtemplate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ShowResultsActivity extends AppCompatActivity {

    private ArrayList<Question> questions = new ArrayList<>();
    private Integer correct_answers_amount;
    private RecyclerView results_recyclerview;
    private ResultsAdapter resultsAdapter;
    private TextView results_textview;
    private String text_size;
    private  TextView results_symbol_textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_results);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        questions = intent.getParcelableArrayListExtra("QUESTIONS");
        correct_answers_amount = intent.getIntExtra("CORRECT_ANSWERS_AMOUNT", -1);
        text_size = intent.getStringExtra("TEXT_SIZE");


        results_textview = findViewById(R.id.results_textview);
        results_symbol_textview = findViewById(R.id.results_symbol_textview);

        if(text_size.equals(getResources().getString(R.string.font_small)))
        {
            results_textview.setTextAppearance(R.style.SMALL_TITLE);
            results_symbol_textview.setTextAppearance(R.style.SMALL_TITLE);
        }
        if(text_size.equals(getResources().getString(R.string.font_medium)))
        {
            results_textview.setTextAppearance(R.style.MEDIUM_TITLE);
            results_symbol_textview.setTextAppearance(R.style.MEDIUM_TITLE);
        }
        if(text_size.equals(getResources().getString(R.string.font_large)))
        {
            results_textview.setTextAppearance(R.style.LARGE_TITLE);
            results_symbol_textview.setTextAppearance(R.style.LARGE_TITLE);
        }

        results_recyclerview = findViewById(R.id.results_recyclerview);
        results_textview.setText(correct_answers_amount + "/" + questions.size());

//        for(Integer i = 0; i < questions.size(); i++)
//        {
//            Log.d("ShowResultsActivity", "question[" + i + "].getCorrectAnswer(): " + questions.get(i).getCorrectAnswer());
//        }

        resultsAdapter = new ResultsAdapter(ShowResultsActivity.this, questions, text_size);
        results_recyclerview.setAdapter(resultsAdapter);
        results_recyclerview.setLayoutManager(new LinearLayoutManager(ShowResultsActivity.this));
    }
}