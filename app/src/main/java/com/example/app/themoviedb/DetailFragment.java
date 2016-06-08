package com.example.app.themoviedb;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by aayush on 31/3/16.
 */
public class DetailFragment extends Fragment {
    Movie movie;
    TextView movieTitleView;
    TextView releaseDateView;
    TextView voteAverageView;
    TextView overView;
    ImageView imageView;
    DetailsActivity detailsActivity = null;
    GridActivity gridActivity = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.detail_fragment_layout,container,false);
        Bundle bundle = getArguments();
        this.movie = (Movie)bundle.getSerializable("movie");
        String activityString = bundle.getString("activityName");
        if(activityString == "GridActivity"){
            gridActivity = (GridActivity)bundle.getSerializable("activity");
        }
        else{
            detailsActivity = (DetailsActivity)bundle.getSerializable("activity");
        }
        movieTitleView = (TextView) v.findViewById(R.id.movie_title);
        releaseDateView= (TextView) v.findViewById(R.id.release_date);
        voteAverageView=(TextView) v.findViewById(R.id.vote_average);
        overView= (TextView)v.findViewById(R.id.over_view);
        imageView = (ImageView)v.findViewById(R.id.movie_poster);
        movieTitleView.setText(this.movie.getTitle());
        releaseDateView.setText( "    "+ this.movie.getReleaseDate());
        voteAverageView.setText(this.movie.getVoteAverage() + "/10");
        overView.setText(this.movie.getOverview());
        if(gridActivity!=null){
            Picasso.with(gridActivity).load("http://image.tmdb.org/t/p/w500" + this.movie.getPosterUrl()).into(imageView);

        }else{
            Picasso.with(detailsActivity).load("http://image.tmdb.org/t/p/w500" + this.movie.getPosterUrl()).into(imageView);


        }
//        overView.setMovementMethod(new ScrollingMovementMethod());
        return v;
    }
}
