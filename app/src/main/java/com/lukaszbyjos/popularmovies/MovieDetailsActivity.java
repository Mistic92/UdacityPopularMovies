package com.lukaszbyjos.popularmovies;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lukaszbyjos.popularmovies.databinding.ActivityMovieDetailsBinding;
import com.lukaszbyjos.popularmovies.dto.MovieDetails;
import com.lukaszbyjos.popularmovies.enums.PosterSizes;
import com.lukaszbyjos.popularmovies.network.RestClient;
import com.lukaszbyjos.popularmovies.utils.MovieUtils;

import hugo.weaving.DebugLog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The type Movie details activity.
 */
public class MovieDetailsActivity extends AppCompatActivity {

    public static final String MOVIE_ID = "MOVIE_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Intent intent = getIntent();
        final int movieId = intent.getIntExtra(MOVIE_ID, -1);
        if (movieId > -1) {
            getTopRatedMovies(movieId);
        }

    }

    @DebugLog
    private void getTopRatedMovies(final int movieId) {
        Call<MovieDetails> call = RestClient.get().getMovieDetails(movieId, BuildConfig.movie_db_api_key);
        call.enqueue(new Callback<MovieDetails>() {
            @Override
            @DebugLog
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                bindData(response.body());
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {

            }
        });
    }

    @DebugLog
    private void bindData(MovieDetails movieDetails) {
        ActivityMovieDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);
        movieDetails.setPosterPath(
                MovieUtils.correctPosterURL(movieDetails.getPosterPath(), PosterSizes.W185));
        binding.setMovie(movieDetails);
    }
}
