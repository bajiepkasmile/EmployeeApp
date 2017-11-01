package com.nodomain.employeeapp.data.datasources.local.impl;


import com.nodomain.employeeapp.model.Speciality;

import java.util.Date;
import java.util.List;

import nl.qbusict.cupboard.annotation.Column;

public class EmployeeDbo {

    public Long _id;
    @Column("first_name") public String firstName;
    @Column("last_name") public String lastName;
    @Column("birthday") public Date birthdayDate;
    @Column("avatar_url") public String avatarUrl;
    public List<Speciality> specialities;
}
