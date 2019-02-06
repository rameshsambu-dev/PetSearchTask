package com.petsearchtask.presenter;

import android.content.Context;
import android.util.Log;

import com.petsearchtask.model.GetMoviesDetailResponse;
import com.petsearchtask.rest.APIClient;
import com.petsearchtask.rest.APIService;
import com.petsearchtask.rest.AppConstants;
import com.petsearchtask.view.MoviesDetailView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;

public class MoviesDetailPresenter {
    private MoviesDetailView view;
    private Context context;

    public MoviesDetailPresenter(Context context, MoviesDetailView view) {
        this.view = view;
        this.context = context;
    }

    public void getMovies(Integer movieId) {

        APIService apiService =
                APIClient.getMovieClient().create(APIService.class);

        Call<GetMoviesDetailResponse> call = apiService.getMovieDetails(movieId, AppConstants.API_KEY, AppConstants.CREDITS);
        call.enqueue(new Callback<GetMoviesDetailResponse>() {
            @Override
            public void onResponse(Call<GetMoviesDetailResponse> call,
                                   Response<GetMoviesDetailResponse> response) {
                Log.d(TAG, "Movie data received: " +response.toString());
                view.onFinishListener(response.body());
            }

            @Override
            public void onFailure(Call<GetMoviesDetailResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
                view.onFailureListener(t);
            }
        });


    }


}
