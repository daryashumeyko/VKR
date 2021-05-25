package com.example.vetsertification.ui.account;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.vetsertification.R;
import com.example.vetsertification.ui.forgetpassword.ForgetPasswordView;
import com.example.vetsertification.ui.registration.RegistrationView;
import com.example.vetsertification.ui.userMainPage.UserMainPageView;

public class AccountView extends AppCompatActivity{

    private EditText Email;
    private EditText Password;
    private TextView Message;
    private ProgressDialog progressDialog;

    private AccountPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myaccount);
        init();
    }

    private void init() {

        Email = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.password);

        findViewById(R.id.signIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.signIn();
            }
        });

        findViewById(R.id.btnFgtPsw).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.fgtPsw();
            }
        });

        findViewById(R.id.signUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.signUp();
            }
        });

        AccountModel accountModel = new AccountModel();
        presenter = new AccountPresenter(accountModel);
        presenter.attachView(this);
        presenter.viewIsReady();
    }

    public AccountData getAccountData() {
        AccountData accountData = new AccountData();
        accountData.setEmail(Email.getText().toString());
        accountData.setPassword(Password.getText().toString());
        return accountData;
    }

    public void showMessage(String text) {
        Message = (TextView) findViewById(R.id.message);
        Message.setText(text);
    }

    public void startUserMainActivity(){
        Intent intent = new Intent(this, UserMainPageView.class);
        startActivity(intent);
    }

    public void startRegistrationActivity(){
        Intent intent = new Intent(this, RegistrationView.class);
        startActivity(intent);
    }

    public void startForgetPasswordActivity(){
        Intent intent = new Intent(this, ForgetPasswordView.class);
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
