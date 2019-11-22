package com.example.squash1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.squash1.R;

public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.ViewHolder> {

    int[] imgGenre;
    String[] nameGenre;

    public DiscoverAdapter(int[] imgGenre, String[] nameGenre) {
        this.imgGenre = imgGenre;
        this.nameGenre = nameGenre;
    }

    @NonNull
    @Override
    public DiscoverAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_discover_genre, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscoverAdapter.ViewHolder holder, int position) {
        holder.tvGenre.setText(nameGenre[position]);
        Glide.with(holder.itemView.getContext())
                .load(imgGenre[position])
                .into(holder.imgGenre);
    }

    @Override
    public int getItemCount() {
        return imgGenre.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvGenre;
        ImageView imgGenre;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGenre = itemView.findViewById(R.id.name_genre_discover);
            imgGenre = itemView.findViewById(R.id.img_genre_discover);
        }
    }


}
