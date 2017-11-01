package com.nodomain.employeeapp.model;


import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.nodomain.employeeapp.utils.collection.Copyable;


public class Speciality implements Parcelable, Comparable<Speciality>, Copyable<Speciality> {

    private final long specialityId;
    private final String name;

    public static final Creator<Speciality> CREATOR = new Creator<Speciality>() {
        @Override
        public Speciality createFromParcel(Parcel in) {
            return new Speciality(in);
        }

        @Override
        public Speciality[] newArray(int size) {
            return new Speciality[size];
        }
    };

    public Speciality(long specialityId, String name) {
        this.specialityId = specialityId;
        this.name = name;
    }

    protected Speciality(Parcel in) {
        specialityId = in.readLong();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(specialityId);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public Speciality copy() {
        return new Speciality(specialityId, name);
    }

    @Override
    public int compareTo(@NonNull Speciality speciality) {
        return speciality.name.compareTo(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Speciality that = (Speciality) o;

        if (specialityId != that.specialityId) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (specialityId ^ (specialityId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }

    public long getSpecialityId() {
        return specialityId;
    }

    public String getName() {
        return name;
    }
}
