package com.example.vetsertification.ui.userMainPage;

public class UserMainPagePresenter {
    private UserMainPageView view;
    private final UserMainPageModel model;

    public UserMainPagePresenter(UserMainPageModel model) {
        this.model = model;
    }

    public void attachView(UserMainPageView userMainPageView) {
        view = userMainPageView;
    }

    public void detachView() {
        view = null;
    }

    public void viewIsReady() {
    }

    public void seeAccount() {
        view.startSeeAccount();
    };

    public void instruction() {
        view.startSeeInstruction();
    };

    public void application() {
        view.startApplicationActivity();
    };

    public void recVetOff() {
        view.startRecVetOffActivity();
    };

    public void recRosselchoz() {
        view.startRecRosselchozActivity();
    };
}
