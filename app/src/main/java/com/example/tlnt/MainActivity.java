package com.example.tlnt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.tlnt.fragments.ComposeFragment;
import com.example.tlnt.fragments.PostsFragment;
import com.example.tlnt.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";


    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
        setContentView(R.layout.activity_main);

        final FragmentManager fragmentManager = getSupportFragmentManager();
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_compose:
                        fragment = new ComposeFragment();
                        break;
                    case R.id.action_profile:
                        fragment = new ProfileFragment();
                        break;
                    default:
                        fragment = new PostsFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        if (ParseUser.getCurrentUser().getBoolean("isTalent")) {
            bottomNavigationView.getMenu().removeItem(R.id.action_compose);
            bottomNavigationView.getMenu().findItem(R.id.action_home).setTitle("Home");
        }
        bottomNavigationView.setSelectedItemId(R.id.action_home);
    }
}