package com.azamat.ui.fragment.setting;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.azamat.App;
import com.azamat.R;
import com.azamat.data.adapter.theme.ThemeAdapter;
import com.azamat.data.model.ThemeModel;
import com.azamat.ui.activity.SplashActivity;

import java.util.Objects;

public class SettingViewModel extends ViewModel {

    public static final String THEME = "theme";

    public MutableLiveData<Boolean> booleanMutableLiveData = new MutableLiveData<>();


    public void clear(Context context) {
        Toast.makeText(context, "Вы очистили историю!", Toast.LENGTH_SHORT).show();
        App.db.quizDao().deleteAll();
    }

    public void add(String name, ThemeAdapter adapter) {
        adapter.add(new ThemeModel(name, R.drawable.ic_template));
    }

    public void addList(ThemeAdapter adapter) {
        add("Night", adapter);
        add("Light", adapter );
    }

    public void defaultTheme() {
        SharedPreferences sP = App.sp;
        if (sP.getInt(SettingFragment.THEME, 20) == 5)
            sP.edit().putInt(SettingFragment.THEME, 5).apply();
    }

    public void setTheme(Context context, int position) {
        SharedPreferences sP = App.sp;
        if (sP.getInt(SettingFragment.THEME, 20) != position) {
            sP.edit().putInt(THEME, position).apply();
            Objects.requireNonNull(context).startActivity(new Intent(context, SplashActivity.class));
            booleanMutableLiveData.setValue(true);
        } else {
            booleanMutableLiveData.setValue(false);
            Toast.makeText(context, "Вы уже выбрали эту тему!", Toast.LENGTH_SHORT).show();
        }
    }

}