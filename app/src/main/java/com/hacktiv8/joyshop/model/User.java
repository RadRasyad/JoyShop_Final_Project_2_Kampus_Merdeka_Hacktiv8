package com.hacktiv8.joyshop.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class User implements Parcelable {

    public User() {

    }

    public User(String uId, String username, String email, String phone, String role) {
        this.uId = uId;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    String uId;
    String username;
    String email;
    String phone;
    //0 admin, 1 staff, 2 user
    String role;

    public String getuId() {
        return uId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getRole() {
        return role;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(uId);
        dest.writeString(username);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(role);
    }

    protected User(Parcel in) {
        uId = in.readString();
        username = in.readString();
        email = in.readString();
        phone = in.readString();
        role = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
