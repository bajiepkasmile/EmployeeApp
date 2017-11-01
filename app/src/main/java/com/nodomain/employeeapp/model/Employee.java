package com.nodomain.employeeapp.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.nodomain.employeeapp.utils.collection.CollectionUtil;
import com.nodomain.employeeapp.utils.collection.Copyable;

import java.util.Date;
import java.util.List;


public class Employee implements Parcelable, Copyable<Employee> {

    private final String firstName;
    private final String lastName;
    private final Date birthdayDate;
    private final String avatarUrl;
    private final List<Speciality> specialities;

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

    public Employee(String firstName, String lastName, Date birthdayDate, String avatarUrl, List<Speciality> specialities) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdayDate = birthdayDate;
        this.avatarUrl = avatarUrl;
        this.specialities = specialities;
    }

    protected Employee(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        birthdayDate = birthdayTimeToBirthdayDate(in.readLong());
        avatarUrl = in.readString();
        specialities = in.createTypedArrayList(Speciality.CREATOR);
    }

    private static Date birthdayTimeToBirthdayDate(long birthdayTime) {
        if (birthdayTime == -1)
            return null;
        else
            return new Date(birthdayTime);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeLong(getBirthdayTime());
        dest.writeString(avatarUrl);
        dest.writeTypedList(specialities);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public Employee copy() {
        return new Employee(
                firstName,
                lastName,
                getBirthdayDateCopy(),
                avatarUrl,
                CollectionUtil.copyCollectionDeep(specialities));
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    private long getBirthdayTime() {
        if (birthdayDate != null)
            return birthdayDate.getTime();
        else
            return -1;
    }

    private Date getBirthdayDateCopy() {
        if (birthdayDate != null)
            return new Date(birthdayDate.getTime());
        else
            return null;
    }
}
