package com.dagger2_rxjava.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dagger2_rxjava.R;
import com.dagger2_rxjava.databinding.ItemDrinkCategoryBinding;
import com.dagger2_rxjava.models.entity.DrinkCategoryListModel;
import com.dagger2_rxjava.ui.activity.DrinkListActivity;
import com.dagger2_rxjava.util.Constants;

import java.util.List;

public class DrinkCategoryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<DrinkCategoryListModel.DrinkCategories> drinkCategories;
    private Context context;
    private String category_field_name;

    public DrinkCategoryListAdapter(Context context, List<DrinkCategoryListModel.DrinkCategories> drinkCategories, String category_field_name) {
        this.context = context;
        this.drinkCategories = drinkCategories;
        this.category_field_name = category_field_name;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_drink_category, viewGroup, false);
        return new DrinkCategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        DrinkCategoryHolder drinkCategoryHolder = (DrinkCategoryHolder) viewHolder;

        drinkCategoryHolder.itemDrinkCategoryBinding.setCatName(drinkCategories.get(i));

    }

    @Override
    public int getItemCount() {
        return drinkCategories.size();
    }

    private class DrinkCategoryHolder extends RecyclerView.ViewHolder {

        ItemDrinkCategoryBinding itemDrinkCategoryBinding;

        DrinkCategoryHolder(@NonNull View itemView) {
            super(itemView);
            itemDrinkCategoryBinding = DataBindingUtil.bind(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context,DrinkListActivity.class)
                    .putExtra(Constants.CATEGORY_FIELD_NAME,category_field_name)
                    .putExtra(Constants.FILTER_NAME,drinkCategories.get(getAdapterPosition()).getStrData()));
                }
            });

        }
    }

}
