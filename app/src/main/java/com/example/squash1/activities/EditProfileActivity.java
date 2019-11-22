package com.example.squash1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.squash1.R;
import com.example.squash1.fragment.ProfileFragment;
import com.google.firebase.auth.FirebaseAuth;

public class EditProfileActivity extends AppCompatActivity {

    FirebaseAuth fAuth;
    Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        btnLogout = findViewById(R.id.btn_logout_edit_profile);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });

    }

    private void logOut() {
        fAuth.getInstance().signOut();
        Intent log = new Intent(EditProfileActivity.this, SignInActivity.class);
        Toast.makeText(this, "Keluar", Toast.LENGTH_SHORT).show();
        startActivity(log);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
