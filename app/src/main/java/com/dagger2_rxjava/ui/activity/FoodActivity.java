package com.dagger2_rxjava.ui.activity;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.os.Bundle;

import com.dagger2_rxjava.R;
import com.dagger2_rxjava.adapter.RecipePagerAdapter;
import com.dagger2_rxjava.databinding.ActivityFoodBinding;
import com.dagger2_rxjava.models.entity.RecipeCategoryModel;
import com.dagger2_rxjava.ui.fragment.CategoryFragment;
import com.dagger2_rxjava.viewmodel.FoodActivityViewModel;

import java.util.List;

public class FoodActivity extends BaseActivity<ActivityFoodBinding,FoodActivityViewModel> {

    private RecipePagerAdapter pagerAdapter;

    @Override
    public int getLayout() {
        return R.layout.activity_food;
    }

    @Override
    public Class<FoodActivityViewModel> getViewModel() {
        return FoodActivityViewModel.class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pagerAdapter = new RecipePagerAdapter(getSupportFragmentManager());
        viewModel.getProgress().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean != null && aBoolean) {
                    showProgress();
                } else {
                    hideProgress();
                }
            }
        });

        viewModel.getCategories().observe(this, new Observer<List<RecipeCategoryModel.Categories>>() {
            @Override
            public void onChanged(@Nullable List<RecipeCategoryModel.Categories> categories) {
                if (categories != null) {
                    setupViewPager(categories);
                }
            }
        });
    }

    private void setupViewPager(List<RecipeCategoryModel.Categories> categories) {
        for (RecipeCategoryModel.Categories category : categories) {
            pagerAdapter.addFragment(CategoryFragment.newInstance(category.getStrCategory()), category.getStrCategory());
        }
        binding.pagerCategory.setAdapter(pagerAdapter);
        binding.tabCategory.setupWithViewPager(binding.pagerCategory);
    }
}
