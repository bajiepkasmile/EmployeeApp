package com.nodomain.employeeapp.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.nodomain.employeeapp.utils.Copyable;


public class Employee implements Parcelable, Copyable<Employee> {

    private final String firstName;
    private final String lastName;
    private final long birthdayTime;
    private final String avatarUrl;
    private final long specialityId;
    private final String speciality;

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

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

    protected Employee(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        birthdayTime = in.readLong();
        avatarUrl = in.readString();
        specialityId = in.readLong();
        speciality = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeLong(birthdayTime);
        dest.writeString(avatarUrl);
        dest.writeLong(specialityId);
        dest.writeString(speciality);
    }

    @Override
    public int describeContents() {
        return 0;
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
