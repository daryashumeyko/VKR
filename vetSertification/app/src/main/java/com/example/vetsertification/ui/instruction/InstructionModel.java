package com.example.vetsertification.ui.instruction;

import android.os.AsyncTask;

import com.example.vetsertification.ui.instruction.*;

public class InstructionModel {

    public void instruction(InstructionData instructionData, InstructionModel.InstructionCallback callback) {
        InstructionModel.InstructionTask instructionTask = new InstructionModel.InstructionTask(callback);
        instructionTask.execute(instructionData);
    }

    interface InstructionCallback {
        void onInstruction(Boolean result);
    }

    class InstructionTask extends AsyncTask<InstructionData, Void, Boolean> {

        private final InstructionModel.InstructionCallback callback;

        InstructionTask(InstructionModel.InstructionCallback callback) {
            this.callback = callback;
        }

        @Override
        protected Boolean doInBackground(InstructionData... params) {
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
                callback.onInstruction(success);
            }
        }
    }
}
