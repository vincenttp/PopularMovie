package id.vincenttp.popularmovie.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DELL on 5/14/2017.
 */

public class PopularMovieModel {

    @SerializedName("page")
    public int page;
    @SerializedName("results")
    public List<Results> results;
    @SerializedName("total_results")
    public int total_results;
    @SerializedName("total_pages")
    public int total_pages;

    public static class Results {
        @SerializedName("poster_path")
        public String poster_path;
        @SerializedName("adult")
        public boolean adult;
        @SerializedName("overview")
        public String overview;
        @SerializedName("release_date")
        public String release_date;
        @SerializedName("genre_ids")
        public List<Integer> genre_ids;
        @SerializedName("id")
        public int id;
        @SerializedName("original_title")
        public String original_title;
        @SerializedName("original_language")
        public String original_language;
        @SerializedName("title")
        public String title;
        @SerializedName("backdrop_path")
        public String backdrop_path;
        @SerializedName("popularity")
        public double popularity;
        @SerializedName("vote_count")
        public int vote_count;
        @SerializedName("video")
        public boolean video;
        @SerializedName("vote_average")
        public double vote_average;
    }
}
