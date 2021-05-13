package com.example.vetsertification.ui.getinstruction;

import android.text.TextUtils;

import com.example.vetsertification.ui.recordrosselchoz.RecordRosselchozData;
import com.example.vetsertification.ui.recordrosselchoz.RecordRosselchozModel;
import com.example.vetsertification.ui.recordrosselchoz.RecordRosselchozView;

public class InstructionPresenter {

    private InstructionView view;
    private final InstructionModel model;

    public InstructionPresenter(InstructionModel model) {
        this.model = model;
    }

    public void attachView(InstructionView instructionView) {
        view = instructionView;
    }

    public void detachView() {
        view = null;
    }

    public void viewIsReady() {
        //loadUsers();
    }

    public void instruction() {
        InstructionData instructionData = view.getInstructionData();
        /*if (TextUtils.isEmpty(instructionData.getCountry()) ||
                TextUtils.isEmpty(instructionData.getDate()) || TextUtils.isEmpty(instructionData.getTime())) {
            view.showMessage("Заполните все поля");
            return;
        } */
        view.showProgress();
        model.instruction(instructionData, new InstructionModel.InstructionCallback(){
            @Override
            public void onInstruction(Boolean result) {
                view.hideProgress();
                view.startUserMainActivity();
            }
        });
    }
}