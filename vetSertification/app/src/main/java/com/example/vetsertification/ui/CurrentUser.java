package com.example.vetsertification.ui;

import com.example.vetsertification.ui.registration.RegistrationData;


public class CurrentUser {

    private static final CurrentUser INSTANCE = new CurrentUser();

    private CurrentUser(){
        registrationData = new RegistrationData();
    }

    public static CurrentUser getInstance(){
        return INSTANCE;
    }

    public RegistrationData getRegistrationData() {
        return registrationData;
    }

    public void setRegistrationData(RegistrationData registrationData) {
        this.registrationData = registrationData;
    }

    private RegistrationData registrationData;
}