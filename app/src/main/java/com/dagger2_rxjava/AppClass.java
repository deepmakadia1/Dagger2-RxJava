package com.dagger2_rxjava;

import android.app.Application;

import com.dagger2_rxjava.di.component.DaggerNetworkComponent;
import com.dagger2_rxjava.di.component.DaggerServiceComponent;
import com.dagger2_rxjava.di.component.NetworkComponent;
import com.dagger2_rxjava.di.component.ServiceComponent;
import com.dagger2_rxjava.di.module.ContextModule;
import com.dagger2_rxjava.di.module.DrinkModule;
import com.dagger2_rxjava.di.module.NetworkModule;
import com.dagger2_rxjava.di.module.RecipeModule;
import com.dagger2_rxjava.util.Constants;

public class AppClass extends Application {

    private ServiceComponent serviceComponent;
    public static AppClass appClass;

    @Override
    public void onCreate() {
        super.onCreate();
        appClass = this;
        NetworkComponent networkComponent = DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(Constants.FOOD_BASE_URL,Constants.DRINK_BASE_URL))
                .build();

        serviceComponent = DaggerServiceComponent.builder()
                .networkComponent(networkComponent)
                .contextModule(new ContextModule(getApplicationContext()))
                .recipeModule(new RecipeModule())
                .drinkModule(new DrinkModule())
                .build();

    }

    public static AppClass getInstance() {
        if (appClass == null) {
            appClass = new AppClass();
        }
        return appClass;
    }

    public ServiceComponent getServiceComponent() {
        return serviceComponent;
    }

}
