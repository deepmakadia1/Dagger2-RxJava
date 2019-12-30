package com.dagger2_rxjava.repositories;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.dagger2_rxjava.models.entity.DrinkCategoryListModel;
import com.dagger2_rxjava.models.entity.DrinkDetailModel;
import com.dagger2_rxjava.models.entity.DrinkListModel;
import com.dagger2_rxjava.models.state.DrinkServiceInterface;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrinkRepository {

    private ArrayList<DrinkCategoryListModel.DrinkCategories> drinksCategory;
    private MutableLiveData<ArrayList<DrinkCategoryListModel.DrinkCategories>> listMutableLiveDataDrinkCategory = new MutableLiveData<>();
    private MutableLiveData<Boolean> mutableProgress = new MutableLiveData<>();
    private static final String TAG = "DrinkRepository";

    @Inject
    DrinkServiceInterface drinkServiceInterface;

    @Inject
    DrinkRepository() {
    }

    public MutableLiveData<ArrayList<DrinkCategoryListModel.DrinkCategories>> getListMutableLiveDataDrinkCategory(HashMap<String,String> map) {
        showProgress();
        drinkServiceInterface.getDrinkCategoryList(map).enqueue(new Callback<DrinkCategoryListModel>() {
            @Override
            public void onResponse(@NonNull Call<DrinkCategoryListModel> call, @NonNull Response<DrinkCategoryListModel> response) {
                hideProgress();
                Log.e(TAG, call.request().url().toString());
                Log.e(TAG, new Gson().toJson(response.body()));

                DrinkCategoryListModel drinkCategoryListModel = response.body();

                if (drinkCategoryListModel!=null && drinkCategoryListModel.getDrinks()!= null){
                    drinksCategory = (ArrayList<DrinkCategoryListModel.DrinkCategories>) drinkCategoryListModel.getDrinks();
                    listMutableLiveDataDrinkCategory.setValue(drinksCategory);
                }

            }

            @Override
            public void onFailure(@NonNull Call<DrinkCategoryListModel> call, @NonNull Throwable t) {
                hideProgress();
                t.printStackTrace();
            }
        });

        return listMutableLiveDataDrinkCategory;
    }

    private ArrayList<DrinkListModel.Drinks> drinks;
    private MutableLiveData<ArrayList<DrinkListModel.Drinks>> listMutableLiveDataDrinks = new MutableLiveData<>();

    public MutableLiveData<ArrayList<DrinkListModel.Drinks>> getListMutableLiveDataDrinks(HashMap<String,String> map) {

        showProgress();
        drinkServiceInterface.getDrinkList(map).enqueue(new Callback<DrinkListModel>() {
            @Override
            public void onResponse(@NonNull Call<DrinkListModel> call, @NonNull Response<DrinkListModel> response) {
                hideProgress();
                Log.e(TAG, call.request().url().toString());
                Log.e(TAG, new Gson().toJson(response.body()));

                DrinkListModel drinkListModel = response.body();

                if (drinkListModel!=null && drinkListModel.getDrinks()!=null){

                    drinks = (ArrayList<DrinkListModel.Drinks>) drinkListModel.getDrinks();
                    listMutableLiveDataDrinks.setValue(drinks);

                }

            }

            @Override
            public void onFailure(@NonNull Call<DrinkListModel> call, @NonNull Throwable t) {
                hideProgress();
                t.printStackTrace();
            }
        });

        return listMutableLiveDataDrinks;
    }

    public MutableLiveData<Boolean> getMutableProgress() {
        return mutableProgress;
    }

    private ArrayList<DrinkDetailModel.Drink> drink = new ArrayList<>();
    private MutableLiveData<ArrayList<DrinkDetailModel.Drink>> listMutableLiveDataDrink = new MutableLiveData<>();

    public MutableLiveData<ArrayList<DrinkDetailModel.Drink>> getListMutableLiveDataDrink(String drinkId) {

        showProgress();
        drinkServiceInterface.getDrink(drinkId).enqueue(new Callback<DrinkDetailModel>() {
            @Override
            public void onResponse(@NonNull Call<DrinkDetailModel> call, @NonNull Response<DrinkDetailModel> response) {
                hideProgress();
                Log.e(TAG, call.request().url().toString());
                Log.e(TAG, new Gson().toJson(response.body()));

                DrinkDetailModel drinkDetailModel = response.body();

                if (drinkDetailModel != null && drinkDetailModel.getDrinks()!=null){
                    drink = (ArrayList<DrinkDetailModel.Drink>) drinkDetailModel.getDrinks();
                    listMutableLiveDataDrink.setValue(drink);
                }

            }

            @Override
            public void onFailure(@NonNull Call<DrinkDetailModel> call, @NonNull Throwable t) {
                hideProgress();
                t.printStackTrace();
            }
        });

        return listMutableLiveDataDrink;
    }

    private void hideProgress() {
        mutableProgress.postValue(false);
    }

    private void showProgress() {
        mutableProgress.setValue(true);
    }
}
