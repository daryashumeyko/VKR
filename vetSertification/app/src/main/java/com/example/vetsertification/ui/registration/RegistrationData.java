package com.example.vetsertification.ui.registration;

import java.util.Date;

public class RegistrationData {
    private String email;
    private String password;
    private String name;
    private String birthday;
    private String phone;
    private String address;
    private Integer id;
    private Boolean result;
    public int emailColor;
    public int birthdayColor;
    public int phoneColor;

    public Boolean getResult() { return result; }

    public void setResult(Boolean result) { this.result = result; }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public int getPhoneColor() {
        return phoneColor;
    }

    public void setPhoneColor(int phoneColor) {
        this.phoneColor = phoneColor;
    }

    public int getBirthdayColor() {
        return birthdayColor;
    }

    public void setBirthdayColor(int birthdayColor) {
        this.birthdayColor = birthdayColor;
    }

    public int getEmailColor() {
        return emailColor;
    }

    public void setEmailColor(int emailColor) {
        this.emailColor = emailColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
