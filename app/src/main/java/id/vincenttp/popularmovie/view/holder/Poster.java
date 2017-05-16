package id.vincenttp.popularmovie.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import id.vincenttp.popularmovie.R;

/**
 * Created by DELL on 5/14/2017.
 */

public class Poster extends RecyclerView.ViewHolder{
    public ImageView posters;

    public Poster(View itemView) {
        super(itemView);
        posters = (ImageView) itemView.findViewById(R.id.img_poster);
    }
}
