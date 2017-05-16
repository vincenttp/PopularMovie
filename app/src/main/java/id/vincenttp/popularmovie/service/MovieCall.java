package id.vincenttp.popularmovie.service;

import id.vincenttp.popularmovie.model.PopularMovieModel;

/**
 * Created by DELL on 5/14/2017.
 */

public class MovieCall {
    public MovieCall() {
    }

    public static InterfaceMovieCall getMovieService(){
        return RetrofitClient.getClient().create(InterfaceMovieCall.class);
    }
}
