package com.example.vetsertification.ui.addpet;

import android.os.AsyncTask;

import com.example.vetsertification.ui.mypets.MyPetsData;
import com.example.vetsertification.ui.mypets.MyPetsModel;

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
            /*try {
                InfoManager.details(id);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }*/
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