package com.example.squash1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.squash1.R;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ListViewHolder> {

    String[] profileName, profileDesc;
    int[] profileImg;
    FragmentActivity fragmentActivity;

    public ProfileAdapter(String[] profileName, String[] profileDesc, int[] profileImg, FragmentActivity fragmentActivity) {
        this.profileName = profileName;
        this.profileDesc = profileDesc;
        this.profileImg = profileImg;
        this.fragmentActivity = fragmentActivity;
    }

    @NonNull
    @Override
    public ProfileAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_profile, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProfileAdapter.ListViewHolder listViewHolder, int i) {
        listViewHolder.TvName.setText(profileName[i]);
        listViewHolder.TvDesc.setText(profileDesc[i]);

        Glide.with(fragmentActivity)
                .load(profileImg[i])
                .into(listViewHolder.image);

    }

    @Override
    public int getItemCount() {
        return profileImg.length;
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView TvName;
        TextView TvDesc;
        ImageView image;

        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.img_poster);
            TvName = itemView.findViewById(R.id.tv_judul);
            TvDesc = itemView.findViewById(R.id.tv_desc);
        }
    }
}
