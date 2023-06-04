package com.example.a23b_11345a_l13.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a23b_11345a_l13.Model.Movie;
import com.example.a23b_11345a_l13.R;
import com.example.a23b_11345a_l13.Utilities.ImageLoader;
import com.example.a23b_11345a_l13.Utilities.TimeFormatter;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private ArrayList<Movie> movies;



    public MovieAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("passed VT:", "" + viewType);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        MovieViewHolder movieViewHolder = new MovieViewHolder(view);
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = getItem(position);
        holder.movie_LBL_title.setText(movie.getTitle());
        holder.movie_LBL_year.setText(movie.getYear() + "");
        holder.movie_LBL_duration.setText(TimeFormatter.getFormattedTime(movie.getDuration()));
        holder.movie_LBL_reviews.setText(movie.getReviews() + " Reviews");
        holder.movie_RTG_rating.setRating(movie.getRating() / 20);
        ImageLoader.getInstance().loadImage(movie.getImage(), holder.movie_IMG_poster);
        if (movie.isFavorite())
            holder.movie_IMG_favorite.setImageResource(R.drawable.heart);
        else
            holder.movie_IMG_favorite.setImageResource(R.drawable.heart_empty);
    }

    @Override
    public int getItemCount() {
        return this.movies == null ? 0 : movies.size();
    }

    private Movie getItem(int position) {
        return this.movies.get(position);
    }

    public void updateMovies(ArrayList<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        private ShapeableImageView movie_IMG_favorite;
        private AppCompatRatingBar movie_RTG_rating;
        private MaterialTextView movie_LBL_reviews;
        private MaterialTextView movie_LBL_duration;
        private MaterialTextView movie_LBL_year;
        private MaterialTextView movie_LBL_title;
        private ShapeableImageView movie_IMG_poster;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movie_IMG_favorite = itemView.findViewById(R.id.movie_IMG_favorite);
            movie_RTG_rating = itemView.findViewById(R.id.movie_RTG_rating);
            movie_LBL_reviews = itemView.findViewById(R.id.movie_LBL_reviews);
            movie_LBL_duration = itemView.findViewById(R.id.movie_LBL_duration);
            movie_LBL_year = itemView.findViewById(R.id.movie_LBL_year);
            movie_LBL_title = itemView.findViewById(R.id.movie_LBL_title);
            movie_IMG_poster = itemView.findViewById(R.id.movie_IMG_poster);
        }
    }
}
