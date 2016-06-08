package com.example.app.themoviedb;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by aayush on 30/3/16.
 */
public class SelectorFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.selector_layout,container,false);
        final SelectorFragmentInterface listener = (SelectorFragmentInterface)getActivity();
        FrameLayout top_rated = (FrameLayout)v.findViewById(R.id.top_rated);
        FrameLayout now_playing = (FrameLayout)v.findViewById(R.id.now_playing);
        FrameLayout popular = (FrameLayout)v.findViewById(R.id.popular);
        top_rated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSelectorFragmentClick("top_rated");
            }
        });
        now_playing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSelectorFragmentClick("now_playing");
            }
        });
        popular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSelectorFragmentClick("popular");
            }
        });
        return v;
    }
    public interface SelectorFragmentInterface{
        void onSelectorFragmentClick(String url);
    }
}
