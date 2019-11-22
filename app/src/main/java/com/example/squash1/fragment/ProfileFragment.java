package com.example.squash1.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.squash1.R;
import com.example.squash1.activities.EditProfileActivity;
import com.example.squash1.adapter.ProfileAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    RecyclerView recyclerView;
    String[] txtJudul;
    String[] txtDesc;
    int[] imgFrag;
    private GridLayoutManager gridLayoutManager;
    TextView tvEditProfile;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        recyclerView = view.findViewById(R.id.rv_frag_profile);
        tvEditProfile = view.findViewById(R.id.tv_edit_profile);
        tvEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), EditProfileActivity.class));
            }
        });

        txtJudul = getResources().getStringArray(R.array.home_name);
        txtDesc = getResources().getStringArray(R.array.home_desc);
        imgFrag = new int[]{
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

        ProfileAdapter profileAdapter = new ProfileAdapter(txtJudul, txtDesc, imgFrag, getActivity());

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(profileAdapter);
        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        return view;

    }


}
