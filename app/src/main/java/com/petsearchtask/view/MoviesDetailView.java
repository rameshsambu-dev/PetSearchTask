package com.petsearchtask.view;


import com.petsearchtask.model.GetMoviesDetailResponse;

public interface MoviesDetailView {
    void showToast(String toastMessage);

    void showAlert(String toastMessage);

    void onFinishListener(GetMoviesDetailResponse movie);

    void onFailureListener(Throwable throwable);
}
