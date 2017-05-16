package id.vincenttp.popularmovie.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.vincenttp.popularmovie.R;
import id.vincenttp.popularmovie.model.TrailerModel;
import id.vincenttp.popularmovie.view.holder.Poster;
import id.vincenttp.popularmovie.view.holder.Trailer;

/**
 * Created by DELL on 5/16/2017.
 */

public class TrailerAdapter extends RecyclerView.Adapter<Trailer> {
    TrailerModel trailerModel;
    Context context;

    public TrailerAdapter(TrailerModel trailerModel, Context context) {
        this.trailerModel = trailerModel;
        this.context = context;
    }

    @Override
    public Trailer onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_trailer, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClicked(viewType);
            }
        });
        Trailer item= new Trailer(view);
        return item;
    }

    @Override
    public void onBindViewHolder(Trailer holder, int position) {
        TextView trailerName = holder.trailer_name;
        trailerName.setText(trailerModel.results.get(position).name);
    }

    @Override
    public int getItemCount() {
        return trailerModel.results.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    private void onClicked(int pos){
        String url = "https://www.youtube.com/watch?v="+trailerModel.results.get(pos).key;
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
