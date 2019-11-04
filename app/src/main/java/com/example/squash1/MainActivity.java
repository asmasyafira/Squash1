package com.example.squash1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.squash1.fragment.DiscoverFragment;
import com.example.squash1.fragment.HomeFragment;
import com.example.squash1.fragment.PostFragment;
import com.example.squash1.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navView;
    private Fragment fragmentContent = new HomeFragment();
    String title = "Home";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragmentContent = new HomeFragment();
                    title = getResources().getString(R.string.title_home);
                    break;
                case R.id.navigation_discover:
                    fragmentContent = new DiscoverFragment();
                    title = getResources().getString(R.string.discover);
                    break;
                case R.id.navigation_post:
                    fragmentContent = new PostFragment();
                    title = getResources().getString(R.string.post);
                    break;
                case R.id.navigation_profile:
                    fragmentContent = new ProfileFragment();
                    title = getResources().getString(R.string.profile);
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragmentContent).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.bottom_nav_menu, menu);
        return true;
    }

}
