package com.dagger2_rxjava.di.module;

import com.dagger2_rxjava.di.qualifier.ProvideDrinkRetrofit;
import com.dagger2_rxjava.di.qualifier.ProvideFoodRetrofit;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private String food_base_url;
    private String drink_base_url;

    public NetworkModule(String food_base_url,String drink_base_url) {
        this.food_base_url = food_base_url;
        this.drink_base_url = drink_base_url;
    }

    @Provides  // Dagger will only look for methods annotated with @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.serializeNulls().create();
    }

    @Provides
    @Singleton
    GsonConverterFactory provideGsonConvertFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    RxJava2CallAdapterFactory provideRxjava2CallAdapterFactory(){
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(httpLoggingInterceptor);

        return builder.build();
    }

    @Provides
    @Singleton
    @ProvideFoodRetrofit
    Retrofit provideFoodRetrofit(GsonConverterFactory gsonConverterFactory,RxJava2CallAdapterFactory rxJava2CallAdapterFactory,OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(food_base_url)
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .build();
    }

    @Provides
    @Singleton
    @ProvideDrinkRetrofit
    Retrofit provideDrinkRetrofit(GsonConverterFactory gsonConverterFactory,RxJava2CallAdapterFactory rxJava2CallAdapterFactory,OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(drink_base_url)
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .build();
    }

}
