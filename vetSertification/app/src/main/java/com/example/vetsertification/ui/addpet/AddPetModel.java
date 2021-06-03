package com.example.vetsertification.ui.addpet;

import android.os.AsyncTask;

import com.example.vetsertification.api.InfoManager;
import com.example.vetsertification.ui.mypets.MyPetsData;

import java.io.IOException;

public class AddPetModel {
    public void addPet(MyPetsData myPetsData, AddPetModel.AddPetCallback callback) {
        AddPetModel.AddPetTask addPetTask = new AddPetModel.AddPetTask(callback);
        addPetTask.execute(myPetsData);
    }

    interface AddPetCallback {
        void onAddPet(Boolean result);
    }

    static class AddPetTask extends AsyncTask<MyPetsData, Void, Boolean> {

        private final AddPetModel.AddPetCallback callback;

        AddPetTask(AddPetModel.AddPetCallback callback) {
            this.callback = callback;
        }

        @Override
        protected Boolean doInBackground(MyPetsData... params) {
            try {
                InfoManager.addpet(params[0].getUserId(), params[0].getName(), params[0].getBirthday(), params[0].getBreed(),
                        params[0].getAddress(), params[0].getKindOfAnimal(), params[0].getIdentificationSystem(),
                        params[0].getCountryOfOrigin(), params[0].getDateOfChipping(), params[0].getNumber(),
                        params[0].getGender());
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (callback != null) {
                callback.onAddPet(success);
            }
        }
    }
}