package id.vincenttp.popularmovie.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import id.vincenttp.popularmovie.R;

/**
 * Created by DELL on 5/16/2017.
 */

public class Review extends RecyclerView.ViewHolder {
    public TextView author;
    public TextView content;

    public Review(View itemView) {
        super(itemView);
        author = (TextView) itemView.findViewById(R.id.author);
        content = (TextView) itemView.findViewById(R.id.content);
    }
}
