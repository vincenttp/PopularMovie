package id.vincenttp.popularmovie.view.activity;

import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.gson.Gson;

import id.vincenttp.popularmovie.R;
import id.vincenttp.popularmovie.data.MovieContract;
import id.vincenttp.popularmovie.helper.Constanta;
import id.vincenttp.popularmovie.model.PopularMovieModel;
import id.vincenttp.popularmovie.service.InterfaceMovieCall;
import id.vincenttp.popularmovie.service.MovieCall;
import id.vincenttp.popularmovie.view.adapter.FavMovieAdapter;
import id.vincenttp.popularmovie.view.adapter.MovieAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
    MovieAdapter movieAdapter;
    FavMovieAdapter favMovieAdapter;
    RecyclerView recyclerView;
    Gson gson;
    String responsess;

    InterfaceMovieCall movieService;

    String TAG = getClass().getSimpleName();
    private static final int TASK_LOADER_ID = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.list_movie);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        movieService = MovieCall.getMovieService();
        gson = new Gson();

        if (savedInstanceState!=null){
            System.out.println(TAG+" savedInstanceState");
            responsess = savedInstanceState.getString(Constanta.BUNDLE_RESPONSE);
            PopularMovieModel popularMovieModel = gson.fromJson(responsess, PopularMovieModel.class);
            movieAdapter = new MovieAdapter(popularMovieModel, getApplicationContext());
            recyclerView.setAdapter(movieAdapter);
        }else {
            System.out.println(TAG+" onCreate");
            getPopularMovie();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getResources().getConfiguration().orientation==2){
            recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 4));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_popular:
                getPopularMovie();
                return true;
            case R.id.menu_rated:
                getTopRatedMovie();
                return true;
            case R.id.menu_fav:
                getFavoriteMoview();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constanta.BUNDLE_RESPONSE, responsess);
    }

    private void getPopularMovie(){
        getSupportActionBar().setTitle(getString(R.string.app_name));
        String url = "popular?api_key="+getString(R.string.api_key);
        movieService.getPopularMovie(url).enqueue(new Callback<PopularMovieModel>() {
            @Override
            public void onResponse(Call<PopularMovieModel> call, Response<PopularMovieModel> response) {
                responsess = gson.toJson(response.body());
                movieAdapter = new MovieAdapter(response.body(), getApplicationContext());
                recyclerView.setAdapter(movieAdapter);
            }

            @Override
            public void onFailure(Call<PopularMovieModel> call, Throwable t) {

            }
        });
    }

    private void getTopRatedMovie(){
        getSupportActionBar().setTitle(getString(R.string.title_top));
        String url = "top_rated?api_key="+getString(R.string.api_key);
        movieService.getTopRatedMovie(url).enqueue(new Callback<PopularMovieModel>() {
            @Override
            public void onResponse(Call<PopularMovieModel> call, Response<PopularMovieModel> response) {
                responsess = gson.toJson(response.body());
                movieAdapter = new MovieAdapter(response.body(), getApplicationContext());
                recyclerView.setAdapter(movieAdapter);
            }

            @Override
            public void onFailure(Call<PopularMovieModel> call, Throwable t) {

            }
        });
    }

    private void getFavoriteMoview(){
        getSupportActionBar().setTitle(getString(R.string.title_fav));
        favMovieAdapter = new FavMovieAdapter(this);
        recyclerView.setAdapter(favMovieAdapter);
        getSupportLoaderManager().restartLoader(TASK_LOADER_ID, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        System.out.println("onCreateLoader true");
        return new AsyncTaskLoader<Cursor>(this) {
            Cursor mTaskData = null;
            @Override
            protected void onStartLoading() {
                if (mTaskData != null) {
                    deliverResult(mTaskData);
                } else {
                    forceLoad();
                }
            }

            @Override
            public Cursor loadInBackground() {
                try {
                    return getContentResolver().query(MovieContract.MovieEntry.CONTENT_URI,
                            null,
                            null,
                            null,
                            MovieContract.MovieEntry._ID);

                } catch (Exception e) {
                    Log.e(TAG, "Failed to asynchronously load data.");
                    e.printStackTrace();
                    return null;
                }
            }

            public void deliverResult(Cursor data) {
                mTaskData = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        favMovieAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        favMovieAdapter.swapCursor(null);
    }
}
