package com.example.vetsertification.ui.forgetpassword;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.vetsertification.R;
import com.example.vetsertification.ui.userMainPage.UserMainPageView;

public class ForgetPasswordView extends AppCompatActivity{

    private EditText Email;
    private TextView Message;

    private ProgressDialog progressDialog;

    private ForgetPasswordPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);
        init();
    }

    private void init() {
        Email = (EditText) findViewById(R.id.email);
        findViewById(R.id.getPassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.forgetpassword();
            }
        });

        ForgetPasswordModel forgetPasswordModel = new ForgetPasswordModel();
        presenter = new ForgetPasswordPresenter(forgetPasswordModel);
        presenter.attachView(this);
        presenter.viewIsReady();
    }

    public ForgetPasswordData getForgetPasswordData() {
        ForgetPasswordData forgetPasswordData = new ForgetPasswordData();
        forgetPasswordData.setEmail(Email.getText().toString());
        //recordVetOffData.setDate(Date.getText().toString());
        //recordVetOffData.setTime(Time.getText().toString());
        return forgetPasswordData;
    }

    public void showMessage(String text) {
        Message = (TextView) findViewById(R.id.message);
        Message.setText(text);
    }

    public void startUserMainActivity(){
        Intent intent = new Intent(this, UserMainPageView.class);
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