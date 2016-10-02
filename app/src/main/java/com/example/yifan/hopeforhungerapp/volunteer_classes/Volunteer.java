package com.example.yifan.hopeforhungerapp.volunteer_classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Yifan on 10/1/2016.
 */

public abstract class Volunteer implements Parcelable {
    private String name;
    private String address;
    private String phoneNum;
    private ArrayList<String> physicalLimitations;
    private String signUpDate;
    private boolean signedIn;

    private int currentHoursWorked;
    private int weeklyHoursWorked;

    transient private Date startTime;
    transient private Date endTime;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public int getCurrentHoursWorked() {return currentHoursWorked; }

    public int getWeeklyHoursWorked() { return weeklyHoursWorked; }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public boolean isSignedIn() {
        return signedIn;
    }

    public void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }

    public ArrayList<String> getPhysicalLimitations() {
        return physicalLimitations;
    }

    public void setPhysicalLimitations(ArrayList<String> physicalLimitations) {
        this.physicalLimitations = physicalLimitations;
    }

    public void setSignUpDate(String signUpDate) {
        this.signUpDate = signUpDate;
    }

    public String getSignUpDate() {
        return signUpDate;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    protected Volunteer(Parcel in) {
        name = in.readString();
        address = in.readString();
        phoneNum = in.readString();
        if (in.readByte() == 0x01) {
            physicalLimitations = new ArrayList<String>();
            in.readList(physicalLimitations, String.class.getClassLoader());
        } else {
            physicalLimitations = null;
        }
        signUpDate = in.readString();
        signedIn = in.readByte() != 0x00;
        currentHoursWorked = in.readInt();
        weeklyHoursWorked = in.readInt();
        long tmpStartTime = in.readLong();
        startTime = tmpStartTime != -1 ? new Date(tmpStartTime) : null;
        long tmpEndTime = in.readLong();
        endTime = tmpEndTime != -1 ? new Date(tmpEndTime) : null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(phoneNum);
        if (physicalLimitations == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(physicalLimitations);
        }
        dest.writeString(signUpDate);
        dest.writeByte((byte) (signedIn ? 0x01 : 0x00));
        dest.writeInt(currentHoursWorked);
        dest.writeInt(weeklyHoursWorked);
        dest.writeLong(startTime != null ? startTime.getTime() : -1L);
        dest.writeLong(endTime != null ? endTime.getTime() : -1L);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Volunteer> CREATOR = new Parcelable.Creator<Volunteer>() {
        @Override
        public Volunteer createFromParcel(Parcel in) {
            return new Volunteer(in);
        }

        @Override
        public Volunteer[] newArray(int size) {
            return new Volunteer[size];
        }
    };
}