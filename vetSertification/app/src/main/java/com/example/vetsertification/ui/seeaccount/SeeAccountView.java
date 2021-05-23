package com.example.vetsertification.ui.seeaccount;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vetsertification.R;
import com.example.vetsertification.ui.application.ApplicationView;
import com.example.vetsertification.ui.editaccount.EditAccountModel;
import com.example.vetsertification.ui.editaccount.EditAccountView;
import com.example.vetsertification.ui.getinstruction.InstructionView;
import com.example.vetsertification.ui.recordrosselchoz.RecordRosselchozView;
import com.example.vetsertification.ui.recordvetoff.RecordVetOffView;
import com.example.vetsertification.ui.userMainPage.UserMainPageModel;
import com.example.vetsertification.ui.userMainPage.UserMainPagePresenter;

public class SeeAccountView extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private SeeAccountPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.see_account);
        init();
    }

    private void init() {
        findViewById(R.id.editAccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.editAccount();
            }
        });
        findViewById(R.id.seePets).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.seePets();
            }
        });

        SeeAccountModel seeAccountModel = new SeeAccountModel();
        presenter = new SeeAccountPresenter(seeAccountModel);
        presenter.attachView(this);
        presenter.viewIsReady();
    }

    public void startEditAccount(){
        Intent intent = new Intent(this, EditAccountView.class);
        startActivity(intent);
    }

    public void startSeePets(){
        Intent intent = new Intent(this, ApplicationView.class);
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
