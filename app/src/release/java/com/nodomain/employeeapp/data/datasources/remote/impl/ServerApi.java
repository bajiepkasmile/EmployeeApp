package com.nodomain.employeeapp.data.datasources.remote.impl;


import retrofit2.Call;
import retrofit2.http.GET;


public interface ServerApi {

    @GET("65gb/static/raw/master/testTask.json")
    Call<Response> getEmployees();
}
