package com.example.vetsertification.ui.editaccount;

import com.example.vetsertification.ui.seeaccount.SeeAccountModel;
import com.example.vetsertification.ui.seeaccount.SeeAccountView;

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

    public void editAccount() {
        view.startSeeAccount();
    };

}
