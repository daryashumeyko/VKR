package com.example.vetsertification.ui.registration;

import android.os.AsyncTask;

public class RegistrationModel {

    public void register(RegistrationData registrationData, RegistrationModel.RegisterCallback callback) {
        RegisterTask registerTask = new RegisterTask(callback);
        registerTask.execute(registrationData);
    }

    interface RegisterCallback {
        void onRegister(Boolean result);
    }

    class RegisterTask extends AsyncTask<RegistrationData, Void, Boolean> {

        private final RegistrationModel.RegisterCallback callback;

        RegisterTask(RegistrationModel.RegisterCallback callback) {
            this.callback = callback;
        }

        @Override
        protected Boolean doInBackground(RegistrationData... params) {
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (callback != null) {
                callback.onRegister(success);
            }
        }
    }
}
