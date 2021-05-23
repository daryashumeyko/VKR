package com.example.vetsertification.ui.account;

import android.os.AsyncTask;

import com.example.vetsertification.api.InfoManager;
import com.example.vetsertification.ui.CurrentUser;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class AccountModel {
    public void signIn(AccountData accountData, SignInCallback callback) {
        SignInTask signInTask = new SignInTask(callback);
        signInTask.execute(accountData);
    }

    interface SignInCallback {
        void onSignIn(Boolean result);
    }

    static class SignInTask extends AsyncTask<AccountData, Void, Boolean> {

        private final SignInCallback callback;

        SignInTask(SignInCallback callback) {
            this.callback = callback;
        }

        @Override
        protected Boolean doInBackground(AccountData... params) {
            try {
                InfoManager.signIn(params[0].getEmail(), params[0].getPassword());
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (callback != null) {
                callback.onSignIn(success);
            }
        }
    }
}
