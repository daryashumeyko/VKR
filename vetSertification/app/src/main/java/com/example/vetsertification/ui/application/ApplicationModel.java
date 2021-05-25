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
