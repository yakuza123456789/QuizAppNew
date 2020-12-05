package com.azamat.data.model.category;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CategoryResponse {
    @SerializedName("trivia_categories")
    private ArrayList<CategoryModel> category;

    public ArrayList<CategoryModel> getList() {
        return category;
    }

    public void setList(ArrayList<CategoryModel> category) {
        this.category = category;
    }
}
