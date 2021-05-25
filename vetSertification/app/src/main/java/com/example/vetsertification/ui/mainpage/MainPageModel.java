package com.example.vetsertification.ui.mainpage;

import android.os.AsyncTask;

import com.example.vetsertification.ui.registration.RegistrationData;
import com.example.vetsertification.ui.userMainPage.UserMainPageModel;

public class MainPageModel {
    public void seeAccount(RegistrationData accountData, MainPageModel.MainPageCallback callback) {
        MainPageModel.MainPageTask mainPageTask = new MainPageModel.MainPageTask(callback);
        MainPageModel.MainPageTask.execute((Runnable) accountData);
    }

    interface MainPageCallback {
        void onSeeAccount(Boolean result);
    }

    class MainPageTask extends AsyncTask<RegistrationData, Void, Boolean> {

        private final MainPageModel.MainPageCallback callback;

        MainPageTask(MainPageModel.MainPageCallback callback) {
            this.callback = callback;
        }

        @Override
        protected Boolean doInBackground(RegistrationData... params) {
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (callback != null) {
                callback.onSeeAccount(success);
            }
        }
    }
}
