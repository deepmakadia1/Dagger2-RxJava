package com.dagger2_rxjava.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.dagger2_rxjava.AppClass;
import com.dagger2_rxjava.models.entity.RecipeCategoryModel;
import com.dagger2_rxjava.repositories.FoodRepository;

import java.util.List;


public class FoodActivityViewModel extends AndroidViewModel {

    private FoodRepository foodRepository;

    public FoodActivityViewModel(@NonNull Application application) {
        super(application);
        foodRepository = AppClass.getInstance().getServiceComponent().getRecipeRepository();
    }

    public LiveData<List<RecipeCategoryModel.Categories>> getCategories(){
        return foodRepository.getMutableLiveDataCategories();
    }

    public LiveData<Boolean> getProgress(){
        return foodRepository.getMutableProgress();
    }

}
