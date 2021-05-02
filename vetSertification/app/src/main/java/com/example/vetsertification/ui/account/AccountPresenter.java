package com.example.vetsertification.ui.account;
import android.content.ContentValues;
import android.content.Intent;
import android.text.TextUtils;

import java.util.List;

import com.example.vetsertification.MainActivity;
import com.example.vetsertification.R;
//import ru.startandroid.mvpsample.common.User;
//import ru.startandroid.mvpsample.common.UserTable;
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
            //loadUsers();
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
                        view.showMessage("Неверный email или пароль");
                    else{
                        view.startMainActivity();
                    }; //todo сделать переход на страницу авторизованного пользователя
                }
            });
        }

        public void signUp() {
            //todo сделать переход на страницу регистрации
        }
    }

