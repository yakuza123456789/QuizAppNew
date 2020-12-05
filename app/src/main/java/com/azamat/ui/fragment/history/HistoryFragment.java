package com.azamat.ui.fragment.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.azamat.App;
import com.azamat.R;
import com.azamat.data.adapter.result.ResultAdapter;
import com.azamat.data.model.QuizResult;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {


    protected HistoryViewModel historyViewModel;
    protected ResultAdapter adapter;
    protected ArrayList<QuizResult> list;
    protected RecyclerView recyclerViewHistory;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.history_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        historyViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        setList((ArrayList<QuizResult>) App.db.quizDao().getAll());
    }

    public void init(View v) {
        list = new ArrayList<>();
        adapter = new ResultAdapter(list);
        recyclerViewHistory = v.findViewById(R.id.rv);
        recyclerViewHistory.setAdapter(adapter);
    }

    public void setList(ArrayList<QuizResult> list) {
        adapter.setList(list);
    }

}