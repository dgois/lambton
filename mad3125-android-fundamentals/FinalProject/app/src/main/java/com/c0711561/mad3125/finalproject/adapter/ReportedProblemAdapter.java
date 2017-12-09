package com.c0711561.mad3125.finalproject.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.c0711561.mad3125.finalproject.R;
import com.c0711561.mad3125.finalproject.model.Problem;

import java.util.List;

/**
 * Created by macstudent on 2017-12-08.
 */

public class ReportedProblemAdapter extends RecyclerView.Adapter<ReportedProblemAdapter.MyViewHolder> {

    private List<Problem> problems;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.problem_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        Problem problem = problems.get(i);
        myViewHolder.title.setText(problem.getTitle());
        myViewHolder.category.setText(problem.getCategory());
        myViewHolder.location.setText(problem.getLocation());
    }

    @Override
    public int getItemCount() {
        return problems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, category, location;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            category = itemView.findViewById(R.id.category);
            location = itemView.findViewById(R.id.location);
        }
    }

    public ReportedProblemAdapter(List<Problem> problems) {
        this.problems = problems;
    }
}
