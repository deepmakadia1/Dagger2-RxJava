package com.dagger2_rxjava.ui.activity;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dagger2_rxjava.R;
import com.dagger2_rxjava.databinding.ActivityDetailDrinksBinding;
import com.dagger2_rxjava.models.entity.DrinkDetailModel;
import com.dagger2_rxjava.util.Constants;
import com.dagger2_rxjava.viewmodel.DetailDrinkActivityViewModel;

import java.util.ArrayList;

public class DetailDrinksActivity extends AppCompatActivity {

    private ActivityDetailDrinksBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = this;
        binding = DataBindingUtil.setContentView(activity,R.layout.activity_detail_drinks);

        DetailDrinkActivityViewModel detailDrinkActivityViewModel = ViewModelProviders.of(this).get(DetailDrinkActivityViewModel.class);

        detailDrinkActivityViewModel.getProcess().observe(this, new Observer<Boolean>() {
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

        detailDrinkActivityViewModel.getDrink(getIntent().getStringExtra(Constants.DRINK_ID)).observe(this, new Observer<ArrayList<DrinkDetailModel.Drink>>() {
            @Override
            public void onChanged(@Nullable ArrayList<DrinkDetailModel.Drink> drinks) {
                if (drinks != null) {
                    binding.setDrink(drinks.get(0));
                }
            }
        });

    }
}
