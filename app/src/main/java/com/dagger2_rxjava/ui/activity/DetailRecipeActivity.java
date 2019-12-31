package com.dagger2_rxjava.ui.activity;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dagger2_rxjava.R;
import com.dagger2_rxjava.databinding.ActivityDetailRecipeBinding;
import com.dagger2_rxjava.models.entity.RecipeDetailModel;
import com.dagger2_rxjava.util.Constants;
import com.dagger2_rxjava.viewmodel.DetailRecipeActivityViewModel;

import java.util.ArrayList;

public class DetailRecipeActivity extends BaseActivity<ActivityDetailRecipeBinding,DetailRecipeActivityViewModel> {

    @Override
    public int getLayout() {
        return R.layout.activity_detail_recipe;
    }

    @Override
    public Class<DetailRecipeActivityViewModel> getViewModel() {
        return DetailRecipeActivityViewModel.class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel.getProgress().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean != null && aBoolean) {
                    binding.scrollView.setVisibility(View.GONE);
                    binding.progress.setVisibility(View.VISIBLE);
                } else {
                    binding.scrollView.setVisibility(View.VISIBLE);
                    binding.progress.setVisibility(View.GONE);
                }
            }
        });

        viewModel.getMeals(getIntent().getStringExtra(Constants.MEAL_ID)).observe(this, new Observer<ArrayList<RecipeDetailModel.Meals>>() {
            @Override
            public void onChanged(@Nullable ArrayList<RecipeDetailModel.Meals> meals) {
                if (meals != null) {
                    binding.setMeal(meals.get(0));
                }
            }
        });


    }
}
