package com.example.vetsertification.ui.registration;

import android.os.AsyncTask;

import com.example.vetsertification.api.InfoManager;

import java.io.IOException;

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
            try {
                InfoManager.registration(params[0].getName(), params[0].getEmail(), params[0].getAddress(),
                        params[0].getBirthday(), params[0].getPassword(), params[0].getPhone());
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
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
