package id.vincenttp.popularmovie.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import id.vincenttp.popularmovie.R;
import id.vincenttp.popularmovie.helper.Constanta;
import id.vincenttp.popularmovie.model.PopularMovieModel;
import id.vincenttp.popularmovie.view.activity.MovieDetail;
import id.vincenttp.popularmovie.view.holder.Poster;

/**
 * Created by DELL on 5/14/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<Poster>{
    PopularMovieModel popularMovieModel;
    Context context;
    String baseUrlImage = "http://image.tmdb.org/t/p/w185/";

    public MovieAdapter(PopularMovieModel popularMovieModel, Context context) {
        this.popularMovieModel = popularMovieModel;
        this.context = context;
    }

    @Override
    public Poster onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_poster, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClicked(viewType);
            }
        });
        Poster item= new Poster(view);
        return item;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(Poster holder, int position) {
        ImageView imageView = holder.posters;
        Glide.with(context)
                .load(baseUrlImage+popularMovieModel.results.get(position).poster_path)
                .thumbnail( 0.1f )
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return popularMovieModel.results.size();
    }


    private void onClicked(int pos){
        Intent intent = new Intent(context, MovieDetail.class);
        intent.putExtra(Constanta.EXTRA_TITLE, popularMovieModel.results.get(pos).original_title);
        intent.putExtra(Constanta.EXTRA_VOTE, String.valueOf(popularMovieModel.results.get(pos).vote_average));
        intent.putExtra(Constanta.EXTRA_DATE, popularMovieModel.results.get(pos).release_date);
        intent.putExtra(Constanta.EXTRA_IMAGE, baseUrlImage+popularMovieModel.results.get(pos).poster_path);
        intent.putExtra(Constanta.EXTRA_SINOPSIS, popularMovieModel.results.get(pos).overview);
        intent.putExtra(Constanta.EXTRA_ID, popularMovieModel.results.get(pos).id);
        intent.putExtra(Constanta.EXTRA_IS_FAV, false);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
