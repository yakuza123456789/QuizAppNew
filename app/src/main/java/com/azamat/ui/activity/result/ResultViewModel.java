package com.azamat.ui.activity.result;

import androidx.lifecycle.ViewModel;

import com.azamat.App;
import com.azamat.data.model.QuizResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class ResultViewModel extends ViewModel {

    public void saveToDB(QuizResult quizResult) {
        App.db.quizDao().insert(quizResult);
    }

    public QuizResult getQuizResult(String stringExtra) {
        Gson gson = new Gson();
        Type type = new TypeToken<QuizResult>() {
        }.getType();
        return gson.fromJson(stringExtra, type);
    }
}
