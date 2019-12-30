package com.dagger2_rxjava.models.state;


import com.dagger2_rxjava.models.entity.RecipeCategoryModel;
import com.dagger2_rxjava.models.entity.RecipeDetailModel;
import com.dagger2_rxjava.models.entity.RecipeModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeServiceInterface {

    @GET("categories.php")
    Call<RecipeCategoryModel> getCategories();

    @GET("filter.php")
    Call<RecipeModel> getRecipes(
            @Query("c") String categoryName
    );

    @GET("lookup.php")
    Call<RecipeDetailModel> getMeal(
            @Query("i") String mealId
    );

}
