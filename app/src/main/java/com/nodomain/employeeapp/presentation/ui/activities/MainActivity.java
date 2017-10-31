package com.nodomain.employeeapp.presentation.ui.activities;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nodomain.employeeapp.App;
import com.nodomain.employeeapp.di.components.MainActivitySubComponent;
import com.nodomain.employeeapp.di.modules.MainActivityModule;
import com.nodomain.employeeapp.presentation.navigation.MainNavigator;


public class MainActivity extends AppCompatActivity {

    private MainActivitySubComponent mainActivitySubComponent;
    private MainNavigator navigator;

    public static MainActivitySubComponent getMainActivitySubComponent(Activity activity) {
        return ((MainActivity) activity).mainActivitySubComponent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        navigator = new MainNavigator(getSupportFragmentManager());
        initMainActivitySubComponent();

        if (savedInstanceState == null)
            navigator.navigateToEmployeeList();
    }

    @Override
    public void onBackPressed() {
        if (navigator.hasPreviousView())
            navigator.navigateToPreviousView();
        else
            super.onBackPressed();
    }

    private void initMainActivitySubComponent() {
        mainActivitySubComponent =
                App.getApplicationComponent(getApplicationContext())
                        .plusMainActivitySubComponent(new MainActivityModule(navigator));
    }
}
