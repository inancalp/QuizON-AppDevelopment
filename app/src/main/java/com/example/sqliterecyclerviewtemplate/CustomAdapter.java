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

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    private Context context;
    private ArrayList quiz_id;
    private ArrayList quiz_title;


    // constructor
    CustomAdapter(Context context, ArrayList quiz_id, ArrayList quiz_title)
    {
        this.context = context;
        this.quiz_id = quiz_id;
        this.quiz_title = quiz_title;
    }


    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.quiz_id_txt.setText(String.valueOf(quiz_id.get(position)));
        holder.quiz_title_txt.setText(String.valueOf(quiz_title.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);

                intent.putExtra("id", String.valueOf(quiz_id.get(position)));
                intent.putExtra("title", String.valueOf(quiz_title.get(position)));

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

        TextView quiz_id_txt;
        TextView quiz_title_txt;

        // part-4
        // layout it for the recyclerView "my_row.xml"
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            quiz_id_txt = itemView.findViewById(R.id.quiz_id_txt);
            quiz_title_txt = itemView.findViewById(R.id.quiz_title_txt);

            // part-4
            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }
}
