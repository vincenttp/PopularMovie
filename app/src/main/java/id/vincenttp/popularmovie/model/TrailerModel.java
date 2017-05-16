package id.vincenttp.popularmovie.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DELL on 5/14/2017.
 */

public class TrailerModel {

    @SerializedName("id")
    public int id;
    @SerializedName("results")
    public List<Results> results;

    public static class Results {
        @SerializedName("id")
        public String id;
        @SerializedName("iso_639_1")
        public String iso_639_1;
        @SerializedName("iso_3166_1")
        public String iso_3166_1;
        @SerializedName("key")
        public String key;
        @SerializedName("name")
        public String name;
        @SerializedName("site")
        public String site;
        @SerializedName("size")
        public int size;
        @SerializedName("type")
        public String type;
    }
}
