package com.azamat.data.adapter.question;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.azamat.R;
import com.azamat.data.model.question.QuestionModel;
import com.azamat.databinding.ListQuestionBinding;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    protected ArrayList<QuestionModel> questionList = new ArrayList<>();
    protected OnClickNextQuestionActivity onClickNextQuestionActivity;
    protected OnClickOpenActivity onClickOpenActivity;
    protected ListQuestionBinding questionBinding;


    public QuestionAdapter(OnClickNextQuestionActivity onClickNextItemQA, OnClickOpenActivity onClickOpenActivity) {
        this.onClickNextQuestionActivity = onClickNextItemQA;
        this.onClickOpenActivity = onClickOpenActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        questionBinding = ListQuestionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(questionBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        questionBinding.setQm(questionList.get(position));
        questionBinding.setVh(holder);
        holder.clear();
        if (questionBinding.getQm().getSelectQuestionPosition() != 100 && !questionList.get(position).isClicked())
            holder.questionBlock(questionBinding.getQm().getSelectQuestionPosition());
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public void setQuestions(ArrayList<QuestionModel> questions) {
        this.questionList = questions;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected Button[] buttons = {questionBinding.questionBtnFirst, questionBinding.questionBtnSecond, questionBinding.questionBtnThird, questionBinding.questionBtnFourth, questionBinding.questionBtnFifth, questionBinding.questionBtnSixth};

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void methods(String text, int position) {
            boolean click = false;
            QuestionModel qm = questionBinding.getQm();
            if (qm.isClicked() && text.equals(qm.getCorrectAnswer())) {
                qm.setClicked(false);
                click = true;
            }
            questionBlock(position);
            onClickNextQuestionActivity.nextItem(click, getAdapterPosition());

            if (getAdapterPosition() == questionList.size() - 1)
                onClickOpenActivity.answersMethod();
        }

        private void questionBlock(int positionBtn) {
            QuestionModel questionModel = questionBinding.getQm();
            if (questionModel.getSelectQuestionPosition() == 50)
                questionModel.setSelectQuestionPosition(positionBtn);

            if (questionModel.isClicked()) {
                if (questionModel.getCorrectAnswer().contentEquals(buttons[positionBtn].getText())) {
                    buttons[positionBtn].setBackgroundResource(R.drawable.for_true_variant);
                } else {
                    buttons[positionBtn].setBackgroundResource(R.drawable.for_false_variant);
                }
                buttons[positionBtn].setTextColor(Color.WHITE);
            }
        }

        void clear() {
            for (Button btn : buttons) {
                btn.setBackgroundResource(R.drawable.for_variants);
                btn.setTextColor(R.style.ForBtn);
            }
        }
    }
}
