package com.example.app.themoviedb;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by aayush on 30/3/16.
 */
public class GridViewFragment extends Fragment {
    ArrayList<Movie> movies;
    gridClick listener;
    String url;
    String apiKey="c08bc521c778c50ba9f47682d595d123";
    JSONObject object;
    GridAdapter adapter;
    GridActivity gridActivity = null;
    MainActivity mainActivity = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.grid_fragment_layout, container, false);
        final GridView gridView= (GridView) v.findViewById(R.id.grid_view);
        Bundle bundle = getArguments();

        String activityString = bundle.getString("activityName");
        if(activityString == "GridActivity"){
            gridActivity = (GridActivity)bundle.getSerializable("activity");
        }
        else{
            mainActivity = (MainActivity)bundle.getSerializable("activity");
        }
        this.url = bundle.getString("url");
        if(this.url == null){
            this.movies = (ArrayList<Movie>)bundle.getSerializable("movies");
            if(gridActivity!=null){
                adapter = new GridAdapter(gridActivity,movies);
            }else{
                adapter = new GridAdapter(mainActivity,movies);
            }

            gridView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }else{
            Call<JSONObject> call = ApiClient.getInterface().getMovies(this.url,this.apiKey);
            call.enqueue(new Callback<JSONObject>() {
                @Override
                public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                    if (response.isSuccessful()) {
                        object = response.body();
                        movies = object.results;
                        if(gridActivity!=null){
                            adapter = new GridAdapter(gridActivity,movies);
                        }else{
                            adapter = new GridAdapter(mainActivity,movies);
                        }
                        gridView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<JSONObject> call, Throwable t) {
                    if(gridActivity!=null){
                        Toast.makeText(gridActivity, "Failed to download", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(mainActivity, "Failed to download", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(gridActivity!=null){
                    listener = gridActivity;
                }else{
                    listener = mainActivity;
                }

                listener.onGridItemClick(movies.get(position),movies);
            }
        });
        return v;
    }
    public interface gridClick{
        void onGridItemClick(Movie movie,ArrayList<Movie> movies);
    }



}
