package com.example.vetsertification.ui.recordrosselchoz;

import android.text.TextUtils;

public class RecordRosselchozPresenter {

    private RecordRosselchozView view;
    private final RecordRosselchozModel model;

    public RecordRosselchozPresenter(RecordRosselchozModel model) {
        this.model = model;
    }

    public void attachView(RecordRosselchozView recordRosselchozView) {
        view = recordRosselchozView;
    }

    public void detachView() {
        view = null;
    }

    public void viewIsReady() {
    }

    public void recRosselchoz() {
        RecordRosselchozData recordRosselchozData = view.getRecordRosselchozData();
        if (TextUtils.isEmpty(recordRosselchozData.getRosselchoz()) /*||
                TextUtils.isEmpty(recordVetOffData.getDate()) || TextUtils.isEmpty(recordVetOffData.getTime())*/) {
            view.showMessage("Заполните все поля");
            return;
        }
        view.showProgress();
        model.recRosselchoz(recordRosselchozData, new RecordRosselchozModel.RosselchozCallback(){
            @Override
            public void onRosselchoz(Boolean result) {
                view.hideProgress();
                view.startUserMainActivity();
            }
        });
    }
}