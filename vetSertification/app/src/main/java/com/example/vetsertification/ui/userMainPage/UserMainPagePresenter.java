package com.example.vetsertification.ui.userMainPage;

import android.text.TextUtils;

import com.example.vetsertification.ui.instruction.InstructionData;
import com.example.vetsertification.ui.registration.RegistrationData;
import com.example.vetsertification.ui.userMainPage.*;

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
        //loadUsers();
    }

    /*public void seeAccount() {
        RegistrationData accountData = view.getAccountData();
        view.showProgress();
        model.seeAccount(accountData, new UserMainPageModel.UserMainPageCallback(){
            @Override
            public void onSeeAccount(Boolean result) {
                view.hideProgress();
                view.startSeeAccountActivity();
            };
        });
    }*/

    public void instruction() {
        InstructionData instructionData = view.getInstructionData();
        view.showProgress();
        model.instruction(instructionData, new UserMainPageModel.UserMainPageCallback(){
            @Override
            public void onSeeInstruction(Boolean result) {
                view.hideProgress();
                view.startSeeInstruction();
            };
        });
    }
}
