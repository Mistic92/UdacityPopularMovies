package com.lukaszbyjos.popularmovies.utils;

import com.lukaszbyjos.popularmovies.dto.MovieMain;

import java.util.Collections;
import java.util.List;

/**
 * Created by Lukasz on 18.03.2016.
 */
public class MoviesSingleton {
    private static List<MovieMain> movieMainList = Collections.emptyList();

    private MoviesSingleton() {
    }

    public static MovieMain getMovieItem(final int index) {
        return movieMainList.get(index);
    }

    public static int getItemCounts() {
        return movieMainList.size();
    }

    public static List<MovieMain> getMovieMainList() {
        return movieMainList;
    }

    public static void setMovieMainList(List<MovieMain> movieMainList) {
        MoviesSingleton.movieMainList = movieMainList;
    }
}
