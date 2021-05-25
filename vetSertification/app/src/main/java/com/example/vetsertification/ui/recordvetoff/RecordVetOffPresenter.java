package com.example.vetsertification.ui.recordvetoff;

import android.text.TextUtils;

public class RecordVetOffPresenter {
    private RecordVetOffView view;
    private final RecordVetOffModel model;

    public RecordVetOffPresenter(RecordVetOffModel model) {
        this.model = model;
    }

    public void attachView(RecordVetOffView recordVetOffView) {
        view = recordVetOffView;
    }

    public void detachView() {
        view = null;
    }

    public void viewIsReady() {
    }

    public void recVetOff() {
        RecordVetOffData recordVetOffData = view.getRecordVetOffData();
        if (TextUtils.isEmpty(recordVetOffData.getVetOff()) ||
                TextUtils.isEmpty((CharSequence) recordVetOffData.getDate()) || TextUtils.isEmpty((CharSequence) recordVetOffData.getTime())) {
            view.showMessage("Заполните все поля");
            return;
        }
        view.showProgress();
        model.recVetOff(recordVetOffData, new RecordVetOffModel.VetOffCallback(){
            @Override
            public void onVetOff(Boolean result) {
                view.hideProgress();
                view.startUserMainActivity();
            }
        });
    }
}
