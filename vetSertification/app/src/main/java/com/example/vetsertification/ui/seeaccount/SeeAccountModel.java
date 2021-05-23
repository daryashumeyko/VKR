package com.example.vetsertification.ui.seeaccount;

import android.os.AsyncTask;

import com.example.vetsertification.ui.registration.RegistrationData;
import com.example.vetsertification.ui.registration.RegistrationModel;

public class SeeAccountModel {
    public void seeAccount(SeeAccountData seeAccountData, SeeAccountModel.SeeAccountCallback callback) {
        SeeAccountModel.SeeAccountTask seeAccountTask = new SeeAccountModel.SeeAccountTask(callback);
        seeAccountTask.execute(seeAccountData);
    }

    interface SeeAccountCallback {
        void onSeeAccount(Boolean result);
    }

    class SeeAccountTask extends AsyncTask<SeeAccountData, Void, Boolean> {

        private final SeeAccountModel.SeeAccountCallback callback;

        SeeAccountTask(SeeAccountModel.SeeAccountCallback callback) {
            this.callback = callback;
        }

        @Override
        protected Boolean doInBackground(SeeAccountData... params) {
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