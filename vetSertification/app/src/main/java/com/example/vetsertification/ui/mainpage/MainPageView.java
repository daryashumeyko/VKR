package com.example.vetsertification.ui.mainpage;

import android.accounts.Account;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.example.vetsertification.R;
import com.example.vetsertification.ui.account.AccountView;
import com.example.vetsertification.ui.getinstruction.InstructionView;
import com.example.vetsertification.ui.seeaccount.SeeAccountView;

public class MainPageView extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private MainPagePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        init();
    }

    private void init() {

        findViewById(R.id.authorization).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.authorization();
            }
        });
        findViewById(R.id.getInstruction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.instruction();
            }
        });

        MainPageModel mainPageModel = new MainPageModel();
        presenter = new MainPagePresenter(mainPageModel);
        presenter.attachView(this);
        presenter.viewIsReady();
    }

    public void startAuthorization(){
        Intent intent = new Intent(this, AccountView.class);
        startActivity(intent);
    }
    //переход на страницу просмотра инструкции
    public void startSeeInstruction(){
        Intent intent = new Intent(this, InstructionView.class);
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
