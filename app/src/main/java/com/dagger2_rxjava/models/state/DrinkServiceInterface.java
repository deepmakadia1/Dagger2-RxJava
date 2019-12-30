package com.dagger2_rxjava.models.state;

import com.dagger2_rxjava.models.entity.DrinkCategoryListModel;
import com.dagger2_rxjava.models.entity.DrinkListModel;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface DrinkServiceInterface {

    @GET("list.php")
    Call<DrinkCategoryListModel> getDrinkCategoryList(
            @QueryMap HashMap<String,String> map
    );

    @GET("filter.php")
    Call<DrinkListModel> getDrinkList(
            @QueryMap HashMap<String,String> map
    );

    @GET("lookup.php")
    Call<DrinkCategoryListModel> getDrink(
            @QueryMap HashMap<String,String> map
    );
}
