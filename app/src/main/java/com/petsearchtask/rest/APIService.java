package com.petsearchtask.rest;


import com.petsearchtask.model.GetMoviesDetailResponse;
import com.petsearchtask.model.GetMoviesResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    @GET("movie")
    Observable<GetMoviesResponse> getMovies(@Query("sort_by") String sort_by,
                                            @Query("api_key") String api_key);

    @GET("movie/{movie_id}")
    Call<GetMoviesDetailResponse> getMovieDetails(@Path("movie_id") int movieId, @Query("api_key") String apiKey, @Query("append_to_response") String credits);

}