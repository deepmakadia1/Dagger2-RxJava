package com.dagger2_rxjava.di.module;

import com.dagger2_rxjava.di.scope.UserScope;
import com.dagger2_rxjava.models.state.DrinkServiceInterface;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class DrinkModule {

    @UserScope
    @Provides
    DrinkServiceInterface provideDrinkService(@Named("provideDrinkRetrofit")Retrofit retrofit){
        return retrofit.create(DrinkServiceInterface.class);
    }

}
