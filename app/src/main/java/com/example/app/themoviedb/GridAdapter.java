package com.example.app.themoviedb;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by aayush on 31/3/16.
 */
public class GridAdapter extends ArrayAdapter<Movie> {
    Context context;
    ArrayList<Movie> movieList;
    public GridAdapter(Context context, ArrayList<Movie> movieList) {
        super(context,0,movieList);
        this.context = context;
        this.movieList = movieList;
    }
    class ViewHolder{
        ImageView imageView;
        TextView textView;
        TextView textView1;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = View.inflate(this.context,R.layout.grid_layout,null);
            ViewHolder vh = new ViewHolder();
            vh.imageView = (ImageView) convertView.findViewById(R.id.image);
             vh.textView = (TextView) convertView.findViewById(R.id.name);
           vh.textView1 = (TextView) convertView.findViewById(R.id.rating);
            convertView.setTag(vh);
        }
        ViewHolder vh = (ViewHolder)convertView.getTag();
        Picasso.with(context).load("http://image.tmdb.org/t/p/w500" + movieList.get(position).getPosterUrl()).into(vh.imageView);
        vh.textView.setText(movieList.get(position).getTitle());
        vh.textView1.setText(movieList.get(position).getVoteAverage());


        return convertView;
    }
}
