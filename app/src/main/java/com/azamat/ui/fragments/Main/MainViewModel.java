package com.azamat.ui.fragments.Main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    int i = 0;

   public MutableLiveData<Integer> newData = new MutableLiveData<>();

   public void plus () {
       i++;
       newData.setValue(i);
    }

    public void minus () {
       i--;
       newData.setValue(i);
    }

    public void reset () {
       i = 0;
       newData.setValue(i);
    }



}