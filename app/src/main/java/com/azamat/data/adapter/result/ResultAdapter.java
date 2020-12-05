package com.azamat.data.adapter.result;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.azamat.data.adapter.base.BaseRecyclerViewAdapter;
import com.azamat.data.model.QuizResult;
import com.azamat.databinding.ListResultBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ResultAdapter extends BaseRecyclerViewAdapter<QuizResult> {

    protected ListResultBinding binding;

    public ResultAdapter(ArrayList<QuizResult> list) {
        super(list);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ListResultBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BaseViewHolder(binding.getRoot());
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void bind(QuizResult quizResult, RecyclerView.ViewHolder holder) {
        binding.setQr(quizResult);
        binding.resultTvCategory.setText("Category:" + quizResult.getCategory());
        binding.resultTvCorrect.setText("Correct answer: " + quizResult.getCorrectAnswerResult() + "/" + quizResult.getAmount());
        binding.resultTvDifficulty.setText("Difficulty: " + quizResult.getDifficulty());
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy hh:mm");
        String strDate = dateFormat.format(quizResult.getCreatedAt());
        binding.resultTvDate.setText(strDate);
    }

    public void setList(ArrayList<QuizResult> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
