package com.example.vetsertification.ui.getinstruction;

import android.os.AsyncTask;

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
