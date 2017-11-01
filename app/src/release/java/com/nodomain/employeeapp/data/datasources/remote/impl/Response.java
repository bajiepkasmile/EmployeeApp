package com.nodomain.employeeapp.data.datasources.remote.impl;


import com.google.gson.annotations.SerializedName;
import com.nodomain.employeeapp.data.datasources.remote.impl.dtos.EmployeeDto;

import java.util.List;


public class Response {

    @SerializedName("response")
    public List<EmployeeDto> employeeDtos;
}
