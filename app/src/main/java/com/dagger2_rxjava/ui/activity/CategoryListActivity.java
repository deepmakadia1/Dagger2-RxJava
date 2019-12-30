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
import com.dagger2_rxjava.adapter.DrinkCategoryListAdapter;
import com.dagger2_rxjava.databinding.ActivityCategoryListBinding;
import com.dagger2_rxjava.models.entity.DrinkCategoryListModel;
import com.dagger2_rxjava.util.Constants;
import com.dagger2_rxjava.viewmodel.DrinkCategoryListViewModel;

import java.util.ArrayList;
import java.util.HashMap;

public class CategoryListActivity extends AppCompatActivity {

    private ActivityCategoryListBinding binding;
    private ProgressDialog progressDialog;
    private HashMap<String,String> map = new HashMap<>();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = this;
        context = this;
        binding = DataBindingUtil.setContentView(activity,R.layout.activity_category_list);

        DrinkCategoryListViewModel drinkCategoryListViewModel = ViewModelProviders.of(this).get(DrinkCategoryListViewModel.class);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        drinkCategoryListViewModel.getProgress().observe(this, new Observer<Boolean>() {
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
        map.put(getIntent().getStringExtra(Constants.CATEGORY_FIELD_NAME),Constants.LIST);
        drinkCategoryListViewModel.getDrinksCAtegory(map).observe(this, new Observer<ArrayList<DrinkCategoryListModel.DrinkCategories>>() {
            @Override
            public void onChanged(@Nullable ArrayList<DrinkCategoryListModel.DrinkCategories> drinkCategories) {
                setRecyclerView(drinkCategories);
            }
        });

    }

    private void setRecyclerView(ArrayList<DrinkCategoryListModel.DrinkCategories> drinkCategories) {

        DrinkCategoryListAdapter drinkCategoryListAdapter = new DrinkCategoryListAdapter(context, drinkCategories,getIntent().getStringExtra(Constants.CATEGORY_FIELD_NAME));
        binding.listCategory.setLayoutManager(new LinearLayoutManager(context));
        binding.listCategory.setAdapter(drinkCategoryListAdapter);

    }
}
