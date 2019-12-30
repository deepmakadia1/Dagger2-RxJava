package com.dagger2_rxjava;

import android.app.Application;

import com.dagger2_rxjava.di.DaggerNetworkComponent;
import com.dagger2_rxjava.di.DaggerServiceComponent;
import com.dagger2_rxjava.di.component.NetworkComponent;
import com.dagger2_rxjava.di.component.ServiceComponent;
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
                .recipeModule(new RecipeModule())
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
