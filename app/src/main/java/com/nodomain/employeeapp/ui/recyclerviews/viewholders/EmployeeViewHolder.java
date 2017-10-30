package com.nodomain.employeeapp.ui.recyclerviews.viewholders;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nodomain.employeeapp.R;
import com.nodomain.employeeapp.ui.listeners.OnItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;


public class EmployeeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    @BindView(R.id.iv_avatar)
    public ImageView ivAvatar;
    @BindView(R.id.tv_full_name)
    public TextView tvFullName;
    @BindView(R.id.tv_age)
    public TextView tvAge;

    private final OnItemClickListener listener;

    public EmployeeViewHolder(View itemView, OnItemClickListener listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.listener = listener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        listener.onItemClick(getAdapterPosition());
    }
}
