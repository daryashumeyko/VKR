package com.example.vetsertification.ui.account;

import android.text.TextUtils;
import com.example.vetsertification.ui.CurrentUser;

public class AccountPresenter {

        private AccountView view;
        private final AccountModel model;

        public AccountPresenter(AccountModel model) {
            this.model = model;
        }

        public void attachView(AccountView accountView) {
            view = accountView;
        }

        public void detachView() {
            view = null;
        }

        public void viewIsReady() {
        }

        public void signIn() {
            AccountData accountData = view.getAccountData();
            if (TextUtils.isEmpty(accountData.getEmail()) || TextUtils.isEmpty(accountData.getPassword())) {
                view.showMessage("Заполните все поля");
                return;
            }
            view.showProgress();
            model.signIn(accountData, new AccountModel.SignInCallback(){
                @Override
                public void onSignIn(Boolean result) {
                    view.hideProgress();
                    if (!result)
                        view.showMessage("Отсутствует соединение с сервером");
                    else{
                        Boolean requestResult = CurrentUser.getInstance().getRegistrationData().getResult();
                        if (!requestResult)
                            view.showMessage("Неверный email или пароль");
                        else{
                            view.startUserMainActivity();
                        }
                    }
                }
            });
        }

        public void signUp() {
            view.startRegistrationActivity();
        }

        public void fgtPsw() {
            view.startForgetPasswordActivity();
        }
    }

