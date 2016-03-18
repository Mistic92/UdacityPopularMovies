package com.lukaszbyjos.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.lukaszbyjos.popularmovies.adapters.MoviesAdapter;
import com.lukaszbyjos.popularmovies.dto.Movie;
import com.lukaszbyjos.popularmovies.dto.MoviesResponse;
import com.lukaszbyjos.popularmovies.enums.PosterSizes;
import com.lukaszbyjos.popularmovies.network.RestClient;
import com.lukaszbyjos.popularmovies.utils.ImageUtils;
import com.lukaszbyjos.popularmovies.utils.MoviesSingleton;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import hugo.weaving.DebugLog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity {

    private static boolean DID_CALL = false;
    private static String DID_CALL_STRING = "DID_CALL";

    private static final String TAG = "LBPMMA";
    @Bind(R.id.moviesRecyclerView)
    RecyclerView moviesRecyclerView;

    @Override
    @DebugLog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        moviesRecyclerView.setLayoutManager(gridLayoutManager);
        if (!DID_CALL)
            getPopularMovies();
        MoviesAdapter moviesAdapter = new MoviesAdapter(this);
        moviesRecyclerView.setAdapter(moviesAdapter);
    }

    @Override
    @DebugLog
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(DID_CALL_STRING, DID_CALL);
    }

    @Override
    @DebugLog
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        DID_CALL = savedInstanceState.getBoolean(DID_CALL_STRING);
    }

    private void getPopularMovies() {
        Call<MoviesResponse> call = RestClient.get().getPopularMovies(BuildConfig.movie_db_api_key);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            @DebugLog
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                Log.d(TAG, "onResponse: ");
                List<Movie> movieList = response.body().getMovieList();
                movieList = ImageUtils.correctPostersURL(movieList, PosterSizes.W185);
                MoviesSingleton.setMovieList(movieList);
                moviesRecyclerView.getAdapter().notifyDataSetChanged();
                DID_CALL = true;
            }

            @Override
            @DebugLog
            public void onFailure(Call<MoviesResponse> call, Throwable t) {

            }
        });
    }


}
