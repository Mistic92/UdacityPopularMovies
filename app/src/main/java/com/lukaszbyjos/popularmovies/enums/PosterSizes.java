package com.lukaszbyjos.popularmovies.enums;

/**
 * Created by Lukasz on 18.03.2016.
 */
public enum PosterSizes {
    W92("w92"),
    W145("w145"),
    W185("w185"),
    W342("w342"),
    W500("w500"),
    W780("w780"),
    ORIGINAL("original");

    private String posterSize;

    PosterSizes(String posterSize) {
        this.posterSize = posterSize;
    }

    /**
     * Gets poster size.
     *
     * @return the poster size
     */
    public String getPosterSize() {
        return posterSize;
    }
}
