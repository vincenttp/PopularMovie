package id.vincenttp.popularmovie.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.vincenttp.popularmovie.R;
import id.vincenttp.popularmovie.model.ReviewModel;
import id.vincenttp.popularmovie.view.holder.Poster;
import id.vincenttp.popularmovie.view.holder.Review;

/**
 * Created by DELL on 5/16/2017.
 */

public class ReviewAdapter extends RecyclerView.Adapter<Review> {
    ReviewModel reviewModel;
    Context context;

    public ReviewAdapter(ReviewModel reviewModel, Context context) {
        this.reviewModel = reviewModel;
        this.context = context;
    }

    @Override
    public Review onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_review, parent, false);
        Review item= new Review(view);
        return item;
    }

    @Override
    public void onBindViewHolder(Review holder, int position) {
        TextView author = holder.author;
        TextView content = holder.content;

        author.setText(reviewModel.results.get(position).author);
        content.setText(reviewModel.results.get(position).content);
    }

    @Override
    public int getItemCount() {
        return reviewModel.results.size();
    }
}
