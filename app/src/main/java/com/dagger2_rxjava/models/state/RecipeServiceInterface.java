package com.dagger2_rxjava.models.state;


import com.dagger2_rxjava.models.entity.RecipeCategoryModel;
import com.dagger2_rxjava.models.entity.RecipeDetailModel;
import com.dagger2_rxjava.models.entity.RecipeModel;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeServiceInterface {

    @GET("categories.php")
    Observable<RecipeCategoryModel> getCategories();

    @GET("filter.php")
    Observable<RecipeModel> getRecipes(
            @Query("c") String categoryName
    );

    @GET("lookup.php")
    Observable<RecipeDetailModel> getMeal(
            @Query("i") String mealId
    );

}
