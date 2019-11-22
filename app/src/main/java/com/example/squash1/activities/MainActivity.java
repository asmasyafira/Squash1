package com.example.squash1.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.squash1.R;
import com.example.squash1.SharedPreferencesManager;
import com.example.squash1.adapter.DiscoverAdapter;
import com.example.squash1.fragment.DiscoverFragment;
import com.example.squash1.fragment.HomeFragment;
import com.example.squash1.fragment.PostFragment;
import com.example.squash1.fragment.ProfileFragment;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navView;
    private Fragment fragmentContent = new HomeFragment();
    String title = "Home";
    ImageView iconFav;
    Button btnOut;
    SharedPreferencesManager sharedPreferencesManager;
    GoogleSignInClient googleSignInClient;
//    Integer isFav;


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
        iconFav = findViewById(R.id.icon_fav);
//        iconFav.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                postFav();
//            }
//        });
    }

//    private void postFav() {
//        if (isFav == 0){
//            iconFav.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_border));
//        } else {
//            iconFav.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite));
//        }
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
//        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("Search");


        super.onCreateOptionsMenu(menu);
        return true;
    }

//    @Override
//    public boolean onQueryTextSubmit(String query) {
//        return true;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String newText) {
//        if (newText == null || newText.trim().isEmpty()){
//            return false;
//        }
//
//        List<String> filteredValues = new ArrayList<String>(mAllValues);
//        for (String value : mAllValues){
//            if (!value.toLowerCase().contains(newText.toLowerCase())){
//                filteredValues.remove(value);
//            }
//        }
//
//        discoverAdapter = new DiscoverAdapter();
//        return false;
//
//
//    }




    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.option_language) {

            Intent i = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);


    }


}


