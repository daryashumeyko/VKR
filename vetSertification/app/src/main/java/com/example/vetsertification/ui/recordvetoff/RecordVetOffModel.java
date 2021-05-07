package com.example.vetsertification.ui.recordvetoff;

import android.os.AsyncTask;

public class RecordVetOffModel {

    public void recVetOff(RecordVetOffData recordVetOffData, RecordVetOffModel.VetOffCallback callback) {
        RecordVetOffModel.VetOffTask vetOffTask = new RecordVetOffModel.VetOffTask(callback);
        vetOffTask.execute(recordVetOffData);
    }

    interface VetOffCallback {
        void onVetOff(Boolean result);
    }

    class VetOffTask extends AsyncTask<RecordVetOffData, Void, Boolean> {

        private final RecordVetOffModel.VetOffCallback callback;

        VetOffTask(RecordVetOffModel.VetOffCallback callback) {
            this.callback = callback;
        }

        @Override
        protected Boolean doInBackground(RecordVetOffData... params) {
            //todo реализовать проверку наличия учетной записи
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (callback != null) {
                callback.onVetOff(success);
            }
        }
    }
}
