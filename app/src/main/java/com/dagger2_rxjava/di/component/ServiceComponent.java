package com.dagger2_rxjava.di.component;


import com.dagger2_rxjava.di.module.DrinkModule;
import com.dagger2_rxjava.di.module.RecipeModule;
import com.dagger2_rxjava.di.scope.UserScope;
import com.dagger2_rxjava.repositories.DrinkRepository;
import com.dagger2_rxjava.repositories.FoodRepository;


import dagger.Component;

@UserScope
@Component(dependencies = NetworkComponent.class, modules = {RecipeModule.class,DrinkModule.class})
public interface ServiceComponent {

    FoodRepository getRecipeRepository();

    DrinkRepository getDrinkRepository();

}
