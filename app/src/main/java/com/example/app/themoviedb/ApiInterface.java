package com.example.app.themoviedb;



import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by aayush on 31/3/16.
 */
public interface ApiInterface {
    @GET("movie/{category}")
    Call<JSONObject> getMovies(@Path("category") String cat, @Query("api_key" )String apiKey);
}
