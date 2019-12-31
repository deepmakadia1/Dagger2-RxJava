package com.dagger2_rxjava.ui.activity;

import android.arch.lifecycle.AndroidViewModel;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dagger2_rxjava.R;
import com.dagger2_rxjava.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding, AndroidViewModel> implements View.OnClickListener {

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public Class<AndroidViewModel> getViewModel() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding.food.setOnClickListener(this);
        binding.drinks.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.food:
                startActivity(new Intent(this,FoodActivity.class));
                break;
            case R.id.drinks:
                startActivity(new Intent(this,DrinksActivity.class));
                break;
        }
    }
}
