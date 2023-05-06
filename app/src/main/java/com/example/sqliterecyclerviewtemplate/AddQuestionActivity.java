package com.example.sqliterecyclerviewtemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class AddQuestionActivity extends AppCompatActivity {


    TextView answer_a_text_view, answer_b_text_view, answer_c_text_view, answer_d_text_view, choose_correct_answer_text_view;
    EditText question_view, answer_a_view, answer_b_view, answer_c_view, answer_d_view;
    Button add_question_button, store_quiz_button;
    long quiz_id;
    String quiz_title;

    RadioGroup correct_answer_radio_group;
    RadioButton correct_answer_radio_button;
    String correct_answer;
    ArrayList<Question> questions = new ArrayList<Question>();

    String text_size;

    RadioButton answer_a_radio, answer_b_radio, answer_c_radio, answer_d_radio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        quiz_id = intent.getLongExtra("QUIZ_ID", -1);
        quiz_title = intent.getStringExtra("QUIZ_TITLE");
        text_size = intent.getStringExtra("TEXT_SIZE");

        Log.d("AddQuestion", "quiz_id = " + quiz_id);
        Log.d("AddQuestion", "quiz_title = " + quiz_title);

//        quiz_title_textview = findViewById(R.id.quiz_title_textview);
//        quiz_title_textview.setText("Quiz with ID: " + quiz_id);
        question_view = findViewById(R.id.question_view);
        answer_a_view = findViewById(R.id.answer_a_view);
        answer_b_view = findViewById(R.id.answer_b_view);
        answer_c_view = findViewById(R.id.answer_c_view);
        answer_d_view = findViewById(R.id.answer_d_view);
        correct_answer_radio_group = findViewById(R.id.correct_answer_radio_group);
        add_question_button = findViewById(R.id.add_question_button);
        store_quiz_button = findViewById(R.id.store_quiz_button);


        answer_a_text_view = findViewById(R.id.answer_a_text_view);
        answer_b_text_view = findViewById(R.id.answer_b_text_view);
        answer_c_text_view = findViewById(R.id.answer_c_text_view);
        answer_d_text_view = findViewById(R.id.answer_d_text_view);
        choose_correct_answer_text_view = findViewById(R.id.choose_correct_answer_text_view);

        answer_a_radio = findViewById(R.id.answer_a_radio);
        answer_b_radio = findViewById(R.id.answer_b_radio);
        answer_c_radio = findViewById(R.id.answer_c_radio);
        answer_d_radio = findViewById(R.id.answer_d_radio);



        if(text_size.equals(getResources().getString(R.string.font_small)))
        {
            question_view.setTextAppearance(R.style.SMALL_TEXT);
            answer_a_view.setTextAppearance(R.style.SMALL_TEXT);
            answer_b_view.setTextAppearance(R.style.SMALL_TEXT);
            answer_c_view.setTextAppearance(R.style.SMALL_TEXT);
            answer_d_view.setTextAppearance(R.style.SMALL_TEXT);
            add_question_button.setTextAppearance(R.style.SMALL_TEXT);
            store_quiz_button.setTextAppearance(R.style.SMALL_TEXT);
            //
            answer_a_text_view.setTextAppearance(R.style.SMALL_TEXT);
            answer_b_text_view.setTextAppearance(R.style.SMALL_TEXT);
            answer_c_text_view.setTextAppearance(R.style.SMALL_TEXT);
            answer_d_text_view.setTextAppearance(R.style.SMALL_TEXT);
            choose_correct_answer_text_view.setTextAppearance(R.style.SMALL_TEXT);
            //
            answer_a_radio.setTextAppearance(R.style.SMALL_TEXT);
            answer_b_radio.setTextAppearance(R.style.SMALL_TEXT);
            answer_c_radio.setTextAppearance(R.style.SMALL_TEXT);
            answer_d_radio.setTextAppearance(R.style.SMALL_TEXT);
        }
        if(text_size.equals(getResources().getString(R.string.font_medium)))
        {
            question_view.setTextAppearance(R.style.MEDIUM_TEXT);
            answer_a_view.setTextAppearance(R.style.MEDIUM_TEXT);
            answer_b_view.setTextAppearance(R.style.MEDIUM_TEXT);
            answer_c_view.setTextAppearance(R.style.MEDIUM_TEXT);
            answer_d_view.setTextAppearance(R.style.MEDIUM_TEXT);
            add_question_button.setTextAppearance(R.style.MEDIUM_TEXT);
            store_quiz_button.setTextAppearance(R.style.MEDIUM_TEXT);
            //
            answer_a_text_view.setTextAppearance(R.style.MEDIUM_TEXT);
            answer_b_text_view.setTextAppearance(R.style.MEDIUM_TEXT);
            answer_c_text_view.setTextAppearance(R.style.MEDIUM_TEXT);
            answer_d_text_view.setTextAppearance(R.style.MEDIUM_TEXT);
            choose_correct_answer_text_view.setTextAppearance(R.style.MEDIUM_TEXT);
            //
            answer_a_radio.setTextAppearance(R.style.MEDIUM_TEXT);
            answer_b_radio.setTextAppearance(R.style.MEDIUM_TEXT);
            answer_c_radio.setTextAppearance(R.style.MEDIUM_TEXT);
            answer_d_radio.setTextAppearance(R.style.MEDIUM_TEXT);
        }
        if(text_size.equals(getResources().getString(R.string.font_large)))
        {
            question_view.setTextAppearance(R.style.LARGE_TEXT);
            answer_a_view.setTextAppearance(R.style.LARGE_TEXT);
            answer_b_view.setTextAppearance(R.style.LARGE_TEXT);
            answer_c_view.setTextAppearance(R.style.LARGE_TEXT);
            answer_d_view.setTextAppearance(R.style.LARGE_TEXT);
            add_question_button.setTextAppearance(R.style.LARGE_TEXT);
            store_quiz_button.setTextAppearance(R.style.LARGE_TEXT);
            //
            answer_a_text_view.setTextAppearance(R.style.LARGE_TEXT);
            answer_b_text_view.setTextAppearance(R.style.LARGE_TEXT);
            answer_c_text_view.setTextAppearance(R.style.LARGE_TEXT);
            answer_d_text_view.setTextAppearance(R.style.LARGE_TEXT);
            choose_correct_answer_text_view.setTextAppearance(R.style.LARGE_TEXT);
            //
            answer_a_radio.setTextAppearance(R.style.LARGE_TEXT);
            answer_b_radio.setTextAppearance(R.style.LARGE_TEXT);
            answer_c_radio.setTextAppearance(R.style.LARGE_TEXT);
            answer_d_radio.setTextAppearance(R.style.LARGE_TEXT);

        }



        add_question_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer correct_answer_radio_button_id = correct_answer_radio_group.getCheckedRadioButtonId();

                if(question_view.getText().toString().equals(""))
                {
                    Toast.makeText(AddQuestionActivity.this, "Error: Questions is Empty.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(answer_a_view.getText().toString().equals("")
                    || answer_b_view.getText().toString().equals("")
                    || answer_c_view.getText().toString().equals("")
                    || answer_d_view.getText().toString().equals(""))
                {
                    Toast.makeText(AddQuestionActivity.this, "Error: Every Option Field should be filled.", Toast.LENGTH_SHORT).show();
                    return;
                }

                //making sure correct answer is selected.
                if(correct_answer_radio_button_id == -1)
                {
                    Toast.makeText(AddQuestionActivity.this, "ERROR: Correct answer needs to be selected.", Toast.LENGTH_SHORT).show();
                    return;
                }

                correct_answer_radio_button = findViewById(correct_answer_radio_button_id);
//                    Log.d("selected_answer", correct_answer_radio_button.getText().toString());

                Log.d("Questions", correct_answer_radio_button.getText().toString());


                // making sure the answers are not the same.
                if(answer_a_view.getText().toString().equals(answer_b_view.getText().toString())
                        || answer_a_view.getText().toString().equals(answer_c_view.getText().toString())
                        || answer_a_view.getText().toString().equals(answer_d_view.getText().toString())
                        || answer_b_view.getText().toString().equals(answer_c_view.getText().toString())
                        || answer_b_view.getText().toString().equals(answer_d_view.getText().toString())
                        || answer_c_view.getText().toString().equals(answer_d_view.getText().toString()))
                {
//                        Toast.makeText(AddQuestionActivity.this, "ERROR!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(AddQuestionActivity.this, "ERROR: It's not allowed to use the same answer for different options.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // checking what which radio button text includes the correct answer.
                if(correct_answer_radio_button.getText().toString().equals("A"))
                    correct_answer = answer_a_view.getText().toString();
                if(correct_answer_radio_button.getText().toString().equals("B"))
                    correct_answer = answer_b_view.getText().toString();
                if(correct_answer_radio_button.getText().toString().equals("C"))
                    correct_answer = answer_c_view.getText().toString();
                if(correct_answer_radio_button.getText().toString().equals("D"))
                    correct_answer = answer_d_view.getText().toString();


                questions.add(new Question(
                        question_view.getText().toString(),
                        answer_a_view.getText().toString(),
                        answer_b_view.getText().toString(),
                        answer_c_view.getText().toString(),
                        answer_d_view.getText().toString(),
                        correct_answer));

                Toast.makeText(AddQuestionActivity.this, "Question has been added.", Toast.LENGTH_SHORT).show();

                question_view.setText("");
                answer_a_view.setText("");
                answer_b_view.setText("");
                answer_c_view.setText("");
                answer_d_view.setText("");
                correct_answer_radio_group.clearCheck();

            }
        });

        store_quiz_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                for(Question question : questions)
//                {
//                    Log.d("Questions", question.getQuestion().toString());
//                    Log.d("Questions", question.getAnswerA().toString());
//                    Log.d("Questions", question.getAnswerB().toString());
//                    Log.d("Questions", question.getAnswerC().toString());
//                    Log.d("Questions", question.getAnswerD().toString());
//                    Log.d("Questions", question.getCorrectAnswer().toString());
//                }

                if(questions.size() == 0)
                {
                    Toast.makeText(AddQuestionActivity.this, "ERROR: There is at least ONE question should be included within the quiz.", Toast.LENGTH_SHORT).show();
                    return;
                }

                DatabaseHelper db = new DatabaseHelper(AddQuestionActivity.this);
                quiz_id = db.addQuiz(quiz_title);
                db.addQuestions(questions, quiz_id);


                Intent intent_main_activity = new Intent(AddQuestionActivity.this, MainActivity.class);
                startActivity(intent_main_activity);
            }
        });

    }


    // More TEST NEEDED.
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        MyDatabaseHelper db = new MyDatabaseHelper(AddQuestionActivity.this);
//        db.deleteQuiz(String.valueOf(quiz_id));
//
//    }
}