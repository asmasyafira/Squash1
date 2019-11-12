package com.example.squash1.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.squash1.Adapter;
import com.example.squash1.DataHome;
import com.example.squash1.R;
import com.example.squash1.activities.MainActivity;
import com.example.squash1.model.HomeModel;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private ArrayList<HomeModel> listHome;

    String[] txtName;
    int[] img;



    public HomeFragment() {
        // Required empty public constructor

        
    }

    private ArrayList<HomeModel> list = new ArrayList<>();

    private void showSelectedHome(HomeModel model) {
        Intent send = new Intent(getContext(), MainActivity.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rvhome);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        Adapter adapter = new Adapter(list);
        list.addAll(DataHome.getListData());
        recyclerView.setAdapter(adapter);

//        txtName = getResources().getStringArray(R.string.name)

        adapter.setOnItemClickCallback(new Adapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(HomeModel data) {
                showSelectedHome(data);
            }
        });

            return view;

    }


}
