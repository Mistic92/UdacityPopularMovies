package com.lukaszbyjos.popularmovies.utils;

import com.lukaszbyjos.popularmovies.dto.Movie;

import java.util.Collections;
import java.util.List;

/**
 * Created by Lukasz on 18.03.2016.
 */
public class MoviesSingleton {
    private static List<Movie> movieList = Collections.emptyList();

    private MoviesSingleton() {
    }

    public static Movie getMovieItem(final int index) {
        return movieList.get(index);
    }

    public static int getItemCounts() {
        return movieList.size();
    }

    public static List<Movie> getMovieList() {
        return movieList;
    }

    public static void setMovieList(List<Movie> movieList) {
        MoviesSingleton.movieList = movieList;
    }
}
