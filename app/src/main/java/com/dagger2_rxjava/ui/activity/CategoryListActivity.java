package com.dagger2_rxjava.ui.activity;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;
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

public class CategoryListActivity extends BaseActivity<ActivityCategoryListBinding,DrinkCategoryListViewModel> {

    private Context context;
    private HashMap<String,String> map = new HashMap<>();

    @Override
    public int getLayout() {
        return R.layout.activity_category_list;
    }

    @Override
    public Class<DrinkCategoryListViewModel> getViewModel() {
        return DrinkCategoryListViewModel.class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
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

        map.clear();
        map.put(getIntent().getStringExtra(Constants.CATEGORY_FIELD_NAME),Constants.LIST);
        viewModel.getDrinksCategory(map).observe(this, new Observer<ArrayList<DrinkCategoryListModel.DrinkCategories>>() {
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
