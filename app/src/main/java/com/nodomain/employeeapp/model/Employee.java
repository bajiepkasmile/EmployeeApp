package com.nodomain.employeeapp.model;


import com.nodomain.employeeapp.utils.Copyable;


public class Employee implements Copyable<Employee> {

    private String firstName;
    private String lastName;
    private long birthdayTime;
    private String avatarUrl;
    private long specialityId;
    private String speciality;

    public Employee(String firstName,
                    String lastName,
                    long birthdayTime,
                    String avatarUrl,
                    long specialityId,
                    String speciality) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdayTime = birthdayTime;
        this.avatarUrl = avatarUrl;
        this.specialityId = specialityId;
        this.speciality = speciality;
    }

    @Override
    public Employee copy() {
        return new Employee(firstName, lastName, birthdayTime, avatarUrl, specialityId, speciality);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getBirthdayTime() {
        return birthdayTime;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public long getSpecialityId() {
        return specialityId;
    }

    public String getSpeciality() {
        return speciality;
    }
}
