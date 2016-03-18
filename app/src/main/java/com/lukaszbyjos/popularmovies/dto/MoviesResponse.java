package com.lukaszbyjos.popularmovies.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Lukasz on 17.03.2016.
 */
public class MoviesResponse {

    @JsonProperty("page")
    private int pageNumber;
    @JsonProperty("results")
    private List<MovieMain> movieMainList;
    @JsonProperty("total_results")
    private int totalResults;
    @JsonProperty("total_pages")
    private int totalPages;


    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<MovieMain> getMovieMainList() {
        return movieMainList;
    }

    public void setMovieMainList(List<MovieMain> movieMainList) {
        this.movieMainList = movieMainList;
    }
}
