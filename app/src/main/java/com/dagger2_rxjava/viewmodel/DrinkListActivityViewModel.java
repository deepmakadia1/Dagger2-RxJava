package com.dagger2_rxjava.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.dagger2_rxjava.AppClass;
import com.dagger2_rxjava.models.entity.DrinkListModel;
import com.dagger2_rxjava.repositories.DrinkRepository;

import java.util.ArrayList;
import java.util.HashMap;

public class DrinkListActivityViewModel extends AndroidViewModel {

    private DrinkRepository drinkRepository;

    public DrinkListActivityViewModel(@NonNull Application application) {
        super(application);
        drinkRepository = AppClass.getInstance().getServiceComponent().getDrinkRepository();
    }

    public LiveData<ArrayList<DrinkListModel.Drinks>> getDrinks(HashMap<String,String> map){
        return drinkRepository.getListMutableLiveDataDrinks(map);
    }

    public LiveData<Boolean> getProsess(){
        return drinkRepository.getMutableProgress();
    }

}
