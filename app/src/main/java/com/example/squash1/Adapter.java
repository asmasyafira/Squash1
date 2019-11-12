package com.example.squash1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.squash1.model.HomeModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.ListViewHolder> {
    private ArrayList<HomeModel> listHome;

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }


    public Adapter(ArrayList<HomeModel> listHome) {
        this.listHome = listHome;
    }

    @NonNull
    @Override
    public Adapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Adapter.ListViewHolder listViewHolder, int i) {
        ArrayList<String> recordings = null;
        HomeModel hm = listHome.get(i);

        Glide.with(listViewHolder.itemView.getContext()).load(hm.getPhoto()).apply(new RequestOptions().override(70, 70)).into(listViewHolder.image);

        listViewHolder.TvName.setText(HomeModel.getName());
        listViewHolder.TvDesc.setText(HomeModel.getDescription());

        listViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClicked(listHome.get(listViewHolder.getAdapterPosition()));
            }
        });

    }
    public interface  OnItemClickCallback{
        void onItemClicked(HomeModel data);
    }

    @Override
    public int getItemCount() {
        return listHome.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        public TextView TvName;
        public TextView TvDesc;
        public ImageView image;
        CardView cv;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.ci_list);
            TvName = itemView.findViewById(R.id.tv_name);
            TvDesc = itemView.findViewById(R.id.tv_desc);
            cv = itemView.findViewById(R.id.cr);
        }
    }
}
