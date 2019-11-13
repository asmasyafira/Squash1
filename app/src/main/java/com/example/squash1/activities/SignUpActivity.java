package com.example.squash1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.squash1.R;
import com.example.squash1.SharedPreferencesManager;

public class SignUpActivity extends AppCompatActivity {

    TextView tvHaveAkun;
    SharedPreferencesManager sharedPreferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        tvHaveAkun = findViewById(R.id.haveAkun);
        tvHaveAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });

        sharedPreferencesManager = new SharedPreferencesManager(this);

        if (sharedPreferencesManager.getSpSigned()) {
            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
            finish();
        }
    }
}
