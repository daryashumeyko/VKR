package com.example.vetsertification.ui.forgetpassword;

import android.text.TextUtils;

public class ForgetPasswordPresenter {

    private ForgetPasswordView view;
    private final ForgetPasswordModel model;

    public ForgetPasswordPresenter(ForgetPasswordModel model) {
        this.model = model;
    }

    public void attachView(ForgetPasswordView forgetPasswordView) {
        view = forgetPasswordView;
    }

    public void detachView() {
        view = null;
    }

    public void viewIsReady() {
    }

    public void forgetpassword() {
        ForgetPasswordData forgetPasswordData = view.getForgetPasswordData();
        if (TextUtils.isEmpty(forgetPasswordData.getEmail())) {
            view.showMessage("Заполните все поля");
            return;
        }
        view.showProgress();
        model.forgetpassword(forgetPasswordData, new ForgetPasswordModel.ForgetPasswordCallback(){
            @Override
            public void onForgetPassword(Boolean result) {
                view.hideProgress();
                view.startUserMainActivity();
            }
        });
    }
}
