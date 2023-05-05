package com.example.sqliterecyclerviewtemplate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.MyViewHolder>{

    private Context context;
    private ArrayList quiz_id;
    private ArrayList quiz_title;


    // constructor
    QuizAdapter(Context context, ArrayList quiz_id, ArrayList quiz_title)
    {
        this.context = context;
        this.quiz_id = quiz_id;
        this.quiz_title = quiz_title;
    }


    @NonNull
    @Override
    public QuizAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        holder.quiz_id_txt.setText(String.valueOf(quiz_id.get(position)));
        holder.quiz_title_txt.setText(String.valueOf(quiz_title.get(position)));

        holder.my_row_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Log.d("showQuestions:", "quiz_id: " + quiz_id.get(position));
//                Log.d("showQuestions:", "quiz_title: " + quiz_title.get(position));

                Intent intent = new Intent(context, ShowQuestionsActivity.class);

                intent.putExtra("QUIZ_ID", String.valueOf(quiz_id.get(position)));
                intent.putExtra("QUIZ_TITLE", String.valueOf(quiz_title.get(position)));
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount()
    {
        return quiz_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView quiz_title_txt;
        LinearLayout my_row_layout;
        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            quiz_title_txt = itemView.findViewById(R.id.quiz_title_txt);
            my_row_layout = itemView.findViewById(R.id.my_row_layout);
        }
    }
}
