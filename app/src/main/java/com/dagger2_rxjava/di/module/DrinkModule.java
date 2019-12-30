package com.dagger2_rxjava.di.module;

import com.dagger2_rxjava.di.qualifier.ProvideDrinkRetrofit;
import com.dagger2_rxjava.di.scope.UserScope;
import com.dagger2_rxjava.models.state.DrinkServiceInterface;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class DrinkModule {

    @UserScope
    @Provides
    DrinkServiceInterface provideDrinkService(@ProvideDrinkRetrofit Retrofit retrofit) {
        return retrofit.create(DrinkServiceInterface.class);
    }

}
