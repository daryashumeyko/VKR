package com.example.vetsertification.ui.editaccount;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vetsertification.R;
import com.example.vetsertification.ui.application.ApplicationView;
import com.example.vetsertification.ui.getinstruction.InstructionView;
import com.example.vetsertification.ui.seeaccount.SeeAccountModel;
import com.example.vetsertification.ui.seeaccount.SeeAccountPresenter;
import com.example.vetsertification.ui.seeaccount.SeeAccountView;

public class EditAccountView extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private EditAccountPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_account);
        init();
    }

    private void init() {
        findViewById(R.id.saveAccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.editAccount();
            }
        });

        EditAccountModel editAccountModel = new EditAccountModel();
        presenter = new EditAccountPresenter(editAccountModel);
        presenter.attachView(this);
        presenter.viewIsReady();
    }

    public void startSeeAccount(){
        Intent intent = new Intent(this, SeeAccountView.class);
        startActivity(intent);
    }

    public void showProgress() {
        progressDialog = ProgressDialog.show(this, "", "Подождите...");
    }

    public void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}

