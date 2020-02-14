package com.siddharth.moviedetailapp.Data;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.siddharth.moviedetailapp.Activites.MovieDetailActivity;
import com.siddharth.moviedetailapp.Model.Movie;
import com.siddharth.moviedetailapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    // Create Member Variable..
    private Context context;
    private List<Movie> movieList;

    // Create Constructor..
    public RecyclerViewAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movieList = movies;

    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_row, viewGroup, false);


        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int i) {

        if ((movieList == null) || (movieList.size() == 0)) {

            viewHolder.poster.setImageResource(R.drawable.error);

        } else {

            Movie movie = movieList.get(i);

            //Poster is in URL thats why  we are converted into a String and use picasso
            String posterLink = movie.getPoster();

            viewHolder.title.setText(movie.getTitle());
            viewHolder.category.setText(movie.getMovietype());
            viewHolder.year.setText(movie.getYear());

            // TODO:Set image using picasso and place holder when url image not showing...
            Picasso.with(context)
                    .load(posterLink)
                    .placeholder(R.drawable.imageholder)
                    .into(viewHolder.poster);
        }


    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title, year, category;
        ImageView poster;

        public ViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);

            context = ctx;


            title = itemView.findViewById(R.id.movietitle);
            year = itemView.findViewById(R.id.movierelease);
            category = itemView.findViewById(R.id.moviecat);
            poster = itemView.findViewById(R.id.movieimage);


            // To go to Next Screen After link on movie row list ...
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Movie movie = movieList.get(getAdapterPosition());

                    Intent intent = new Intent(context, MovieDetailActivity.class);
                    intent.putExtra("movie", movie);
                    context.startActivity(intent);


                }
            });
        }

        @Override
        public void onClick(View view) {

        }
    }
}
