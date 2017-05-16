package id.vincenttp.popularmovie.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DELL on 5/14/2017.
 */

public class ReviewModel {

    @SerializedName("id")
    public int id;
    @SerializedName("page")
    public int page;
    @SerializedName("results")
    public List<Results> results;
    @SerializedName("total_pages")
    public int total_pages;
    @SerializedName("total_results")
    public int total_results;

    public static class Results {
        @SerializedName("id")
        public String id;
        @SerializedName("author")
        public String author;
        @SerializedName("content")
        public String content;
        @SerializedName("url")
        public String url;
    }
}
