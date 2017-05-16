package id.vincenttp.popularmovie.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import id.vincenttp.popularmovie.R;

/**
 * Created by DELL on 5/16/2017.
 */

public class Trailer extends RecyclerView.ViewHolder {
    public TextView trailer_name;
    public Trailer(View itemView) {
        super(itemView);
        trailer_name = (TextView) itemView.findViewById(R.id.trailer_name);
    }
}
