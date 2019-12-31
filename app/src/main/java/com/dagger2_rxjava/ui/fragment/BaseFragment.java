package com.dagger2_rxjava.ui.fragment;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment<T extends ViewDataBinding, V extends AndroidViewModel> extends Fragment {

    public abstract int getLayout();

    public abstract Class<V> getViewModel();

    public abstract void onCreateView();

    public T binding;

    public V viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false);

        if (getViewModel() != null) {
            viewModel = ViewModelProviders.of(this).get(getViewModel());
        }

        onCreateView();

        return binding.getRoot();
    }
}
