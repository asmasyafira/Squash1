package com.example.squash1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.squash1.R;
import com.example.squash1.model.MainModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ListViewHolder> {

    String[] homeName, homeDesc;
    int[] homeImg;
    FragmentActivity fragmentActivity;

    public HomeAdapter(String[] homeName, String[] homeDesc, int[] homeImg, FragmentActivity fragmentActivity) {
        this.homeName = homeName;
        this.homeDesc = homeDesc;
        this.homeImg = homeImg;
        this.fragmentActivity = fragmentActivity;
    }

    @NonNull
    @Override
    public HomeAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeAdapter.ListViewHolder listViewHolder, int i) {

        listViewHolder.TvName.setText(homeName[i]);
        listViewHolder.TvDesc.setText(homeDesc[i]);

        Glide.with(fragmentActivity)
                .load(homeImg[i])
                .into(listViewHolder.image);

    }


    @Override
    public int getItemCount() {
        return homeImg.length;
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView TvName;
        TextView TvDesc;
        ImageView image;

        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.img_user);
            TvName = itemView.findViewById(R.id.txt_user);
            TvDesc = itemView.findViewById(R.id.txt_caption);
        }
    }
}
