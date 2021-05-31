package com.example.vetsertification.ui;

import com.example.vetsertification.ui.mypets.MyPetsData;

import java.util.ArrayList;
import java.util.List;

public class CurrentPet {

    private static final com.example.vetsertification.ui.CurrentPet INSTANCE = new com.example.vetsertification.ui.CurrentPet();
    private  static List<MyPetsData> myPetsData = new ArrayList<>();

    private CurrentPet(){

    }

    public static com.example.vetsertification.ui.CurrentPet getInstance(){
        return INSTANCE;
    }

    public List<MyPetsData> getMyPetsData() {
        return myPetsData;
    }

    public void addPet(Integer petId, MyPetsData petsData) { myPetsData.add(petId, petsData); }

    public Integer size() { return myPetsData.size(); }

    public void setMyPetsData(List<MyPetsData> myPetsData) {
        this.myPetsData = myPetsData;
    }

    //private MyPetsData myPetsData;
}