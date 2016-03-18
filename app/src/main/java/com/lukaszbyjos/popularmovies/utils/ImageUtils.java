package com.lukaszbyjos.popularmovies.utils;

import com.lukaszbyjos.popularmovies.dto.Movie;
import com.lukaszbyjos.popularmovies.enums.PosterSizes;

import java.util.List;

/**
 * Created by Lukasz on 18.03.2016.
 */
public class ImageUtils {
    private ImageUtils() {
    }


    private static String movieURL = "http://image.tmdb.org/t/p/%s/";

    /**
     * Correct posters url list.
     *
     * @param movieList  the movie list
     * @param posterSize the poster size
     * @return the list
     */
    public static List<Movie> correctPostersURL(final List<Movie> movieList, final PosterSizes posterSize) {
        for (Movie movie : movieList) {
            final String path = String.format(movieURL, posterSize.getPosterSize()) + movie.getPosterPath();
            movie.setPosterPath(path);
        }
        return movieList;
    }
}
