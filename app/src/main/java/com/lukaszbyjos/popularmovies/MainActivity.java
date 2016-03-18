package com.lukaszbyjos.popularmovies;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.widget.CompoundButton;

import com.lukaszbyjos.popularmovies.adapters.MoviesAdapter;
import com.lukaszbyjos.popularmovies.dto.MovieMain;
import com.lukaszbyjos.popularmovies.dto.MoviesResponse;
import com.lukaszbyjos.popularmovies.enums.PosterSizes;
import com.lukaszbyjos.popularmovies.network.RestClient;
import com.lukaszbyjos.popularmovies.utils.MovieUtils;
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
public class MainActivity extends AppCompatActivity implements Callback<MoviesResponse>, CompoundButton.OnCheckedChangeListener {

    private static boolean DID_CALL = false;
    private static String DID_CALL_STRING = "didCall";
    private static boolean SHOWING_POPULAR = true;
    private static String SHOWING_POPULAR_STRING = "showingPopular";

    private static final String TAG = "LBPMMA";
    @Bind(R.id.moviesRecyclerView)
    RecyclerView moviesRecyclerView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.topRatedSwitch)
    SwitchCompat topRatedSwitch;

    @Override
    @DebugLog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        topRatedSwitch.setOnCheckedChangeListener(this);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            moviesRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            moviesRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        }
        if (!DID_CALL) {
            if (SHOWING_POPULAR)
                getPopularMovies();
            else
                getTopRatedMovies();
        }
        MoviesAdapter moviesAdapter = new MoviesAdapter(this);
        moviesRecyclerView.setAdapter(moviesAdapter);
    }

    @Override
    @DebugLog
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(DID_CALL_STRING, DID_CALL);
        outState.putBoolean(SHOWING_POPULAR_STRING, SHOWING_POPULAR);
    }

    @Override
    @DebugLog
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        DID_CALL = savedInstanceState.getBoolean(DID_CALL_STRING);
        SHOWING_POPULAR = savedInstanceState.getBoolean(SHOWING_POPULAR_STRING);
    }

    private void getPopularMovies() {
        Call<MoviesResponse> call = RestClient.get().getPopularMovies(BuildConfig.movie_db_api_key);
        call.enqueue(this);
    }

    private void getTopRatedMovies() {
        Call<MoviesResponse> call = RestClient.get().getTopRatedMovies(BuildConfig.movie_db_api_key);
        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
        List<MovieMain> movieMainList = response.body().getMovieMainList();
        movieMainList = MovieUtils.correctMoviePosterURL(movieMainList, PosterSizes.W185);
        MoviesSingleton.setMovieMainList(movieMainList);
        moviesRecyclerView.getAdapter().notifyDataSetChanged();
        DID_CALL = true;
    }

    @Override
    public void onFailure(Call<MoviesResponse> call, Throwable t) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean showTopRated) {
        SHOWING_POPULAR = !showTopRated;
        if (showTopRated)
            getTopRatedMovies();
        else
            getPopularMovies();
    }
}
