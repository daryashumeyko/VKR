package com.example.vetsertification.ui.account;

import android.os.AsyncTask;

import com.example.vetsertification.api.InfoManager;
import com.example.vetsertification.api.InfoManagerService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class AccountModel {
    /*public UsersModel(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }*/

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
            }
           /*List<User> users = new LinkedList<>();
            Cursor cursor = dbHelper.getReadableDatabase().query(UserTable.TABLE, null, null, null, null, null, null);
            while (cursor.moveToNext()) {
                User user = new User();
                user.setId(cursor.getLong(cursor.getColumnIndex(UserTable.COLUMN.ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(UserTable.COLUMN.NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(UserTable.COLUMN.EMAIL)));
                users.add(user);
            }
            cursor.close();*/
            //todo реализовать проверку наличия учетной записи
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
