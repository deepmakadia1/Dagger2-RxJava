package com.dagger2_rxjava.ui.activity;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.dagger2_rxjava.R;
import com.dagger2_rxjava.adapter.DrinksListAdapter;
import com.dagger2_rxjava.databinding.ActivityDrinkListBinding;
import com.dagger2_rxjava.models.entity.DrinkListModel;
import com.dagger2_rxjava.util.Constants;
import com.dagger2_rxjava.viewmodel.DrinkListActivityViewModel;

import java.util.ArrayList;
import java.util.HashMap;

public class DrinkListActivity extends BaseActivity<ActivityDrinkListBinding,DrinkListActivityViewModel> {

    private HashMap<String,String> map = new HashMap<>();
    private Context context;

    @Override
    public int getLayout() {
        return R.layout.activity_drink_list;
    }

    @Override
    public Class<DrinkListActivityViewModel> getViewModel() {
        return DrinkListActivityViewModel.class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        viewModel.getProsess().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean != null && aBoolean) {
                    showProgress();
                } else {
                    hideProgress();
                }
            }
        });

        map.clear();
        map.put(getIntent().getStringExtra(Constants.CATEGORY_FIELD_NAME),getIntent().getStringExtra(Constants.FILTER_NAME));
        viewModel.getDrinks(map).observe(this, new Observer<ArrayList<DrinkListModel.Drinks>>() {
            @Override
            public void onChanged(@Nullable ArrayList<DrinkListModel.Drinks> drinks) {
                setRecyclerView(drinks);
            }
        });

    }

    private void setRecyclerView(ArrayList<DrinkListModel.Drinks> drinks) {

        DrinksListAdapter drinksListAdapter =  new DrinksListAdapter(context,drinks);
        binding.listDrinks.setLayoutManager(new LinearLayoutManager(context));
        binding.listDrinks.setAdapter(drinksListAdapter);

    }
}
