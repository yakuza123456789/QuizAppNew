package com.azamat.ui.activity.question;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;

import com.azamat.App;
import com.azamat.R;
import com.azamat.data.adapter.question.OnClickNextQuestionActivity;
import com.azamat.data.adapter.question.OnClickOpenActivity;
import com.azamat.data.adapter.question.QuestionAdapter;
import com.azamat.data.custom.CustomLinearLayoutManager;
import com.azamat.databinding.ActivityQuestionBinding;
import com.azamat.ui.fragment.setting.SettingFragment;

public class QuestionActivity extends AppCompatActivity implements OnClickNextQuestionActivity, OnClickOpenActivity {
    protected ActivityQuestionBinding binding;
    protected QuestionAdapter adapter;
    protected QuestionViewModel questionViewModel;
    public final static String CORRECT_ANSWER = "correct_answer";


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = App.sp;
        setTheme(App.setMyTheme(sp.getInt(SettingFragment.THEME, 20)));
        recyclerInit();
        methodsVM();
        backClickQuestion();
        skipQuestions();
        methodsVM();
        binding.tvQuality.setText(0 + "/" + questionViewModel.id);
    }

    private void methodsVM() {
        questionViewModel.fromIntent(getIntent());
        questionViewModel.upProgressBar(binding.pBar, binding.tvQuality);
        questionViewModel.getQuestionsData(this, adapter);
    }

    private void skipQuestions() {
        binding.btnSkip.setOnClickListener(v -> questionViewModel.onSkip(binding.questionRecyclerView));
    }

    private void recyclerInit() {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_question);
        questionViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);
        binding.setQv(questionViewModel);

        CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.questionRecyclerView);

        adapter = new QuestionAdapter(this, this);

        binding.questionRecyclerView.setAdapter(adapter);
        binding.questionRecyclerView.setLayoutManager(layoutManager);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void nextItem(boolean correctAnswer, final int adapterPosition) {
        binding.setPosition(adapterPosition + 1);
        binding.tvQuality.setText(adapterPosition + 1 + "/" + questionViewModel.id);
        questionViewModel.nextItem(correctAnswer, adapterPosition, binding.questionRecyclerView);
    }

    @Override
    public void onBackPressed() {
        questionViewModel.outback(this, binding.questionRecyclerView);
    }

    public void backClickQuestion() {
        binding.ibBack.setOnClickListener(v -> finish());
    }

    @Override
    public void answersMethod() {
        questionViewModel.sendIntent(this);
    }

}