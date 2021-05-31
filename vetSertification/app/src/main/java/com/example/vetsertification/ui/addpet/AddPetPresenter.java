package com.example.vetsertification.ui.addpet;

import android.text.TextUtils;

import com.example.vetsertification.ui.CurrentPet;
import com.example.vetsertification.ui.CurrentUser;
import com.example.vetsertification.ui.mypets.MyPetsData;
import com.example.vetsertification.ui.mypets.MyPetsModel;
import com.example.vetsertification.ui.mypets.MyPetsView;

public class AddPetPresenter {
    private AddPetView view;
    private final AddPetModel model;

    public AddPetPresenter(AddPetModel model) {
        this.model = model;
    }

    public void attachView(AddPetView addPetView) {
        view = addPetView;
    }

    public void detachView() {
        view = null;
    }

    public void viewIsReady() {
    }

    public void addPet() {
        MyPetsData myPetsData = view.getMyPetsData();
        if (TextUtils.isEmpty(myPetsData.getName()) || TextUtils.isEmpty(myPetsData.getBirthday()) ||
                TextUtils.isEmpty(myPetsData.getBreed()) || TextUtils.isEmpty(myPetsData.getGender()) ||
                TextUtils.isEmpty(myPetsData.getCountryOfOrigin()) || TextUtils.isEmpty(myPetsData.getIdentificationSystem()) ||
                TextUtils.isEmpty(myPetsData.getKindOfAnimal()) || TextUtils.isEmpty(myPetsData.getAddress()) ||
                TextUtils.isEmpty(myPetsData.getDateOfChipping()) || TextUtils.isEmpty(myPetsData.getNumber())) {
            view.showMessage("Заполните все поля");
            return;
        }
        view.showProgress();
        model.addPet(myPetsData, new AddPetModel.AddPetCallback(){
            @Override
            public void onAddPet(Boolean result) {
                view.hideProgress();
                if (!result)
                    view.showMessage("Отсутствует соединение с сервером");
                else{
                    Boolean requestResult = CurrentUser.getInstance().getRegistrationData().getResult();
                    if (!requestResult)
                        view.showMessage("");
                    else{
                        view.startPetDetails();
                    }
                }
            }
        });
    }
}