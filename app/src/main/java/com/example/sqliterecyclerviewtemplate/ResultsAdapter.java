package com.example.sqliterecyclerviewtemplate;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ResultsHolder> {

    private Context context;
    private ArrayList<Question> questions = new ArrayList<>();
    private  String text_size;

    ResultsAdapter(Context context, ArrayList questions, String text_size)
    {
        this.context = context;
        this.questions = questions;
        this.text_size = text_size;
    }

    @NonNull
    @Override
    public ResultsAdapter.ResultsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.results_row, parent, false);
        return new ResultsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultsAdapter.ResultsHolder results_holder, int position) {
        results_holder.results_question_view.setText(String.valueOf(questions.get(position).getQuestion()));
        results_holder.results_answerA_view.setText(String.valueOf(questions.get(position).getAnswerA()));
        results_holder.results_answerB_view.setText(String.valueOf(questions.get(position).getAnswerB()));
        results_holder.results_answerC_view.setText(String.valueOf(questions.get(position).getAnswerC()));
        results_holder.results_answerD_view.setText(String.valueOf(questions.get(position).getAnswerD()));

        if(text_size.equals("Small"))
        {
            results_holder.results_question_view.setTextAppearance(R.style.SMALL_TEXT);
            results_holder.results_answerA_view.setTextAppearance(R.style.SMALL_TEXT);
            results_holder.results_answerB_view.setTextAppearance(R.style.SMALL_TEXT);
            results_holder.results_answerC_view.setTextAppearance(R.style.SMALL_TEXT);
            results_holder.results_answerD_view.setTextAppearance(R.style.SMALL_TEXT);
            results_holder.results_return_main_activity_button.setTextAppearance(R.style.SMALL_TEXT);
        }
        if(text_size.equals("Medium"))
        {
            results_holder.results_question_view.setTextAppearance(R.style.MEDIUM_TEXT);
            results_holder.results_answerA_view.setTextAppearance(R.style.MEDIUM_TEXT);
            results_holder.results_answerB_view.setTextAppearance(R.style.MEDIUM_TEXT);
            results_holder.results_answerC_view.setTextAppearance(R.style.MEDIUM_TEXT);
            results_holder.results_answerD_view.setTextAppearance(R.style.MEDIUM_TEXT);
            results_holder.results_return_main_activity_button.setTextAppearance(R.style.MEDIUM_TEXT);
        }
        if(text_size.equals("Large"))
        {
            results_holder.results_question_view.setTextAppearance(R.style.LARGE_TEXT);
            results_holder.results_answerA_view.setTextAppearance(R.style.LARGE_TEXT);
            results_holder.results_answerB_view.setTextAppearance(R.style.LARGE_TEXT);
            results_holder.results_answerC_view.setTextAppearance(R.style.LARGE_TEXT);
            results_holder.results_answerD_view.setTextAppearance(R.style.LARGE_TEXT);
            results_holder.results_return_main_activity_button.setTextAppearance(R.style.LARGE_TEXT);
        }


        if (position == getItemCount() - 1)
            results_holder.results_return_main_activity_button.setVisibility(View.VISIBLE);

        results_holder.results_return_main_activity_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        });



        // depending on the state
        String correctAnswer = questions.get(position).getCorrectAnswer();
        String selectedAnswer = questions.get(position).getSelectedAnswer();

        if (correctAnswer.equals(selectedAnswer)) {
            setBackgroundResourceByAnswer(results_holder, correctAnswer, R.drawable.border_green);
            setCheckedByAnswer(results_holder, correctAnswer);
        } else {
            setBackgroundResourceByAnswer(results_holder, correctAnswer, R.drawable.border_gray);
            setBackgroundResourceByAnswer(results_holder, selectedAnswer, R.drawable.border_red);
            setCheckedByAnswer(results_holder, selectedAnswer);
        }


    }



    @Override
    public int getItemCount() {
        return questions.size();
    }


    public class ResultsHolder extends RecyclerView.ViewHolder
    {
        TextView results_question_view;
        RadioButton results_answerA_view, results_answerB_view, results_answerC_view, results_answerD_view;
        RadioGroup results_select_answer_radio_group;
        Button results_return_main_activity_button;
        public ResultsHolder(@NonNull View itemView) {
            super(itemView);
            results_question_view = itemView.findViewById(R.id.results_question_view);
            results_select_answer_radio_group = itemView.findViewById(R.id.results_select_answer_radio_group);
            results_answerA_view = itemView.findViewById(R.id.results_answerA_view);
            results_answerB_view = itemView.findViewById(R.id.results_answerB_view);
            results_answerC_view = itemView.findViewById(R.id.results_answerC_view);
            results_answerD_view = itemView.findViewById(R.id.results_answerD_view);
            results_return_main_activity_button = itemView.findViewById(R.id.results_return_main_activity_button);
        }
    }

    private void setBackgroundResourceByAnswer(ResultsHolder results_holder, String answer, int resourceId) {
        if (answer.equals(results_holder.results_answerA_view.getText())) {
            results_holder.results_answerA_view.setBackgroundResource(resourceId);
        }
        if (answer.equals(results_holder.results_answerB_view.getText())) {
            results_holder.results_answerB_view.setBackgroundResource(resourceId);
        }
        if (answer.equals(results_holder.results_answerC_view.getText())) {
            results_holder.results_answerC_view.setBackgroundResource(resourceId);
        }
        if (answer.equals(results_holder.results_answerD_view.getText())) {
            results_holder.results_answerD_view.setBackgroundResource(resourceId);
        }
    }

    private void setCheckedByAnswer(ResultsHolder results_holder, String answer) {
        if (answer.equals(results_holder.results_answerA_view.getText())) {
            results_holder.results_answerA_view.setChecked(true);
        }
        if (answer.equals(results_holder.results_answerB_view.getText())) {
            results_holder.results_answerB_view.setChecked(true);
        }
        if (answer.equals(results_holder.results_answerC_view.getText())) {
            results_holder.results_answerC_view.setChecked(true);
        }
        if (answer.equals(results_holder.results_answerD_view.getText())) {
            results_holder.results_answerD_view.setChecked(true);
        }
    }

}


