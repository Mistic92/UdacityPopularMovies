package com.lukaszbyjos.popularmovies.utils;

import com.lukaszbyjos.popularmovies.dto.MovieMain;
import com.lukaszbyjos.popularmovies.enums.PosterSizes;

import java.util.List;

/**
 * Created by Lukasz on 18.03.2016.
 */
public class MovieUtils {
    private MovieUtils() {
    }


    private static String movieURL = "http://image.tmdb.org/t/p/%s/";

    /**
     * Correct posters url list.
     *
     * @param movieMainList the movie list
     * @param posterSize    the poster size
     * @return the list
     */
    public static List<MovieMain> correctMoviePosterURL(final List<MovieMain> movieMainList, final PosterSizes posterSize) {
        for (MovieMain movieMain : movieMainList) {
            correctMoviePosterURL(movieMain, posterSize);
        }
        return movieMainList;
    }

    public static MovieMain correctMoviePosterURL(final MovieMain movieMain, final PosterSizes posterSize) {
        movieMain.setPosterPath(correctPosterURL(movieMain.getPosterPath(), posterSize));
        return movieMain;
    }

    public static String correctPosterURL(final String posterPath, final PosterSizes posterSize) {
        return String.format(movieURL, posterSize.getPosterSize()) + posterPath;
    }


}
