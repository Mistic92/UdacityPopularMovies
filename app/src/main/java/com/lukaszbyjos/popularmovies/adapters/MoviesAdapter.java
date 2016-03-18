package com.lukaszbyjos.popularmovies.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lukaszbyjos.popularmovies.R;
import com.lukaszbyjos.popularmovies.dto.Movie;
import com.lukaszbyjos.popularmovies.utils.MoviesSingleton;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Lukasz on 18.03.2016.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private final Context mContext;

    public MoviesAdapter(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Movie movie = MoviesSingleton.getMovieItem(position);
        Glide.with(mContext)
                .load(Uri.parse(movie.getPosterPath()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.movieImageView);
        holder.setClickListener(new ViewHolder.ClickListener() {

            @Override
            public void onClick(View v, int position) {
                //todo implement activity open
            }
        });
    }

    @Override
    public int getItemCount() {
        return MoviesSingleton.getItemCounts();
    }


    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.movieImageView)
        ImageView movieImageView;

        private ClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void setClickListener(ClickListener clickListener) {
            this.clickListener = clickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getAdapterPosition());
        }

        public interface ClickListener {
            void onClick(View v, int position);
        }
    }
}
