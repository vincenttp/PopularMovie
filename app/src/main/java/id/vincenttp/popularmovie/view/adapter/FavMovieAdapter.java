package id.vincenttp.popularmovie.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import id.vincenttp.popularmovie.R;
import id.vincenttp.popularmovie.data.MovieContract;
import id.vincenttp.popularmovie.helper.Constanta;
import id.vincenttp.popularmovie.view.activity.MovieDetail;
import id.vincenttp.popularmovie.view.holder.Poster;

/**
 * Created by DELL on 5/16/2017.
 */

public class FavMovieAdapter extends RecyclerView.Adapter<Poster> {
    private Context context;
    private Cursor mCursor;
    int idIndex, idMovieIndex, imageIndex, titleIndex, voteIndex, dateIndex, sinopsisIndex;

    public FavMovieAdapter(Context context) {
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
    public void onBindViewHolder(Poster holder, int position) {
        idIndex = mCursor.getColumnIndex(MovieContract.MovieEntry._ID);
        idMovieIndex = mCursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_ID);
        imageIndex = mCursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_IMAGE);
        titleIndex = mCursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_TITLE);
        voteIndex = mCursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_VOTE);
        dateIndex = mCursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_DATE);
        sinopsisIndex = mCursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_SINOPSIS);

        mCursor.moveToPosition(position);

        final int id = mCursor.getInt(idIndex);
        ImageView imageView = holder.posters;
        Glide.with(context)
                .load(mCursor.getString(imageIndex))
                .thumbnail( 0.1f )
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        if (mCursor == null) {
            return 0;
        }
        return mCursor.getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public Cursor swapCursor(Cursor c) {
        // check if this cursor is the same as the previous cursor (mCursor)
        if (mCursor == c) {
            return null; // bc nothing has changed
        }
        Cursor temp = mCursor;
        this.mCursor = c; // new cursor value assigned

        //check if this is a valid cursor, then update the cursor
        if (c != null) {
            this.notifyDataSetChanged();
        }
        return temp;
    }

    private void onClicked(int pos){
        mCursor.moveToPosition(pos);
        Intent intent = new Intent(context, MovieDetail.class);
        intent.putExtra(Constanta.EXTRA_TITLE, mCursor.getString(titleIndex));
        intent.putExtra(Constanta.EXTRA_VOTE, mCursor.getString(voteIndex));
        intent.putExtra(Constanta.EXTRA_DATE, mCursor.getString(dateIndex));
        intent.putExtra(Constanta.EXTRA_IMAGE, mCursor.getString(imageIndex));
        intent.putExtra(Constanta.EXTRA_SINOPSIS, mCursor.getString(sinopsisIndex));
        intent.putExtra(Constanta.EXTRA_ID, mCursor.getInt(idMovieIndex));
        intent.putExtra(Constanta.EXTRA_IS_FAV, true);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
