package com.dagger2_rxjava.di.module;

import android.content.Context;

import com.dagger2_rxjava.di.scope.UserScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @UserScope
    @Provides
    Context provideContext(){
        return context;
    }

}
