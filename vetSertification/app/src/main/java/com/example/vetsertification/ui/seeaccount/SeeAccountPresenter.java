package com.example.vetsertification.ui.seeaccount;

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
