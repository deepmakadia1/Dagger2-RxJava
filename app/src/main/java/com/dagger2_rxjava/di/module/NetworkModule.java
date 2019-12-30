package com.dagger2_rxjava.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private String food_base_url;
    private String drink_base_url;

    public NetworkModule(String food_base_url,String drink_base_url) {
        this.food_base_url = food_base_url;
        this.drink_base_url = drink_base_url;
    }

    @Provides
    @Singleton
    GsonConverterFactory provideGsonConvertFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    @Named("provideFoodRetrofit")
    Retrofit provideFoodRetrofit(GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .baseUrl(food_base_url)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    @Singleton
    @Named("provideDrinkRetrofit")
    Retrofit provideDrinkRetrofit(GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .baseUrl(drink_base_url)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides  // Dagger will only look for methods annotated with @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.serializeNulls().create();
    }
}
