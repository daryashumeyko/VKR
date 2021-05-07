package com.example.vetsertification.ui.application;

import android.os.AsyncTask;

public class ApplicationModel {

    public void doApp(ApplicationData applicationData, ApplicationModel.DoAppCallback callback) {
        ApplicationModel.DoAppTask doAppTask = new ApplicationModel.DoAppTask(callback);
        doAppTask.execute(applicationData);
    }

    interface DoAppCallback {
        void onDoApp(Boolean result);
    }

    class DoAppTask extends AsyncTask<ApplicationData, Void, Boolean> {

        private final ApplicationModel.DoAppCallback callback;

        DoAppTask(ApplicationModel.DoAppCallback callback) {
            this.callback = callback;
        }

        @Override
        protected Boolean doInBackground(ApplicationData... params) {
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
                callback.onDoApp(success);
            }
        }
    }
}
