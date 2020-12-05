package com.azamat;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Room;

import com.azamat.data.db.QuizDataBase;
import com.azamat.data.local.HistoryStorage;
import com.azamat.data.local.IHistoryStorage;
import com.azamat.data.local.QuizRepository;
import com.azamat.data.network.QuizApiClient;

import java.util.Objects;

public class App extends Application {
    public static QuizApiClient apiClient;
    public static QuizRepository repository;
    public static QuizDataBase db;
    public static String theme = "str";
    public static final String SHARED = "shared";
    public static SharedPreferences sp = null;


    @Override
    public void onCreate() {
        super.onCreate();
        apiClient = new QuizApiClient();
        IHistoryStorage historyStorage = new HistoryStorage();

        repository = new QuizRepository(apiClient, historyStorage);

        db = Room.databaseBuilder(getApplicationContext(),
                QuizDataBase.class, "Quizdb").allowMainThreadQueries().build();
        sp = Objects.requireNonNull(getSharedPreferences(SHARED, Context.MODE_PRIVATE));


    }

    public static int setMyTheme(int theme) {
        switch (theme) {
            case 0:
                return R.style.Night;
        }
        return R.style.AppTheme;
    }


}
