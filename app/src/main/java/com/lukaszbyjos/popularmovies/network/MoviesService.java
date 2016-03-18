package com.lukaszbyjos.popularmovies.network;

import com.lukaszbyjos.popularmovies.dto.MovieDetails;
import com.lukaszbyjos.popularmovies.dto.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Lukasz on 17.03.2016.
 */
public interface MoviesService {

    @GET("/3/movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") final String apiKey);

    @GET("/3/movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") final String apiKey);

    @GET("/3/movie/{MOVIE_ID}")
    Call<MovieDetails> getMovieDetails(@Path("MOVIE_ID") int movieId, @Query("api_key") final String apiKey);
}
