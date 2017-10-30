package com.nodomain.employeeapp.ui.activities;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nodomain.employeeapp.App;
import com.nodomain.employeeapp.di.components.MainActivitySubComponent;
import com.nodomain.employeeapp.di.modules.MainActivityModule;
import com.nodomain.employeeapp.navigation.MainNavigator;


public class MainActivity extends AppCompatActivity {

    private MainActivitySubComponent mainActivitySubComponent;

    public static MainActivitySubComponent getMainActivitySubComponent(Activity activity) {
        return ((MainActivity) activity).mainActivitySubComponent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainNavigator navigator = new MainNavigator(getSupportFragmentManager());
        initMainActivitySubComponent(navigator);

        if (savedInstanceState == null)
            navigator.navigateToEmployeeList();
    }

    private void initMainActivitySubComponent(MainNavigator navigator) {
        mainActivitySubComponent =
                App.getApplicationComponent(getApplicationContext())
                        .plusMainActivitySubComponent(new MainActivityModule(navigator));
    }
}
