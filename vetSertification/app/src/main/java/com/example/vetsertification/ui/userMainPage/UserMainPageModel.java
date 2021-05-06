package com.example.vetsertification.ui.userMainPage;

import android.os.AsyncTask;

import com.example.vetsertification.ui.instruction.InstructionData;
import com.example.vetsertification.ui.registration.RegistrationData;
import com.example.vetsertification.ui.registration.RegistrationModel;

public class UserMainPageModel {

    public void seeAccount(RegistrationData accountData, UserMainPageModel.UserMainPageCallback callback) {
        UserMainPageModel.UserMainPageTask userMainPageTask = new UserMainPageModel.UserMainPageTask(callback);
        UserMainPageTask.execute((Runnable) accountData);
    }

    public void instruction(InstructionData instructionData, UserMainPageCallback instructionCallback) {
    }

    interface UserMainPageCallback {
        void onSeeAccount(Boolean result);
        void onSeeInstruction(Boolean result);
    }

    class UserMainPageTask extends AsyncTask<RegistrationData, Void, Boolean> {

        private final UserMainPageModel.UserMainPageCallback callback;

        UserMainPageTask(UserMainPageModel.UserMainPageCallback callback) {
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
                callback.onSeeAccount(success);
                callback.onSeeInstruction(success);
            }
        }
    }
}
