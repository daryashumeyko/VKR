package com.example.vetsertification.ui.registration;

import android.graphics.Color;
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
        if ((registrationData.getEmailColor() == Color.parseColor("#ff808080")) ||
            (registrationData.getPhoneColor() == Color.parseColor("#ff808080")) ||
            (registrationData.getBirthdayColor() == Color.parseColor("#ff808080")))
        {
            view.showMessage("Данные, выделенные красным цветом, введены некорректны");
            return;
        }
        //todo не работает проверка по цвету текста
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

