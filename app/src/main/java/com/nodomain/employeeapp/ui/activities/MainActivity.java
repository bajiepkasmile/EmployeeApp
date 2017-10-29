package com.nodomain.employeeapp.ui.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nodomain.employeeapp.navigation.MainNavigator;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainNavigator navigator = new MainNavigator(getSupportFragmentManager());

        if (savedInstanceState == null)
            navigator.navigateToEmployeeList();
    }
}
