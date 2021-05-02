package com.example.vetsertification.ui.registration;

import android.text.TextUtils;

import com.example.vetsertification.ui.account.AccountModel;
import com.example.vetsertification.ui.registration.*;

public class RegistrationPresenter {

    private RegistrationView view;
    private final RegistrationModel model;

    public RegistrationPresenter(RegistrationModel model) {
        this.model = model;
    }

    public void attachView(RegistrationView registrationView) {
        view = registrationView;
    }

    public void detachView() {
        view = null;
    }

    public void viewIsReady() {
        //loadUsers();
    }

    public void register() {
        RegistrationData registrationData = view.getRegistrationData();
        if (TextUtils.isEmpty(registrationData.getEmail()) ||
                TextUtils.isEmpty(registrationData.getPassword()) ||
                //TextUtils.isEmpty(registrationData.getPhone()) ||todo исправить
                TextUtils.isEmpty(registrationData.getName()) ||
                TextUtils.isEmpty(registrationData.getAddress()) )
                //TextUtils.isEmpty(registrationData.getBirthday()) //todo исправить
                {
            view.showMessage("Заполните все поля");
            return;
        }
        view.showProgress();
        model.register(registrationData, new RegistrationModel.RegisterCallback(){
            @Override
            public void onRegister(Boolean result) {
                view.hideProgress();
                    view.startAccountActivity();
                };
        });
    }
}

