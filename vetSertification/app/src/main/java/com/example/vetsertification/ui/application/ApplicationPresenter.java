package com.example.vetsertification.ui.application;

import android.text.TextUtils;

public class ApplicationPresenter {

    private ApplicationView view;
    private final ApplicationModel model;

    public ApplicationPresenter(ApplicationModel model) {
        this.model = model;
    }

    public void attachView(ApplicationView applicationView) {
        view = applicationView;
    }

    public void detachView() {
        view = null;
    }

    public void viewIsReady() {
        //loadUsers();
    }

    public void doApp() {

        ApplicationData applicationData = view.getApplicationData();
        if (TextUtils.isEmpty(applicationData.getFromCountry()) || TextUtils.isEmpty(applicationData.getToCountry())
                || TextUtils.isEmpty((CharSequence) applicationData.getDateOfExport()) || TextUtils.isEmpty(applicationData.getName())
                || TextUtils.isEmpty((CharSequence) applicationData.getBirthday()) || TextUtils.isEmpty(applicationData.getEmail())
                || TextUtils.isEmpty(applicationData.getPhone()) || TextUtils.isEmpty(applicationData.getAddress())
                || TextUtils.isEmpty(applicationData.getTypeOfTransport()) || TextUtils.isEmpty(applicationData.getNumberOfTransport())
                || TextUtils.isEmpty(applicationData.getKindOfAnimal()) || TextUtils.isEmpty(applicationData.getPetName())
                || TextUtils.isEmpty(applicationData.getBreed()) || TextUtils.isEmpty((CharSequence) applicationData.getPetBirthday())
                || TextUtils.isEmpty(applicationData.getPetAddress()) || TextUtils.isEmpty(applicationData.getCountryOfOrigin())
                || TextUtils.isEmpty(applicationData.getIdentificationSystem()) || TextUtils.isEmpty((CharSequence) applicationData.getDateOfChipping())
                || TextUtils.isEmpty(applicationData.getNumber()) || TextUtils.isEmpty(applicationData.getMethodOfResearch())
                || TextUtils.isEmpty((CharSequence) applicationData.getDate()) || TextUtils.isEmpty(applicationData.getNameOfDesease())
                || TextUtils.isEmpty(applicationData.getResult()) || TextUtils.isEmpty(applicationData.getNameOfVaccine())
                || TextUtils.isEmpty((CharSequence) applicationData.getDate2()) || TextUtils.isEmpty(applicationData.getNameOfDesease2())
                || TextUtils.isEmpty(applicationData.getSeriesOfVaccine()) || TextUtils.isEmpty((CharSequence) applicationData.getShelfLifeVaccine())) {
            view.showMessage("Заполните все поля");
            return;
        }
        view.showProgress();
        model.doApp(applicationData, new ApplicationModel.DoAppCallback(){
            @Override
            public void onDoApp(Boolean result) {
                view.hideProgress();
                view.startUserMainActivity();
            }
        });
    }
}