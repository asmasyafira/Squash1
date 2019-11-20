package com.example.squash1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.squash1.R;
import com.example.squash1.model.MainModel;

import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {

    int[] img;
    private CheckboxCheckedListener checkedListener;

    public GenreAdapter(int[] img) {
        this.img = img;
    }

    @NonNull
    @Override
    public GenreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_genre, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreAdapter.ViewHolder holder, int position) {
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkedListener != null) {
                    checkedListener.getCheckBoxCheckedListener(position);
                }
            }
        });

        Glide.with(holder.itemView.getContext())
                .load(img[position])
                .into(holder.imgIcon);
    }

    @Override
    public int getItemCount() {
        return img.length;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgIcon;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.cb_genre);
            imgIcon = itemView.findViewById(R.id.img_genre);
        }

    }

    public interface CheckboxCheckedListener {

        void getCheckBoxCheckedListener(int position);
    }

    public void setCheckedListener(CheckboxCheckedListener checkedListener) {
        this.checkedListener = checkedListener;
    }



}
