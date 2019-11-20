package com.example.squash1.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.squash1.adapter.HomeAdapter;
import com.example.squash1.R;
import com.example.squash1.activities.MainActivity;
import com.example.squash1.model.MainModel;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    String[] txtName, txtDesc;
    int[] img;


    public HomeFragment() {
        // Required empty public constructor

        
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.rvhome);
        txtName = getResources().getStringArray(R.array.home_name);
        txtDesc = getResources().getStringArray(R.array.home_desc);

        img = new int[]{

                R.drawable.gambar_satu,
                R.drawable.gambar_dua,
                R.drawable.gambar_tiga,
                R.drawable.gambar_empat,
                R.drawable.gambar_lime,
                R.drawable.gambar_enam,
                R.drawable.gambar_tujuh,
                R.drawable.gambar_delapan,
                R.drawable.gambar_sembilan,
                R.drawable.gambar_sepuluh,
                R.drawable.gambar_sebelas,
                R.drawable.gambar_duabelas,
                R.drawable.gambar_tigabelas,
                R.drawable.gambar_empatbelas
        };

        HomeAdapter homeAdapter = new HomeAdapter(txtName, txtDesc, img, getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setAdapter(homeAdapter);


        return view;

    }


}
