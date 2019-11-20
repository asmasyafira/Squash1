package com.example.squash1.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import com.example.squash1.R;
import com.example.squash1.adapter.GenreAdapter;
import com.example.squash1.adapter.ProfileAdapter;

public class GenreActivity extends AppCompatActivity implements GenreAdapter.CheckboxCheckedListener {

    Button btnMulai;
    RecyclerView recyclerView;
    CheckBox checkBox;
    int[] imgIcon;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);
        recyclerView = findViewById(R.id.rv_genre);
        checkBox = findViewById(R.id.cb_genre);
        btnMulai = findViewById(R.id.btn_mulai);
//        imgIcon = getResources().getStringArray(R.array.name_genre);
        imgIcon = new int[]{

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

        GenreAdapter genreAdapter = new GenreAdapter(imgIcon);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(genreAdapter);
        genreAdapter.setCheckedListener(this);
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

    @Override
    public void getCheckBoxCheckedListener(int position) {

    }
}
