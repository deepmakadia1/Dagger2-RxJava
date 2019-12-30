package com.dagger2_rxjava.di.component;

import com.dagger2_rxjava.di.module.ContextModule;
import com.dagger2_rxjava.di.module.DrinkModule;
import com.dagger2_rxjava.di.module.RecipeModule;
import com.dagger2_rxjava.di.scope.UserScope;
import com.dagger2_rxjava.viewmodel.CategoryFragmentViewModel;
import com.dagger2_rxjava.viewmodel.DetailDrinkActivityViewModel;
import com.dagger2_rxjava.viewmodel.DetailRecipeActivityViewModel;
import com.dagger2_rxjava.viewmodel.DrinkCategoryListViewModel;
import com.dagger2_rxjava.viewmodel.DrinkListActivityViewModel;
import com.dagger2_rxjava.viewmodel.FoodActivityViewModel;


import dagger.Component;

@UserScope
@Component(dependencies = {NetworkComponent.class,ContextModule.class}, modules = {RecipeModule.class,DrinkModule.class})
public interface ServiceComponent {

    void inject(CategoryFragmentViewModel categoryFragmentViewModel);

    void inject(DetailRecipeActivityViewModel detailRecipeActivityViewModel);

    void inject(FoodActivityViewModel foodActivityViewModel);

    void inject(DrinkListActivityViewModel drinkListActivityViewModel);

    void inject(DrinkCategoryListViewModel drinkCategoryListViewModel);

    void inject(DetailDrinkActivityViewModel detailDrinkActivityViewModel);
}
