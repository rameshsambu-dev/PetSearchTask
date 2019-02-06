package com.petsearchtask.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.petsearchtask.R;
import com.petsearchtask.model.GetMoviesDetailResponse;
import com.petsearchtask.model.GetMoviesResponse;
import com.petsearchtask.presenter.MoviesDetailPresenter;
import com.petsearchtask.rest.AppConstants;
import com.petsearchtask.view.MoviesDetailView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesDetailActivity extends AppCompatActivity implements MoviesDetailView {
    @BindView(R.id.iv_image)
    ImageView iv_image;
    @BindView(R.id.tv_desc)
    TextView tv_desc;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_date)
    TextView tv_date;
    @BindView(R.id.tv_rating)
    TextView tv_rating;
    @BindView(R.id.tv_category)
    TextView tv_category;
    @BindView(R.id.tv_language)
    TextView tv_language;
    @BindView(R.id.tv_budget_value)
    TextView tv_budget_value;
    @BindView(R.id.tv_revenue_value)
    TextView tv_revenue_value;

    GetMoviesResponse.Result result;
    MoviesDetailPresenter moviesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_detail);
        ButterKnife.bind(this);

        result = (GetMoviesResponse.Result) getIntent().getExtras().get("moviedata");
        updateCenterTitle(true, result.getTitle());

        moviesPresenter = new MoviesDetailPresenter(this, this);
        moviesPresenter.getMovies(result.getId());


    }

    @Override
    public void showToast(String toastMessage) {

    }

    @Override
    public void showAlert(String toastMessage) {

    }

    @Override
    public void onFinishListener(GetMoviesDetailResponse movie) {
        try {
            Glide.with(this).load(AppConstants.BASE_IMAGE_URL + movie.getPosterPath()).into(iv_image);
            tv_desc.setText(movie.getOverview());
            tv_date.setText(movie.getReleaseDate());
            tv_rating.setText(String.valueOf(movie.getVoteAverage()));


            tv_time.setText(movie.getRuntime() + " Minutes");
            tv_budget_value.setText("$ " + movie.getBudget());
            tv_revenue_value.setText("$ " + movie.getRevenue());
            tv_language.setText(movie.getOriginalLanguage());


            try {
                String categories = "";
                for (int i = 0; i < movie.getGenres().size(); i++) {
                    if (TextUtils.isDigitsOnly(categories)) {
                        categories = movie.getGenres().get(i).getName();
                    } else {
                        categories = categories + "," + movie.getGenres().get(i).getName();
                    }
                }
                tv_category.setText(categories);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFailureListener(Throwable throwable) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_filter);
        item.setVisible(false);
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

    public void updateCenterTitle(boolean isBackEnabled, String title) {
        try {
            ActionBar actionBar = getSupportActionBar();
            assert actionBar != null;
            actionBar.setTitle(title);
            actionBar.setDisplayHomeAsUpEnabled(isBackEnabled);
            actionBar.setDisplayUseLogoEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
