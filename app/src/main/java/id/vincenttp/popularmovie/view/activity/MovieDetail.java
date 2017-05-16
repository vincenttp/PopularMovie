package id.vincenttp.popularmovie.view.activity;

import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import id.vincenttp.popularmovie.R;
import id.vincenttp.popularmovie.data.MovieContract;
import id.vincenttp.popularmovie.helper.Constanta;
import id.vincenttp.popularmovie.model.ReviewModel;
import id.vincenttp.popularmovie.model.TrailerModel;
import id.vincenttp.popularmovie.service.InterfaceMovieCall;
import id.vincenttp.popularmovie.service.MovieCall;
import id.vincenttp.popularmovie.util.ItemDevider;
import id.vincenttp.popularmovie.view.adapter.ReviewAdapter;
import id.vincenttp.popularmovie.view.adapter.TrailerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetail extends AppCompatActivity implements View.OnClickListener {
    TextView movieTitle, movieVote, movieDate, movieSinopsis;
    ImageView imageView;
    RecyclerView listTrailer, listReview;
    Button btnFav;

    InterfaceMovieCall movieService;
    TrailerAdapter trailerAdapter;
    ReviewAdapter reviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        movieService = MovieCall.getMovieService();

        initView();
        getTrailerMovie(getIntent().getIntExtra(Constanta.EXTRA_ID, 0));
        getReviewsMoview(getIntent().getIntExtra(Constanta.EXTRA_ID, 0));
    }

    private void initView(){
        movieTitle = (TextView) findViewById(R.id.movie_title);
        movieVote = (TextView) findViewById(R.id.movie_vote);
        movieDate = (TextView) findViewById(R.id.movie_date);
        movieSinopsis = (TextView) findViewById(R.id.movie_sinopsis);
        imageView = (ImageView) findViewById(R.id.movie_poster);
        btnFav = (Button) findViewById(R.id.btn_fav);

        btnFav.setOnClickListener(this);

        listTrailer = (RecyclerView) findViewById(R.id.list_trailer);
        listTrailer.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        listTrailer.addItemDecoration(new ItemDevider(getApplicationContext(), ItemDevider.VERTICAL_LIST));
        listTrailer.setNestedScrollingEnabled(false);

        listReview = (RecyclerView) findViewById(R.id.list_review);
        listReview.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        listReview.setNestedScrollingEnabled(false);

        movieTitle.setText(getIntent().getStringExtra(Constanta.EXTRA_TITLE));
        movieVote.setText(getIntent().getStringExtra(Constanta.EXTRA_VOTE));
        movieDate.setText(getIntent().getStringExtra(Constanta.EXTRA_DATE));
        movieSinopsis.setText(getIntent().getStringExtra(Constanta.EXTRA_SINOPSIS));

        Glide.with(getApplicationContext())
                .load(getIntent().getStringExtra(Constanta.EXTRA_IMAGE))
                .thumbnail( 0.1f )
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(imageView);

        if (getIntent().getBooleanExtra(Constanta.EXTRA_IS_FAV, true)){
            btnFav.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getTrailerMovie(int id){
        String url = id+"/videos?api_key="+getString(R.string.api_key);
        movieService.getTrailer(url).enqueue(new Callback<TrailerModel>() {
            @Override
            public void onResponse(Call<TrailerModel> call, Response<TrailerModel> response) {
                trailerAdapter = new TrailerAdapter(response.body(), getApplicationContext());
                listTrailer.setAdapter(trailerAdapter);
            }

            @Override
            public void onFailure(Call<TrailerModel> call, Throwable t) {

            }
        });
    }

    private void getReviewsMoview(int id){
        String url = id+"/reviews?api_key="+getString(R.string.api_key);
        movieService.getReviews(url).enqueue(new Callback<ReviewModel>() {
            @Override
            public void onResponse(Call<ReviewModel> call, Response<ReviewModel> response) {
                reviewAdapter = new ReviewAdapter(response.body(), getApplicationContext());
                listReview.setAdapter(reviewAdapter);
            }

            @Override
            public void onFailure(Call<ReviewModel> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        ContentValues contentValues = new ContentValues();
        // Put the task description and selected mPriority into the ContentValues
        contentValues.put(MovieContract.MovieEntry.COLUMN_ID, getIntent().getIntExtra(Constanta.EXTRA_ID, 0));
        contentValues.put(MovieContract.MovieEntry.COLUMN_TITLE, getIntent().getStringExtra(Constanta.EXTRA_TITLE));
        contentValues.put(MovieContract.MovieEntry.COLUMN_VOTE, getIntent().getStringExtra(Constanta.EXTRA_VOTE));
        contentValues.put(MovieContract.MovieEntry.COLUMN_DATE, getIntent().getStringExtra(Constanta.EXTRA_DATE));
        contentValues.put(MovieContract.MovieEntry.COLUMN_SINOPSIS, getIntent().getStringExtra(Constanta.EXTRA_SINOPSIS));
        contentValues.put(MovieContract.MovieEntry.COLUMN_IMAGE, getIntent().getStringExtra(Constanta.EXTRA_IMAGE));

        Uri uri = getContentResolver().insert(MovieContract.MovieEntry.CONTENT_URI, contentValues);

        if(uri != null) {
            Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
