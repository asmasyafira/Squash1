package com.example.squash1.favorite;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.squash1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LikedFavoriteFragment extends Fragment {


    public LikedFavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_liked_favorite, container, false);
    }

}
