package com.nodomain.employeeapp.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nodomain.employeeapp.R;
import com.nodomain.employeeapp.domain.Error;
import com.nodomain.employeeapp.model.Employee;
import com.nodomain.employeeapp.mvp.presenters.EmployeeDetailsMvpPresenter;
import com.nodomain.employeeapp.mvp.views.EmployeeDetailsMvpView;
import com.nodomain.employeeapp.navigation.EmployeeDetailsNavigator;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

import static com.nodomain.employeeapp.utils.DateUtil.dateToAge;
import static com.nodomain.employeeapp.utils.DateUtil.dateToBirthdayStr;


public class EmployeeDetailsFragment
        extends BaseMvpFragment<EmployeeDetailsMvpPresenter, EmployeeDetailsNavigator>
        implements EmployeeDetailsMvpView {

    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.tv_age)
    TextView tvAge;
    @BindView(R.id.tv_speciality)
    TextView tvSpeciality;

    private static final String ARG_EMPLOYEE = "employee";

    public static EmployeeDetailsFragment newInstance(Employee employee) {
        EmployeeDetailsFragment fragment = new EmployeeDetailsFragment();

        Bundle args = new Bundle();
        args.putParcelable(ARG_EMPLOYEE, employee);
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_employee_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        displayHomeButton();

        if (savedInstanceState == null) {
            Employee employee = getEmployeeFromArgs();
            mvpPresenter.getEmployeeDetails(employee);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            navigator.navigateToEmployeeList();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showEmployeeDetails(Employee employee) {
        String fullName = getString(R.string.employee_full_name, employee.getFirstName(), employee.getLastName());
        String birthdayStr = dateToBirthdayStr(employee.getBirthdayDate());
        int age = dateToAge(employee.getBirthdayDate());
        String ageStr = getResources().getQuantityString(R.plurals.age, age, age);

        Picasso.with(getContext()).load(employee.getAvatarUrl()).into(ivAvatar);
        setTitle(fullName);
        tvBirthday.setText(birthdayStr);
        tvAge.setText(ageStr);
        tvSpeciality.setText(employee.getSpeciality());
    }

    @Override
    public void showEmployeeListView() {
        navigator.navigateToEmployeeList();
    }

    @Override
    public void showError(Error error) {
    }

    private Employee getEmployeeFromArgs() {
        return getArguments().getParcelable(ARG_EMPLOYEE);
    }
}
