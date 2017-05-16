package id.vincenttp.popularmovie.service;

import id.vincenttp.popularmovie.model.PopularMovieModel;
import id.vincenttp.popularmovie.model.ReviewModel;
import id.vincenttp.popularmovie.model.TrailerModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by DELL on 5/14/2017.
 */

public interface InterfaceMovieCall {
    @GET
    Call<PopularMovieModel> getPopularMovie(@Url String url);

    @GET
    Call<PopularMovieModel> getTopRatedMovie(@Url String url);

    @GET
    Call<TrailerModel> getTrailer(@Url String url);

    @GET
    Call<ReviewModel> getReviews(@Url String url);
}
