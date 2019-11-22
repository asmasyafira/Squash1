package com.example.squash1.fragment;


import android.app.SearchManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.squash1.R;
import com.example.squash1.adapter.DiscoverAdapter;
import com.example.squash1.adapter.HomeAdapter;
import com.example.squash1.adapter.ProfileAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment {

    RecyclerView recyclerView;
    String[] tvGenreDis;
    int[] imgGenreDis;
    private GridLayoutManager gridLayoutManager;
    DiscoverAdapter discoverAdapter;

    public DiscoverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        recyclerView = view.findViewById(R.id.rv_genre_discover);
        tvGenreDis = getResources().getStringArray(R.array.name_genre);
        imgGenreDis = new int[]{
                R.drawable.painting,
                R.drawable.statue,
                R.drawable.relief,
                R.drawable.photography,
                R.drawable.graphic,
                R.drawable.mask,
                R.drawable.caligraphy,
                R.drawable.carving,
                R.drawable.mozaik,
                R.drawable.keramik
        };

        DiscoverAdapter discoverAdapter = new DiscoverAdapter(imgGenreDis, tvGenreDis);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(discoverAdapter);
        gridLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);


        return view;


    }
}
