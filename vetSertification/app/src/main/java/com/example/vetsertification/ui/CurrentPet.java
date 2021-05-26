package com.example.vetsertification.ui;

import com.example.vetsertification.ui.mypets.MyPetsData;

public class CurrentPet {

    private static final com.example.vetsertification.ui.CurrentPet INSTANCE = new com.example.vetsertification.ui.CurrentPet();

    private CurrentPet(){
        myPetsData = new MyPetsData();
    }

    public static com.example.vetsertification.ui.CurrentPet getInstance(){
        return INSTANCE;
    }

    public MyPetsData getMyPetsData() {
        return myPetsData;
    }

    public void setMyPetsData(MyPetsData myPetsData) {
        this.myPetsData = myPetsData;
    }

    private MyPetsData myPetsData;
}