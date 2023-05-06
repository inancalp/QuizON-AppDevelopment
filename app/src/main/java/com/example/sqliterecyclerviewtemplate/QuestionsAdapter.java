package com.example.sqliterecyclerviewtemplate;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionHolder> {
    private Context context;
    ArrayList<Question> questions = new ArrayList<>();
    private RadioButton checked_radio_button;
    private Integer correct_answers_amount;
    private long quiz_id;
    private String text_size;

//    QuestionsAdapter(Context context, ArrayList question_id, ArrayList question, ArrayList answer_a, ArrayList answer_b, ArrayList answer_c, ArrayList answer_d, ArrayList correct_answers)
    QuestionsAdapter(Context context, ArrayList<Question> questions, long quiz_id, String text_size)
    {
        this.context = context;
        this.questions = questions;
        this.quiz_id = quiz_id;
        this.text_size = text_size;

        for(Integer i = 0; i < questions.size(); i++)
        {
            this.questions.get(i).setSelectedAnswer("");
        }
        for(Integer j = 0; j < questions.size(); j++)
        {
            Log.d("QuestionAdapter", this.questions.get(j).getSelectedAnswer().toString());
            Log.d("QuestionAdapter", this.questions.get(j).getCorrectAnswer().toString());
        }
    }


    @NonNull
    @Override
    public QuestionsAdapter.QuestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.question_row, parent, false);
        return new QuestionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsAdapter.QuestionHolder question_holder, @SuppressLint("RecyclerView") int position) {

        question_holder.question_view.setText(String.valueOf(questions.get(position).getQuestion()));
        question_holder.answerA_view.setText(String.valueOf(questions.get(position).getAnswerA()));
        question_holder.answerB_view.setText(String.valueOf(questions.get(position).getAnswerB()));
        question_holder.answerC_view.setText(String.valueOf(questions.get(position).getAnswerC()));
        question_holder.answerD_view.setText(String.valueOf(questions.get(position).getAnswerD()));

        if(text_size.equals(context.getString(R.string.font_small)))
        {
            question_holder.question_view.setTextAppearance(R.style.SMALL_TEXT);
            question_holder.answerA_view.setTextAppearance(R.style.SMALL_TEXT);
            question_holder.answerB_view.setTextAppearance(R.style.SMALL_TEXT);
            question_holder.answerC_view.setTextAppearance(R.style.SMALL_TEXT);
            question_holder.answerD_view.setTextAppearance(R.style.SMALL_TEXT);
            question_holder.show_results_button.setTextAppearance(R.style.SMALL_TEXT);
            question_holder.delete_quiz_button.setTextAppearance(R.style.SMALL_TEXT);
        }
        if(text_size.equals(context.getString(R.string.font_medium)))
        {
            question_holder.question_view.setTextAppearance(R.style.MEDIUM_TEXT);
            question_holder.answerA_view.setTextAppearance(R.style.MEDIUM_TEXT);
            question_holder.answerB_view.setTextAppearance(R.style.MEDIUM_TEXT);
            question_holder.answerC_view.setTextAppearance(R.style.MEDIUM_TEXT);
            question_holder.answerD_view.setTextAppearance(R.style.MEDIUM_TEXT);
            question_holder.show_results_button.setTextAppearance(R.style.MEDIUM_TEXT);
            question_holder.delete_quiz_button.setTextAppearance(R.style.MEDIUM_TEXT);
        }
        if(text_size.equals(context.getString(R.string.font_large)))
        {
            question_holder.question_view.setTextAppearance(R.style.LARGE_TEXT);
            question_holder.answerA_view.setTextAppearance(R.style.LARGE_TEXT);
            question_holder.answerB_view.setTextAppearance(R.style.LARGE_TEXT);
            question_holder.answerC_view.setTextAppearance(R.style.LARGE_TEXT);
            question_holder.answerD_view.setTextAppearance(R.style.LARGE_TEXT);
            question_holder.show_results_button.setTextAppearance(R.style.LARGE_TEXT);
            question_holder.delete_quiz_button.setTextAppearance(R.style.LARGE_TEXT);
        }

        if (position == 0)
            question_holder.delete_quiz_button.setVisibility(View.VISIBLE);

        if (position == getItemCount() - 1)
            question_holder.show_results_button.setVisibility(View.VISIBLE);




        question_holder.select_answer_radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checked_radio_button = group.findViewById(checkedId);
                questions.get(position).setSelectedAnswer(checked_radio_button.getText().toString());

                if(questions.get(position).getSelectedAnswer().toString()
                        .equals(questions.get(position).getCorrectAnswer().toString()))
                {
                    Log.d("QuestionAdapter", "Correct answer selected!");
                }

            }
        });

        question_holder.show_results_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correct_answers_amount = 0;
                for(Integer i = 0; i < getItemCount(); i++)
                {
                    if(questions.get(i).getSelectedAnswer()
                            .equals(questions.get(i).getCorrectAnswer()))
                    {
                        correct_answers_amount++;
                    }
                }

                Intent intent = new Intent(context, ShowResultsActivity.class);
                // if Question class implements Parcelable Interface, then (ArrayList<? extends Parcelable>) can be used as casted type.
                intent.putParcelableArrayListExtra("QUESTIONS", (ArrayList<? extends Parcelable>) questions);
                intent.putExtra("CORRECT_ANSWERS_AMOUNT", correct_answers_amount);
                intent.putExtra("TEXT_SIZE", text_size);
                context.startActivity(intent);





//                for(Integer j = 0; j < getItemCount(); j++)
//                {
//                    Log.d("QuestionAdapter", "selected_answer: " + questions.get(j).getSelectedAnswer());
//                    Log.d("QuestionAdapter", "correct_answer: " + questions.get(j).getCorrectAnswer());
//                }
//
//                Log.d("QuestionAdapter", "(!)(!) Correct Answers: " + correct_answers_amount + "/" + getItemCount());
            }
        });

        question_holder.delete_quiz_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // AlertDialog is used to confirm Delete Quiz Action.
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure you want to perform this action?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DatabaseHelper db = new DatabaseHelper(context);
                        String quiz_id_string = Long.toString(quiz_id);
                        db.deleteQuiz(quiz_id_string);
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", null);
                builder.show();
            }
        });
    }

    @Override
    // does not matter since all the arrays have the same size.
    public int getItemCount() {
        return questions.size();
    }

    public class QuestionHolder extends RecyclerView.ViewHolder {
        TextView question_view;
        RadioButton  answerA_view, answerB_view, answerC_view, answerD_view;
        RadioGroup select_answer_radio_group;
        Button show_results_button;

        Button delete_quiz_button;

        public QuestionHolder(@NonNull View itemView) {
            super(itemView);

            question_view = itemView.findViewById(R.id.question_view);
            answerA_view = itemView.findViewById(R.id.answerA_view);
            answerB_view = itemView.findViewById(R.id.answerB_view);
            answerC_view = itemView.findViewById(R.id.answerC_view);
            answerD_view = itemView.findViewById(R.id.answerD_view);
            show_results_button = itemView.findViewById(R.id.show_results_button);
            select_answer_radio_group = itemView.findViewById(R.id.select_answer_radio_group);
            show_results_button = itemView.findViewById(R.id.show_results_button);
            delete_quiz_button = itemView.findViewById(R.id.delete_quiz_button);
        }
    }
}
