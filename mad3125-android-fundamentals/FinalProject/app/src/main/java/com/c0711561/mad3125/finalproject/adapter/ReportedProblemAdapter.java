package com.c0711561.mad3125.finalproject.adapter;

import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.c0711561.mad3125.finalproject.R;
import com.c0711561.mad3125.finalproject.listener.OnItemClickListener;
import com.c0711561.mad3125.finalproject.model.Problem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by macstudent on 2017-12-08.
 */

public class ReportedProblemAdapter extends RecyclerView.Adapter<ReportedProblemAdapter.MyViewHolder> {

    private List<Problem> problems;

    public ReportedProblemAdapter(List<Problem> problems) {
        this.problems = problems;
    }

    public ReportedProblemAdapter(List<Problem> problems, OnItemClickListener listener) {
        this.problems = problems;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.problem_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {
        Problem problem = problems.get(i);
        holder.title.setText(problem.getTitle());
        holder.category.setText(problem.getCategory());
        holder.location.setText(problem.getLocation());
        holder.dateEvent.setText(formatDate(problem.getHappenedOn()));
        holder.problemPhoto.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        holder.problemPhoto.setAdjustViewBounds(true);
        holder.problemPhoto.setImageBitmap(BitmapFactory.decodeByteArray(problem.getImage(), 0, problem.getImage().length));
    }

    @Override
    public int getItemCount() {
        return problems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, category, location, dateEvent;
        public ImageView problemPhoto;
        public View viewForeground, viewBackground;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            category = itemView.findViewById(R.id.category);
            location = itemView.findViewById(R.id.location);
            dateEvent = itemView.findViewById(R.id.dateEvent);
            problemPhoto = itemView.findViewById(R.id.problemImage);
            viewForeground = itemView.findViewById(R.id.viewForeground);
            viewBackground = itemView.findViewById(R.id.viewBackground);
        }

    }

    private String formatDate(Date date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormatter.format(date);
    }

    public void removeProblem(int position) {
        problems.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreProblem(Problem problem, int position) {
        problems.add(position, problem);
        notifyItemInserted(position);
    }
}
