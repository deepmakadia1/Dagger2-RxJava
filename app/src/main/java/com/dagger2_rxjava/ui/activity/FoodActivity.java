package com.dagger2_rxjava.ui.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dagger2_rxjava.R;
import com.dagger2_rxjava.adapter.RecipePagerAdapter;
import com.dagger2_rxjava.databinding.ActivityFoodBinding;
import com.dagger2_rxjava.models.entity.RecipeCategoryModel;
import com.dagger2_rxjava.ui.fragment.CategoryFragment;
import com.dagger2_rxjava.viewmodel.FoodActivityViewModel;

import java.util.List;

public class FoodActivity extends AppCompatActivity {

    private ActivityFoodBinding binding;
    private RecipePagerAdapter pagerAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = this;
        binding = DataBindingUtil.setContentView(activity,R.layout.activity_food);

        FoodActivityViewModel foodActivityViewModel = ViewModelProviders.of(this).get(FoodActivityViewModel.class);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        pagerAdapter = new RecipePagerAdapter(getSupportFragmentManager());

        foodActivityViewModel.getProgress().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean != null && aBoolean) {
                    if (!progressDialog.isShowing())
                        progressDialog.show();
                } else {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                }
            }
        });

        foodActivityViewModel.getCategories().observe(this, new Observer<List<RecipeCategoryModel.Categories>>() {
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
