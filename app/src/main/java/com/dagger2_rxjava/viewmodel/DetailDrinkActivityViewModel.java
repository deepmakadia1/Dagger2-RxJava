package com.dagger2_rxjava.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.dagger2_rxjava.AppClass;
import com.dagger2_rxjava.models.entity.DrinkDetailModel;
import com.dagger2_rxjava.repositories.DrinkRepository;

import java.util.ArrayList;

public class DetailDrinkActivityViewModel extends AndroidViewModel {

    private DrinkRepository drinkRepository;

    public DetailDrinkActivityViewModel(@NonNull Application application) {
        super(application);
        drinkRepository = AppClass.getInstance().getServiceComponent().getDrinkRepository();
    }

    public LiveData<ArrayList<DrinkDetailModel.Drink>> getDrink (String mealId){
        return drinkRepository.getListMutableLiveDataDrink(mealId);
    }

    public LiveData<Boolean> getProcess(){
        return drinkRepository.getMutableProgress();
    }
}
