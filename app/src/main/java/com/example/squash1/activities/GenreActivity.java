package com.example.squash1.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.example.squash1.R;
import com.example.squash1.adapter.GenreAdapter;
import com.example.squash1.adapter.ProfileAdapter;

public class GenreActivity extends AppCompatActivity {

    Button btnMulai;
    RecyclerView recyclerView;
    RadioGroup rgGenre;
    int[] imgIcon;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);
        recyclerView = findViewById(R.id.rv_genre);
//        rgGenre = findViewById(R.id.rg_genre);
        btnMulai = findViewById(R.id.btn_mulai);
        imgIcon = new int[]{
                R.drawable.gambar_satu,
                R.drawable.gambar_dua,
                R.drawable.gambar_tiga,
                R.drawable.gambar_empat,
                R.drawable.gambar_lime,
                R.drawable.gambar_enam,
                R.drawable.gambar_tujuh,
                R.drawable.gambar_delapan,
                R.drawable.gambar_sembilan,
                R.drawable.gambar_sepuluh

        };

//        GenreAdapter genreAdapter = new GenreAdapter(imgIcon,rgGenre);
        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(genreAdapter);
        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        btnMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GenreActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
