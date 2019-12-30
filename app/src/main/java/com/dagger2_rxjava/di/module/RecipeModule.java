package com.dagger2_rxjava.di.module;

import com.dagger2_rxjava.di.qualifier.ProvideFoodRetrofit;
import com.dagger2_rxjava.di.scope.UserScope;
import com.dagger2_rxjava.models.state.RecipeServiceInterface;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class RecipeModule {

    @UserScope
    @Provides
    RecipeServiceInterface provideRecipeService(@ProvideFoodRetrofit Retrofit retrofit) {
        return retrofit.create(RecipeServiceInterface.class);
    }

}
