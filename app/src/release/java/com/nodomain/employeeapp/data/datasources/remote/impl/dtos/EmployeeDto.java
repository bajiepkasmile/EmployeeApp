package com.nodomain.employeeapp.data.datasources.remote.impl.dtos;


import com.google.gson.annotations.SerializedName;

import java.util.List;


public class EmployeeDto {

    @SerializedName("f_name") public String firstName;
    @SerializedName("l_name") public String lastName;
    @SerializedName("birthday") public String birthday;
    @SerializedName("avatr_url") public String avatarUrl;
    @SerializedName("specialty") public List<SpecialityDto> specialityDtos;
}
