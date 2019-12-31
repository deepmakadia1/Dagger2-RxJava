package com.dagger2_rxjava.ui.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<T extends ViewDataBinding, V extends AndroidViewModel> extends AppCompatActivity {

    public abstract int getLayout();

    public abstract Class<V> getViewModel();

    public T binding;

    public V viewModel;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = this;
        binding = DataBindingUtil.setContentView(activity, getLayout());

        if (getViewModel() != null) {
            viewModel = ViewModelProviders.of(this).get(getViewModel());
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

    }

    public void showProgress() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    public void hideProgress() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }
}
