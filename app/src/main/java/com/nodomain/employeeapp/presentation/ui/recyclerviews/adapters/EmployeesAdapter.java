package com.nodomain.employeeapp.presentation.ui.recyclerviews.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nodomain.employeeapp.R;
import com.nodomain.employeeapp.model.Employee;
import com.nodomain.employeeapp.presentation.ui.listeners.OnItemClickListener;
import com.nodomain.employeeapp.presentation.ui.recyclerviews.viewholders.EmployeeViewHolder;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

import static com.nodomain.employeeapp.utils.DateUtil.dateToAge;


public class EmployeesAdapter extends RecyclerView.Adapter<EmployeeViewHolder> {

    private List<Employee> employees;
    private OnItemClickListener listener;

    public EmployeesAdapter(List<Employee> employees, OnItemClickListener listener) {
        this.employees = employees;
        this.listener = listener;
    }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee, parent, false);
        return new EmployeeViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, int position) {
        Employee employee = employees.get(position);
        Context context = holder.ivAvatar.getContext();

        String fullName = context.getString(R.string.employee_full_name, employee.getFirstName(), employee.getLastName());
        String ageStr = birthdayDateToAgeStr(context, employee.getBirthdayDate());

        holder.tvFullName.setText(fullName);
        holder.tvAge.setText(ageStr);
        Picasso.with(context)
                .load(employee.getAvatarUrl())
                .placeholder(R.drawable.ic_person)
                .into(holder.ivAvatar);
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
        notifyDataSetChanged();
    }

    public Employee getItem(int position) {
        return employees.get(position);
    }

    private String birthdayDateToAgeStr(Context context, Date birthdayDate) {
        int age = dateToAge(birthdayDate);
        if (age == 0)
            return context.getString(R.string.zero_age);
        else
            return context.getResources().getQuantityString(R.plurals.age, age, age);
    }
}
