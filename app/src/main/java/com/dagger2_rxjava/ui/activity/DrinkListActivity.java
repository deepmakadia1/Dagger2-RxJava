package com.dagger2_rxjava.ui.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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

public class DrinkListActivity extends AppCompatActivity {

    private ActivityDrinkListBinding binding;
    private ProgressDialog progressDialog;
    private HashMap<String,String> map = new HashMap<>();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = this;
        context = this;
        binding = DataBindingUtil.setContentView(activity,R.layout.activity_drink_list);

        DrinkListActivityViewModel drinkListActivityViewModel = ViewModelProviders.of(this).get(DrinkListActivityViewModel.class);

        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);


        drinkListActivityViewModel.getProsess().observe(this, new Observer<Boolean>() {
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

        map.clear();
        map.put(getIntent().getStringExtra(Constants.CATEGORY_FIELD_NAME),getIntent().getStringExtra(Constants.FILTER_NAME));
        drinkListActivityViewModel.getDrinks(map).observe(this, new Observer<ArrayList<DrinkListModel.Drinks>>() {
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
