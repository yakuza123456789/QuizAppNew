package com.azamat.ui.fragment.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.azamat.App;
import com.azamat.data.model.category.CategoryModel;
import com.azamat.data.network.IQuizApiClient;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {

    MutableLiveData<ArrayList<CategoryModel>> categoryData = new MutableLiveData<>();
    ArrayList<String> cat_title = new ArrayList<>();
    MutableLiveData<String> newData = new MutableLiveData<>();
    Integer i = 1;


    public void plus() {
        if (i < 10)
            i++;
        setNum();
    }

    public void minus() {
        if (i > 0)
            i--;
        setNum();
    }


    void setNum() {
        newData.setValue(String.valueOf(i));
    }


    public void getCategory() {
        App.apiClient.getCategory(new IQuizApiClient.CategoryCallBack() {
            @Override
            public void onSuccess(ArrayList<CategoryModel> result) {
                for (int j = 0; j < result.size(); j++) {
                    cat_title.add(result.get(j).getName());
                }
                categoryData.setValue(result);
            }

            @Override
            public void onFailure(Exception e) {
            }
        });
    }



}