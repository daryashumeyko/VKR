package com.example.vetsertification.ui.userMainPage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vetsertification.R;
import com.example.vetsertification.ui.account.*;
import com.example.vetsertification.ui.instruction.*;
import com.example.vetsertification.ui.registration.*;
import com.example.vetsertification.ui.userMainPage.*;

public class UserMainPageView extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private UserMainPagePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_user_page);
        init();
    }

    private void init() {

        /*findViewById(R.id.seeAccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.seeAccount();
            }
        });*/
        findViewById(R.id.getInstruction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.instruction();
            }
        });
        /*findViewById(R.id.application).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.application();
            }
        });
        findViewById(R.id.recordVetOff).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.recordVetOff();
            }
        });
        findViewById(R.id.recordRosselchoz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.recordRosselchoz();
            }
        });*/

        UserMainPageModel userMainPageModel = new UserMainPageModel();
        presenter = new UserMainPagePresenter(userMainPageModel);
        presenter.attachView(this);
        presenter.viewIsReady();
    }

    /*//переход на страницу просмотра аккаунта
    public void startSeeAccountActivity(){
        Intent intent = new Intent(this, SeeAccountView.class);
        startActivity(intent);
    }*/
    //переход на страницу просмотра инструкции
    public void startSeeInstruction(){
        Intent intent = new Intent(this, InstructionView.class);
        startActivity(intent);
    }
    /*//переход на страницу создания заявки на получение ВСД
    public void startApplicationActivity(){
        Intent intent = new Intent(this, ApplicationView.class);
        startActivity(intent);
    }
    //переход на страницу создания записи в вет.управление
    public void startRecVetOffActivity(){
        Intent intent = new Intent(this, RecVetOffView.class);
        startActivity(intent);
    }
    //переход на страницу создания записи в Россельхознадзор
    public void startRecRosselchozActivity(){
        Intent intent = new Intent(this, RecRosselchozView.class);
        startActivity(intent);
    }*/

    public RegistrationData getAccountData() {
        RegistrationData accountData = new RegistrationData();
        //todo реализовать получение данных пользователя для просмотра данных его аккаунта
        return accountData;
    }

    public InstructionData getInstructionData() {
        InstructionData instructionData = new InstructionData();
        //todo реализовать получение данных пользователя для просмотра данных его аккаунта
        return instructionData;
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
