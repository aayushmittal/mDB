package com.example.app.themoviedb;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by aayush on 30/3/16.
 */
public class Movie implements Serializable {
    String title;

    int id;


    String overview;

    @SerializedName("vote_average")
    String voteAverage;
    @SerializedName("release_date")
    String releaseDate;
    @SerializedName("poster_path")
    String posterUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
}

