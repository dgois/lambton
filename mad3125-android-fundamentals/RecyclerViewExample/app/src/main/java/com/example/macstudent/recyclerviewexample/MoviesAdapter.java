package com.example.macstudent.recyclerviewexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by macstudent on 2017-12-07.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<Movie> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;
        public Button btnclick;
        public Movie selectedMovie;
        public Context context;

        public MyViewHolder(Context itemContext, View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            title.setSelected(true);
            genre = (TextView) itemView.findViewById(R.id.genre);
            year = (TextView) itemView.findViewById(R.id.year);
            btnclick = (Button) itemView.findViewById(R.id.btnClick);
            context = itemContext;

            btnclick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("DENIS", selectedMovie.getTitle());
                    Toast.makeText(context, selectedMovie.getTitle(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public MoviesAdapter(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MoviesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(parent.getContext(), itemView);
    }

    @Override
    public void onBindViewHolder(MoviesAdapter.MyViewHolder holder, int position) {
        Movie movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());
        holder.genre.setText(movie.getGenre());
        holder.year.setText(movie.getYear());
        holder.selectedMovie = movie;
        Log.i("DENIS", "creating a new movie: " + movie.getTitle());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
