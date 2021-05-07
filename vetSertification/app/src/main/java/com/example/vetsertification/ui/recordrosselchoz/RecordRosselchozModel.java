package com.example.vetsertification.ui.recordrosselchoz;

import android.os.AsyncTask;

public class RecordRosselchozModel {
    public void recRosselchoz(RecordRosselchozData recordRosselchozData, RecordRosselchozModel.RosselchozCallback callback) {
        RecordRosselchozModel.RosselchozTask rosselchozTask = new RecordRosselchozModel.RosselchozTask(callback);
        rosselchozTask.execute(recordRosselchozData);
    }

    interface RosselchozCallback {
        void onRosselchoz(Boolean result);
    }

    class RosselchozTask extends AsyncTask<RecordRosselchozData, Void, Boolean> {

        private final RecordRosselchozModel.RosselchozCallback callback;

        RosselchozTask(RecordRosselchozModel.RosselchozCallback callback) {
            this.callback = callback;
        }

        @Override
        protected Boolean doInBackground(RecordRosselchozData... params) {
            //todo реализовать проверку наличия учетной записи
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (callback != null) {
                callback.onRosselchoz(success);
            }
        }
    }
}
