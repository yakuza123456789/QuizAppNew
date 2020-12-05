package com.azamat.ui.fragment.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.azamat.R;
import com.azamat.databinding.MainFragmentBinding;

public class MainFragment extends Fragment {

    private MainViewModel mainViewModel;
    protected MainFragmentBinding binding;
    public static final String ID = "id";
    public static final String CATEGORY = "category";
    public static final String CATEGORY_STR = "categoryStr";
    public static final String DIFFICULTY = "difficulty";
    protected OnClickListenerMainFragment onClickListenerMainFragment;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModelInit();
        onOpenQuestionActivity();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        dataView();
        seekBarListener();
    }

    private void dataView() {
        mainViewModel.getCategory();
        mainViewModel.categoryData.observe(getViewLifecycleOwner(), categoryModels -> {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, mainViewModel.cat_title);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.mainSpinnerFirst.setAdapter(adapter);
        });
        mainViewModel.newData.observe(getViewLifecycleOwner(), s -> {
            binding.mainTvTen.setText(s);
            binding.mainSeekBar.setProgress(Integer.parseInt(s));
        });

    }

    private void viewModelInit() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        onClickListenerMainFragment = (OnClickListenerMainFragment) requireContext();
        binding.setMv(mainViewModel);
    }

    private void onOpenQuestionActivity() {
        binding.mainBtnStart.setOnClickListener(view ->
        onClickListenerMainFragment.openActivity(binding.mainSeekBar.getProgress(),
                mainViewModel.categoryData.getValue().get(binding.mainSpinnerFirst.getSelectedItemPosition()).getId(),
                mainViewModel.categoryData.getValue().get(binding.mainSpinnerFirst.getSelectedItemPosition()).getName(),
                binding.mainSpinnerSecond.getSelectedItem().toString().toLowerCase()));

    }

    private void seekBarListener() {
        binding.mainSeekBar.setOnSeekBarChangeListener(new IOnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                binding.mainTvTen.setText(String.valueOf(progress));
                mainViewModel.i = progress;
            }
        });
    }
}