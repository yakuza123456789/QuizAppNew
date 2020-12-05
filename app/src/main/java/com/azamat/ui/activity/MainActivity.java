package com.azamat.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.azamat.App;
import com.azamat.R;
import com.azamat.data.adapter.pager.FragmentAdapter;
import com.azamat.ui.activity.question.QuestionActivity;
import com.azamat.ui.fragment.history.HistoryFragment;
import com.azamat.ui.fragment.main.MainFragment;
import com.azamat.ui.fragment.main.OnClickListenerMainFragment;
import com.azamat.ui.fragment.setting.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickListenerMainFragment {

    private BottomNavigationView bottomNavigationView;
    protected ViewPager viewPager;
    protected List<Fragment> list;
    HistoryFragment historyFragment = new HistoryFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = App.sp;
        setTheme(App.setMyTheme(sp.getInt(SettingFragment.THEME, 20)));

        setContentView(R.layout.main_activity);
        init();
        fillFragment();
        bottomNavView();
        setPageAdapter();
    }

    private void setPageAdapter() {
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), list));
        viewPager.setOffscreenPageLimit(3);
    }

    private void fillFragment() {
        if (list != null) {
            list.add(new MainFragment());
            list.add(historyFragment);
            list.add(new SettingFragment());
        }
    }

    @SuppressLint("NonConstantResourceId")
    private void bottomNavView() {
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_main:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.menu_history:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.menu_settings:
                    viewPager.setCurrentItem(2);
                    break;
            }
            return true;
        });
    }


    private void init() {
        list = new ArrayList<>();
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        viewPager = findViewById(R.id.viewPager);
    }

    @Override
    public void openActivity(int id, int category, String categoryStr, String difficulty) {
        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putExtra(MainFragment.ID, id);
        intent.putExtra(MainFragment.CATEGORY, category);
        intent.putExtra(MainFragment.DIFFICULTY, difficulty);
        intent.putExtra(MainFragment.CATEGORY_STR, categoryStr);
        startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);

    }
}