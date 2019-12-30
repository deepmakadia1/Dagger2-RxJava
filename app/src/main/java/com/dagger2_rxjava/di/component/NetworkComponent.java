package com.dagger2_rxjava.di.component;

import com.dagger2_rxjava.di.module.AppModule;
import com.dagger2_rxjava.di.module.NetworkModule;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface NetworkComponent {

    @Named("provideFoodRetrofit")
    Retrofit provideFoodRetrofit();

    @Named("provideDrinkRetrofit")
    Retrofit provideDrinkRetrofit();

}
