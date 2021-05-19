package com.example.vetsertification.ui.forgetpassword;

import android.os.AsyncTask;

import com.example.vetsertification.ui.getinstruction.InstructionData;
import com.example.vetsertification.ui.getinstruction.InstructionModel;

public class ForgetPasswordModel {
    public void forgetpassword(ForgetPasswordData forgetPasswordData, ForgetPasswordModel.ForgetPasswordCallback callback) {
        ForgetPasswordModel.ForgetPasswordTask forgetPasswordTask = new ForgetPasswordModel.ForgetPasswordTask(callback);
        forgetPasswordTask.execute(forgetPasswordData);
    }

    interface ForgetPasswordCallback {
        void onForgetPassword(Boolean result);
    }

    class ForgetPasswordTask extends AsyncTask<ForgetPasswordData, Void, Boolean> {

        private final ForgetPasswordModel.ForgetPasswordCallback callback;

        ForgetPasswordTask(ForgetPasswordModel.ForgetPasswordCallback callback) {
            this.callback = callback;
        }

        @Override
        protected Boolean doInBackground(ForgetPasswordData... params) {
            //todo реализовать проверку наличия учетной записи
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (callback != null) {
                callback.onForgetPassword(success);
            }
        }
    }
}