package com.nodomain.employeeapp.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.nodomain.employeeapp.R;
import com.nodomain.employeeapp.mvp.presenters.MvpPresenter;
import com.nodomain.employeeapp.mvp.views.MvpView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseMvpFragment<P extends MvpPresenter, N> extends Fragment implements MvpView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    protected N navigator;
    protected P mvpPresenter;

    private Unbinder unbinder;

    protected BaseMvpFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        mvpPresenter.attachMvpView(this);
        setupActionBar();
    }

    @Override
    public void onDestroyView() {
        mvpPresenter.detachMvpView();
        unbinder.unbind();
        super.onDestroyView();
    }

    protected void setTitle(String title) {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    protected void displayHomeButton() {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupActionBar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }
}
