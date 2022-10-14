package com.hacktiv8.joyshop.model;

public class User {

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
}
