package com.dagger2_rxjava.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.dagger2_rxjava.AppClass;
import com.dagger2_rxjava.models.entity.RecipeModel;
import com.dagger2_rxjava.repositories.FoodRepository;

import java.util.List;

import javax.inject.Inject;

public class CategoryFragmentViewModel extends AndroidViewModel {

    @Inject
    FoodRepository foodRepository;

    public CategoryFragmentViewModel(@NonNull Application application) {
        super(application);
        AppClass.getInstance().getServiceComponent().inject(this);
    }

    public LiveData<List<RecipeModel.Recipe>> getRecipeList(String categoryName){
        return foodRepository.getMutableLiveDataRecipesList(categoryName);
    }

    public LiveData<Boolean> getProgress(){
        return foodRepository.getMutableProgress();
    }

}
