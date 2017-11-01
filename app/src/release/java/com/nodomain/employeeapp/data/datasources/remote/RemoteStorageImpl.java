package com.nodomain.employeeapp.data.datasources.remote;


import com.nodomain.employeeapp.data.datasources.remote.impl.DtoMapper;
import com.nodomain.employeeapp.data.datasources.remote.impl.ServerApiConstants;
import com.nodomain.employeeapp.data.datasources.remote.impl.Response;
import com.nodomain.employeeapp.data.datasources.remote.impl.ServerApi;
import com.nodomain.employeeapp.domain.exceptions.ConnectionFailedException;
import com.nodomain.employeeapp.model.Employee;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RemoteStorageImpl implements RemoteStorage{

    private final ServerApi serverApi = new Retrofit.Builder()
            .baseUrl(ServerApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ServerApi.class);;

    @Inject
    public RemoteStorageImpl() {
    }

    @Override
    public List<Employee> getEmployees() {
        try {
            Response response = serverApi.getEmployees().execute().body();
            return DtoMapper.fromEmployeeDtos(response.employeeDtos);
        } catch (IOException | NullPointerException e) {
            throw new ConnectionFailedException();
        }
    }
}
