package com.example.vetsertification.ui.registration;

import android.text.TextUtils;

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
        if (TextUtils.isEmpty(registrationData.getEmail()) || TextUtils.isEmpty(registrationData.getPassword())
                || TextUtils.isEmpty(registrationData.getPhone()) || TextUtils.isEmpty(registrationData.getName())
                || TextUtils.isEmpty(registrationData.getAddress()) || TextUtils.isEmpty((CharSequence) registrationData.getBirthday()))
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

