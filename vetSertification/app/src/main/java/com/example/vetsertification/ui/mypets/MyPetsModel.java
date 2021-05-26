package com.example.vetsertification.ui.mypets;

import android.os.AsyncTask;

import com.example.vetsertification.api.InfoManager;
import com.example.vetsertification.ui.account.AccountData;
import com.example.vetsertification.ui.account.AccountModel;

import java.io.IOException;

public class MyPetsModel {
    public void details(MyPetsData myPetsData, MyPetsModel.DetailsCallback callback) {
        MyPetsModel.DetailsTask detailsTask = new MyPetsModel.DetailsTask(callback);
        detailsTask.execute(myPetsData);
    }

    interface DetailsCallback {
        void onDetails(Boolean result);
    }

    static class DetailsTask extends AsyncTask<MyPetsData, Void, Boolean> {

        private final MyPetsModel.DetailsCallback callback;

        DetailsTask(MyPetsModel.DetailsCallback callback) {
            this.callback = callback;
        }

        @Override
        protected Boolean doInBackground(MyPetsData... params) {
            try {
                InfoManager.details();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (callback != null) {
                callback.onDetails(success);
            }
        }
    }
}
