package com.example.vetsertification.ui.seeaccount;

import android.graphics.Color;
import android.text.TextUtils;

import com.example.vetsertification.ui.registration.RegistrationData;
import com.example.vetsertification.ui.registration.RegistrationModel;
import com.example.vetsertification.ui.registration.RegistrationView;

public class SeeAccountPresenter {
    private SeeAccountView view;
    private final SeeAccountModel model;

    public SeeAccountPresenter(SeeAccountModel model) {
        this.model = model;
    }

    public void attachView(SeeAccountView seeAccountView) {
        view = seeAccountView;
    }

    public void detachView() {
        view = null;
    }

    public void viewIsReady() {
        //loadUsers();
    }

    public void editAccount() {
        view.startEditAccount();
    };

    public void seePets() {
        view.startSeePets();
    };
}
