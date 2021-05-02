package com.example.vetsertification.ui.registration;

import android.os.AsyncTask;

import com.example.vetsertification.ui.registration.*;

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
