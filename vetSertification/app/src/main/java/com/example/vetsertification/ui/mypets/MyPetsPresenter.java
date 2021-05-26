package com.example.vetsertification.ui.mypets;

import android.text.TextUtils;

import com.example.vetsertification.ui.CurrentPet;
import com.example.vetsertification.ui.CurrentUser;
import com.example.vetsertification.ui.account.AccountData;
import com.example.vetsertification.ui.account.AccountModel;
import com.example.vetsertification.ui.account.AccountView;

public class MyPetsPresenter {

    private MyPetsView view;
    private final MyPetsModel model;

    public MyPetsPresenter(MyPetsModel model) {
        this.model = model;
    }

    public void attachView(MyPetsView myPetsView) {
        view = myPetsView;
    }

    public void detachView() {
        view = null;
    }

    public void viewIsReady() {
    }

    public void details() {
        MyPetsData myPetsData = view.getMyPetsData();
        /*if (TextUtils.isEmpty(accountData.getEmail()) || TextUtils.isEmpty(accountData.getPassword())) {
            view.showMessage("Заполните все поля");
            return;
        }*/
        view.showProgress();
        model.details(myPetsData, new MyPetsModel.DetailsCallback(){
            @Override
            public void onDetails(Boolean result) {
                view.hideProgress();
                if (!result)
                    view.showMessage("Отсутствует соединение с сервером");
                else{
                    Boolean requestResult = CurrentPet.getInstance().getMyPetsData().getResult();
                    if (!requestResult)
                        view.showMessage("Неверный email или пароль");
                    else{
                        view.startPetDetails();
                    }
                }
            }
        });
    }

    public void newPet() {
        view.startNewPet();
    }

}