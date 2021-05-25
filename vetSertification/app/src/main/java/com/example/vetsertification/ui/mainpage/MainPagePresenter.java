package com.example.vetsertification.ui.mainpage;

public class MainPagePresenter {
    private MainPageView view;
    private final MainPageModel model;

    public MainPagePresenter(MainPageModel model) {
        this.model = model;
    }

    public void attachView(MainPageView mainPageView) {
        view = mainPageView;
    }

    public void detachView() {
        view = null;
    }

    public void viewIsReady() {
    }

    public void authorization() {
        view.startAuthorization();
    };

    public void instruction() {
        view.startSeeInstruction();
    };
}
