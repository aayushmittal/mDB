package com.example.app.themoviedb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import java.io.Serializable;
import java.util.ArrayList;

public class GridActivity extends AppCompatActivity implements GridViewFragment.gridClick,Serializable {
    Movie movie;
    ArrayList<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector_list);
        Intent i = getIntent();
        String url = i.getStringExtra("url");

        if (url == null) {
            this.movie = (Movie) i.getSerializableExtra("movie");
            this.movies = (ArrayList<Movie>) i.getSerializableExtra("movies");
            GridViewFragment gridViewFragment = new GridViewFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("movies", this.movies);
            bundle.putSerializable("activity", this);
            bundle.putString("activityName", "GridActivity");
            gridViewFragment.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.frame_for_grid, gridViewFragment).commit();
            FrameLayout frameLayout = (FrameLayout)findViewById(R.id.frame_for_details);
            if (this.movie != null && frameLayout!=null) {
                DetailFragment detailFragment = new DetailFragment();
                Bundle b = new Bundle();
                b.putSerializable("movie", this.movie);
                b.putSerializable("activity",this);
                b.putString("activityName", "GridActivity");
                detailFragment.setArguments(b);
                getFragmentManager().beginTransaction().replace(R.id.frame_for_details, detailFragment).commit();
            }


        } else {

            GridViewFragment gridViewFragment = new GridViewFragment();
            Bundle bundle = new Bundle();
            bundle.putString("url", url);
            bundle.putSerializable("activity",this);
            bundle.putString("activityName","GridActivity");
            gridViewFragment.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.frame_for_grid, gridViewFragment).commit();
        }
    }

    @Override
    public void onGridItemClick(Movie movie, ArrayList<Movie> movies) {

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frame_for_details);
        this.movie = movie;
        this.movies = movies;
        if(frameLayout!=null){
            DetailFragment detailFragment = new DetailFragment();
            Bundle b = new Bundle();
            b.putSerializable("movie", this.movie);
            b.putSerializable("activity",this);
            b.putString("activityName","GridActivity");
            detailFragment.setArguments(b);
            getFragmentManager().beginTransaction().replace(R.id.frame_for_details, detailFragment).commit();

        }else{
            Intent i = new Intent();
            i.putExtra("movie",movie);
            i.setClass(this, DetailsActivity.class);
            startActivity(i);
        }

    }


}