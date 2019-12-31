package com.dagger2_rxjava.ui.activity;

import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dagger2_rxjava.R;
import com.dagger2_rxjava.databinding.ActivityDrinksBinding;
import com.dagger2_rxjava.util.Constants;


public class DrinksActivity extends BaseActivity<ActivityDrinksBinding, AndroidViewModel> implements View.OnClickListener {

    private Context context;

    @Override
    public int getLayout() {
        return R.layout.activity_drinks;
    }

    @Override
    public Class<AndroidViewModel> getViewModel() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        binding.listByCategory.setOnClickListener(this);
        binding.listByGlasses.setOnClickListener(this);
        binding.listByIngredients.setOnClickListener(this);
        binding.listByAlcoholic.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.list_by_category:
                startActivity(new Intent(context,CategoryListActivity.class).putExtra(Constants.CATEGORY_FIELD_NAME,Constants.PARAM_C));
                break;
            case R.id.list_by_glasses:
                startActivity(new Intent(context,CategoryListActivity.class).putExtra(Constants.CATEGORY_FIELD_NAME,Constants.PARAM_G));
                break;
            case R.id.list_by_ingredients:
                startActivity(new Intent(context,CategoryListActivity.class).putExtra(Constants.CATEGORY_FIELD_NAME,Constants.PARAM_I));
                break;
            case R.id.list_by_alcoholic:
                startActivity(new Intent(context,CategoryListActivity.class).putExtra(Constants.CATEGORY_FIELD_NAME,Constants.PARAM_A));
                break;

        }
    }
}
