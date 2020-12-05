package com.azamat.data.network;

import com.azamat.data.model.category.CategoryResponse;
import com.azamat.data.model.question.QuestionResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("api.php")
    Call<QuestionResponse> getQuestionModel(@Query("amount") int amount,
                                            @Query("category") int category,
                                            @Query("difficulty") String difficulty);

    @GET("api_category.php")
    Call<CategoryResponse> getCategories();
}
