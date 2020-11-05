package com.azamat.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.azamat.R;
import com.azamat.adapters.FragmentAdapter;
import com.azamat.ui.fragments.History.HistoryFragment;
import com.azamat.ui.fragments.Main.MainFragment;
import com.azamat.ui.fragments.Settings.SettingsFragment;
import com.azamat.viewPager.NonViewPagerSwipe;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private NonViewPagerSwipe viewPager;
    private ArrayList<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        fragmentList = new ArrayList<>();
        bottomNavigationView = findViewById(R.id.main_navigation);
        viewPager = findViewById(R.id.main_viewpager);

        fillFragments();

        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragmentList));


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.main_item){
                    viewPager.setCurrentItem(0);
                } else if (item.getItemId() == R.id.history_item){
                    viewPager.setCurrentItem(1);
                } else if (item.getItemId() == R.id.settings_item){
                    viewPager.setCurrentItem(2);
                }

                return true;
            }
        });

    }

    private void fillFragments() {
        fragmentList.add(new MainFragment());
        fragmentList.add(new HistoryFragment());
        fragmentList.add(new SettingsFragment());
    }
}