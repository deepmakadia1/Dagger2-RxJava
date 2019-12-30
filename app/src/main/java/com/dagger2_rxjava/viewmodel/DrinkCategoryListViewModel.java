package com.dagger2_rxjava.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.dagger2_rxjava.AppClass;
import com.dagger2_rxjava.models.entity.DrinkCategoryListModel;
import com.dagger2_rxjava.repositories.DrinkRepository;

import java.util.ArrayList;
import java.util.HashMap;

public class DrinkCategoryListViewModel extends AndroidViewModel {

    private DrinkRepository drinkRepository;

    public DrinkCategoryListViewModel(@NonNull Application application) {
        super(application);
        drinkRepository = AppClass.getInstance().getServiceComponent().getDrinkRepository();
    }

    public LiveData<ArrayList<DrinkCategoryListModel.DrinkCategories>> getDrinksCAtegory(HashMap<String,String> map){
        return drinkRepository.getListMutableLiveDataDrinkCategory(map);
    }

    public LiveData<Boolean> getProgress(){
        return drinkRepository.getMutableProgress();
    }
}
