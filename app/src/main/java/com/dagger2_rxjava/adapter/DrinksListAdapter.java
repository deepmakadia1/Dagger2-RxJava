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
import com.dagger2_rxjava.databinding.ItemDrinksBinding;
import com.dagger2_rxjava.models.entity.DrinkListModel;
import com.dagger2_rxjava.ui.activity.DetailDrinksActivity;
import com.dagger2_rxjava.util.Constants;

import java.util.List;

public class DrinksListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<DrinkListModel.Drinks> drinks;
    private Context context;

    public DrinksListAdapter(Context context, List<DrinkListModel.Drinks> drinks) {
        this.context = context;
        this.drinks = drinks;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_drinks, viewGroup, false);
        return new DrinksHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        DrinksHolder recipeHolder = (DrinksHolder) viewHolder;

        recipeHolder.itemDrinksBinding.setDrinks(drinks.get(i));

    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }

    private class DrinksHolder extends RecyclerView.ViewHolder {

        ItemDrinksBinding itemDrinksBinding;

        DrinksHolder(@NonNull View itemView) {
            super(itemView);
            itemDrinksBinding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, DetailDrinksActivity.class)
                            .putExtra(Constants.DRINK_ID, drinks.get(getAdapterPosition()).getIdDrink()));
                }
            });
        }
    }

}
