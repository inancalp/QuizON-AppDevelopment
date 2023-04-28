package com.example.sqliterecyclerviewtemplate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionHolder> {

    private Context context;
    private ArrayList question_id, question, answer_a, answer_b, answer_c, answer_d;

    QuestionsAdapter(Context context, ArrayList question_id, ArrayList question, ArrayList answer_a, ArrayList answer_b, ArrayList answer_c, ArrayList answer_d)
    {
        this.context = context;
        this.question_id = question_id;
        this.question = question;
        this.answer_a = answer_a;
        this.answer_b = answer_b;
        this.answer_c = answer_c;
        this.answer_d = answer_d;
    }


    @NonNull
    @Override
    public QuestionsAdapter.QuestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.question_row, parent, false);
        return new QuestionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsAdapter.QuestionHolder holder, int position) {
        holder.question_view.setText(String.valueOf(question.get(position)));
        holder.answerA_view.setText(String.valueOf(answer_a.get(position)));
        holder.answerB_view.setText(String.valueOf(answer_b.get(position)));
        holder.answerC_view.setText(String.valueOf(answer_c.get(position)));
        holder.answerD_view.setText(String.valueOf(answer_d.get(position)));
    }

    @Override
    // does not matter since all the arrays have the same size.
    public int getItemCount() {
        return question_id.size();
    }

    public class QuestionHolder extends RecyclerView.ViewHolder {

        TextView question_view, answerA_view, answerB_view, answerC_view, answerD_view;
        public QuestionHolder(@NonNull View itemView) {
            super(itemView);
            question_view = itemView.findViewById(R.id.question_view);
            answerA_view = itemView.findViewById(R.id.answerA_view);
            answerB_view = itemView.findViewById(R.id.answerB_view);
            answerC_view = itemView.findViewById(R.id.answerC_view);
            answerD_view = itemView.findViewById(R.id.answerD_view);

        }
    }
}
