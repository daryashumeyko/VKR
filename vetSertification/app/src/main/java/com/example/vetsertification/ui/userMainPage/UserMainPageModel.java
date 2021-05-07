package com.example.vetsertification.ui.userMainPage;

import android.os.AsyncTask;

import com.example.vetsertification.ui.registration.RegistrationData;

public class UserMainPageModel {

    public void seeAccount(RegistrationData accountData, UserMainPageModel.UserMainPageCallback callback) {
        UserMainPageModel.UserMainPageTask userMainPageTask = new UserMainPageModel.UserMainPageTask(callback);
        UserMainPageTask.execute((Runnable) accountData);
    }

    interface UserMainPageCallback {
        void onSeeAccount(Boolean result);
    }

    class UserMainPageTask extends AsyncTask<RegistrationData, Void, Boolean> {

        private final UserMainPageModel.UserMainPageCallback callback;

        UserMainPageTask(UserMainPageModel.UserMainPageCallback callback) {
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
