package com.dagger2_rxjava.ui.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.dagger2_rxjava.R;
import com.dagger2_rxjava.adapter.RecipeListAdapter;
import com.dagger2_rxjava.databinding.FragmentCategoryBinding;
import com.dagger2_rxjava.models.entity.RecipeModel;
import com.dagger2_rxjava.util.Constants;
import com.dagger2_rxjava.viewmodel.CategoryFragmentViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends BaseFragment<FragmentCategoryBinding,CategoryFragmentViewModel> {

    private Context context;
    public CategoryFragment() {
        // Required empty public constructor
    }

    public static CategoryFragment newInstance(String categoryName) {
        CategoryFragment categoryFragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString(Constants.CATEGORY_NAME, categoryName);
        categoryFragment.setArguments(args);
        return categoryFragment;
    }


    @Override
    public int getLayout() {
        return R.layout.fragment_category;
    }

    @Override
    public Class<CategoryFragmentViewModel> getViewModel() {
        return CategoryFragmentViewModel.class;
    }

    @Override
    public void onCreateView() {

        String categoryName = getArguments() != null ? getArguments().getString(Constants.CATEGORY_NAME) : null;
        CategoryFragmentViewModel categoryFragmentViewModel = ViewModelProviders.of(this).get(CategoryFragmentViewModel.class);

        categoryFragmentViewModel.getRecipeList(categoryName).observe(this, new Observer<List<RecipeModel.Recipe>>() {
            @Override
            public void onChanged(@Nullable List<RecipeModel.Recipe> recipes) {
                setRecyclerView(recipes);
            }
        });

        categoryFragmentViewModel.getProgress().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean != null && aBoolean) {
                    binding.listRecipe.setVisibility(View.GONE);
                    binding.progress.setVisibility(View.VISIBLE);
                } else {
                    binding.listRecipe.setVisibility(View.VISIBLE);
                    binding.progress.setVisibility(View.GONE);
                }
            }
        });

    }


    private void setRecyclerView(List<RecipeModel.Recipe> recipes) {

        RecipeListAdapter recipeListAdapter = new RecipeListAdapter(context, recipes);
        binding.listRecipe.setLayoutManager(new LinearLayoutManager(context));
        binding.listRecipe.setAdapter(recipeListAdapter);

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
