package com.petsearchtask.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.petsearchtask.R;
import com.petsearchtask.adapter.HomeListAdapter;
import com.petsearchtask.model.GetMoviesResponse;
import com.petsearchtask.presenter.MoviesPresenter;
import com.petsearchtask.view.MoviesView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MoviesView {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.pb_loading)
    ProgressBar pb_loading;

    HomeListAdapter mJobAdapter;
    MoviesPresenter moviesPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        moviesPresenter = new MoviesPresenter(this, this);
        moviesPresenter.getMovies();
    }

    @Override
    public void showToast(String toastMessage) {

    }

    @Override
    public void showAlert(String toastMessage) {

    }

    @Override
    public void success(List<GetMoviesResponse.Result> results) {

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mJobAdapter = new HomeListAdapter(results, MainActivity.this);
        recyclerView.setAdapter(mJobAdapter);
        pb_loading.setVisibility(View.GONE);

    }

    @Override
    public void hideProgress() {
        pb_loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        pb_loading.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_filter);
        item.setVisible(true);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (android.R.id.home == id) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
