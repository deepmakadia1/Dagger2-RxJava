package com.dagger2_rxjava.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dagger2_rxjava.R;
import com.dagger2_rxjava.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = this;
        ActivityMainBinding binding = DataBindingUtil.setContentView(activity, R.layout.activity_main);

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
