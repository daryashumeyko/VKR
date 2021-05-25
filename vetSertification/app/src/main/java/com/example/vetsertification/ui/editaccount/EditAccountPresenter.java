package com.example.vetsertification.ui.editaccount;

import android.text.TextUtils;
import com.example.vetsertification.ui.CurrentUser;
import com.example.vetsertification.ui.seeaccount.SeeAccountData;

public class EditAccountPresenter {
    private EditAccountView view;
    private final EditAccountModel model;

    public EditAccountPresenter(EditAccountModel model) {
        this.model = model;
    }

    public void attachView(EditAccountView editAccountView) {
        view = editAccountView;
    }

    public void detachView() {
        view = null;
    }

    public void viewIsReady() {
        //loadUsers();
    }

    public void saveAccount() {
        SeeAccountData seeAccountData = view.getSeeAccountData();
        if (TextUtils.isEmpty(seeAccountData.getPhone()) || TextUtils.isEmpty(seeAccountData.getAddress()) ||
            TextUtils.isEmpty(seeAccountData.getName()) || TextUtils.isEmpty(seeAccountData.getBirthday())) {
            view.showMessage("Заполните все поля");
            return;
        }
        view.showProgress();
        model.saveAccount(seeAccountData, new EditAccountModel.EditAccountCallback(){
            @Override
            public void onSaveAccount(Boolean result) {
                view.hideProgress();
                if (!result)
                    view.showMessage("Отсутствует соединение с сервером");
                else{
                    Boolean requestResult = CurrentUser.getInstance().getRegistrationData().getResult();
                    if (!requestResult)
                        view.showMessage("Неверный email или пароль");
                    else{
                        view.startSeeAccountActivity();
                    }
                }
            }
        });
    };

}
