package com.example.app.themoviedb;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SelectorFragment.SelectorFragmentInterface, GridViewFragment.gridClick,Serializable{

    ArrayList<Movie> movies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SelectorFragment selectorFragment = new SelectorFragment();
        getFragmentManager().beginTransaction().replace(R.id.frame_for_selector,selectorFragment).commit();
        if(savedInstanceState!=null)
        this.movies = (ArrayList<Movie>)savedInstanceState.getSerializable("movies");
        if(this.movies!=null){
            FrameLayout frameLayoutForGrid = (FrameLayout)findViewById(R.id.frame_for_grid);
            if(frameLayoutForGrid!=null){
                GridViewFragment gridViewFragment = new GridViewFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("movies", this.movies);
                bundle.putSerializable("activity",this);
                bundle.putString("activityName","MainActivity");
                gridViewFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.frame_for_grid,gridViewFragment).commit();
            }
        }
    }

    @Override
    public void onSelectorFragmentClick(String url) {
        FrameLayout frameLayoutForGrid = (FrameLayout)findViewById(R.id.frame_for_grid);
        if(frameLayoutForGrid == null){
            Intent i = new Intent();
            i.putExtra("url", url);
            i.setClass(MainActivity.this, GridActivity.class);
            startActivity(i);
        }
        else{
            GridViewFragment gridViewFragment = new GridViewFragment();
            Bundle bundle = new Bundle();
            bundle.putString("url",url);
            bundle.putSerializable("activity",this);
            bundle.putString("activityName","MainActivity");
            gridViewFragment.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.frame_for_grid,gridViewFragment).commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //save supper later
        outState.putSerializable("movies",this.movies);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onGridItemClick(Movie movie, ArrayList<Movie> movies) {
        Intent i = new Intent();
        i.putExtra("movie",movie);
        i.putExtra("movies",movies);
        this.movies = movies;
        i.setClass(this, GridActivity.class);
        startActivity(i);
    }
}
