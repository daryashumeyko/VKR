package com.example.vetsertification.ui.editaccount;

import android.os.AsyncTask;

import com.example.vetsertification.ui.seeaccount.SeeAccountData;
import com.example.vetsertification.ui.seeaccount.SeeAccountModel;

public class EditAccountModel {
    public void editAccount(SeeAccountData editAccountData, EditAccountModel.EditAccountCallback callback) {
        EditAccountModel.EditAccountTask editAccountTask = new EditAccountModel.EditAccountTask(callback);
        editAccountTask.execute(editAccountData);
    }

    interface EditAccountCallback {
        void onEditAccount(Boolean result);
    }

    class EditAccountTask extends AsyncTask<SeeAccountData, Void, Boolean> {

        private final EditAccountModel.EditAccountCallback callback;

        EditAccountTask(EditAccountModel.EditAccountCallback callback) {
            this.callback = callback;
        }

        @Override
        protected Boolean doInBackground(SeeAccountData... params) {
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (callback != null) {
                callback.onEditAccount(success);
            }
        }
    }
}