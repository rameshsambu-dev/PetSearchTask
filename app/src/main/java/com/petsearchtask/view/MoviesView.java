package com.petsearchtask.view;


import com.petsearchtask.model.GetMoviesResponse;

import java.util.List;

public interface MoviesView {
    void showToast(String toastMessage);

    void showAlert(String toastMessage);

    void success(List<GetMoviesResponse.Result> results);

    void hideProgress();

    void showProgress();
}
