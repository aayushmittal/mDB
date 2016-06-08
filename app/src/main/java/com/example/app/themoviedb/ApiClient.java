package com.example.app.themoviedb;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aayush on 31/3/16.
 */
public class ApiClient {
    private static ApiInterface mService;

    public static ApiInterface getInterface()
    {
        if (mService == null)
        {
           /* Gson gson = new GsonBuilder()
                    .excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.STATIC)
                    .create();*/
            Gson gson=new GsonBuilder().create();
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();//movie/
            //builder makes retrofit object in it
            //telling retrofit to use gson for conversion into object

            mService = retrofit.create(ApiInterface.class);
        }
        return mService;
    }
}
