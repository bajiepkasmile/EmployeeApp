package com.nodomain.employeeapp.data.datasources.remote.impl.dtos;


import com.google.gson.annotations.SerializedName;


public class SpecialityDto {

    @SerializedName("specialty_id") public long specialityId;
    @SerializedName("name") public String name;
}
