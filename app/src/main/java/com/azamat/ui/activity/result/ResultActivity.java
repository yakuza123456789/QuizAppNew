package com.azamat.ui.activity.result;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.azamat.App;
import com.azamat.R;
import com.azamat.data.model.QuizResult;
import com.azamat.databinding.ActivityResultBinding;
import com.azamat.ui.activity.question.QuestionViewModel;
import com.azamat.ui.fragment.setting.SettingFragment;

public class ResultActivity extends AppCompatActivity {
    public String difficulty, categoryStr;
    protected float idF, forAnswerF;
    protected int id, forAnswer;
    protected ActivityResultBinding binding;
    protected ResultViewModel vm;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = App.sp;
        setTheme(App.setMyTheme(sp.getInt(SettingFragment.THEME, 20)));

        binding = DataBindingUtil.setContentView(this, R.layout.activity_result);
        vm = new ViewModelProvider(this).get(ResultViewModel.class);

        binding.setRa(this);

        Intent intent = getIntent();
        QuizResult qr = vm.getQuizResult(intent.getStringExtra(QuestionViewModel.QUIZ_RESULT));
        vm.saveToDB(qr);

        idF = qr.getAmount();
        forAnswerF = qr.getCorrectAnswerResult();

        binding.textUnderDifficulty.setText(qr.getDifficulty());
        binding.textUnderCorrectAnswers.setText(qr.getCorrectAnswerResult() + "/" + qr.getAmount());
        binding.textUnderResult.setText(forAnswerF / idF * 100 + "%");
        binding.textViewVar.setText(qr.getCategory());

        binding.btnFinish.setOnClickListener(v -> {
            finish();
        });
    }
}