package com.dagger2_rxjava.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.dagger2_rxjava.AppClass;
import com.dagger2_rxjava.models.entity.RecipeDetailModel;
import com.dagger2_rxjava.repositories.FoodRepository;

import java.util.ArrayList;

import javax.inject.Inject;

public class DetailRecipeActivityViewModel extends AndroidViewModel {

    @Inject
    FoodRepository foodRepository;

    public DetailRecipeActivityViewModel(@NonNull Application application) {
        super(application);
        AppClass.getInstance().getServiceComponent().inject(this);
    }

    public LiveData<ArrayList<RecipeDetailModel.Meals>> getMeals(String mealId){
        return foodRepository.getListMutableLiveDataMeals(mealId);
    }

    public LiveData<Boolean> getProgress(){
        return foodRepository.getMutableProgress();
    }
}
