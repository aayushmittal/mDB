package com.example.app.themoviedb;

import android.content.Intent;
import android.support.v4.app.BundleCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Serializable;

public class DetailsActivity extends AppCompatActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent i = getIntent();
        Movie movie =(Movie)i.getSerializableExtra("movie");
        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("movie",movie);
        bundle.putSerializable("activity",this);
        bundle.putString("activityName", "DetailsActivity");
        detailFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.frame_for_details,detailFragment).commit();

    }

}
