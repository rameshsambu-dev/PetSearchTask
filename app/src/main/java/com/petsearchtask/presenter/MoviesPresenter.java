package com.petsearchtask.presenter;

import android.content.Context;

import com.petsearchtask.model.GetMoviesResponse;
import com.petsearchtask.rest.APIClient;
import com.petsearchtask.rest.APIService;
import com.petsearchtask.rest.AppConstants;
import com.petsearchtask.view.MoviesView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MoviesPresenter {
    private MoviesView view;
    private Context context;

    public MoviesPresenter(Context context, MoviesView view) {
        this.view = view;
        this.context = context;
    }

    public void getMovies() {
        view.showProgress();

        APIService appInterface = APIClient.getClient().create(APIService.class);
        appInterface.getMovies("popularity.desc", AppConstants.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<GetMoviesResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(GetMoviesResponse loginResponse) {
                        view.success(loginResponse.getResults());
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgress();
                        System.out.println(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


}
