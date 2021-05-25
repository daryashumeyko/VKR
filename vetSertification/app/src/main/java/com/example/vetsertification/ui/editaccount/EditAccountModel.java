package com.example.vetsertification.ui.editaccount;

import android.os.AsyncTask;
import com.example.vetsertification.api.InfoManager;
import com.example.vetsertification.ui.seeaccount.SeeAccountData;
import java.io.IOException;

public class EditAccountModel {
    public void saveAccount(SeeAccountData seeAccountData, EditAccountModel.EditAccountCallback callback) {
        EditAccountModel.EditAccountTask editAccountTask = new EditAccountModel.EditAccountTask(callback);
        editAccountTask.execute(seeAccountData);
    }

    interface EditAccountCallback {
        void onSaveAccount(Boolean result);
    }

    class EditAccountTask extends AsyncTask<SeeAccountData, Void, Boolean> {

        private final EditAccountModel.EditAccountCallback callback;

        EditAccountTask(EditAccountModel.EditAccountCallback callback) {
            this.callback = callback;
        }

        @Override
        protected Boolean doInBackground(SeeAccountData... params) {

            try {
                InfoManager.editAccount(params[0].getName(), params[0].getPhone(), params[0].getAddress(), params[0].getBirthday());
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (callback != null) {
                callback.onSaveAccount(success);
            }
        }
    }
}