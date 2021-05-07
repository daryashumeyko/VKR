package com.example.vetsertification.ui.instruction;

import android.text.TextUtils;

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
        if (TextUtils.isEmpty(instructionData.getCountry())) {
            view.showMessage("Выберите страну");
            return;
        }
        view.showProgress();
        model.instruction(instructionData, new InstructionModel.InstructionCallback(){
            @Override
            public void onInstruction(Boolean result) {
                view.hideProgress();
                view.startInstructionActivity();
                };
        });
    }
}
