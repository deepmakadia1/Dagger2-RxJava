package com.dagger2_rxjava.di.component;

import com.dagger2_rxjava.di.module.AppModule;
import com.dagger2_rxjava.di.module.NetworkModule;
import com.dagger2_rxjava.di.qualifier.ProvideDrinkRetrofit;
import com.dagger2_rxjava.di.qualifier.ProvideFoodRetrofit;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface NetworkComponent {

    @ProvideFoodRetrofit
    Retrofit provideFoodRetrofit();

    @ProvideDrinkRetrofit
    Retrofit provideDrinkRetrofit();

}
