package com.example.vetsertification.ui.seeaccount;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.vetsertification.R;
import com.example.vetsertification.ui.CurrentUser;
import com.example.vetsertification.ui.application.ApplicationView;
import com.example.vetsertification.ui.editaccount.EditAccountView;
import com.example.vetsertification.ui.mypets.MyPetsView;
import com.example.vetsertification.ui.registration.RegistrationData;

public class SeeAccountView extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private SeeAccountPresenter presenter;
    private TextView Email;
    private TextView Phone;
    private TextView Birthday;
    private TextView Address;
    private TextView Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.see_account);
        init();
    }

    private void init() {
        Email = (TextView) findViewById(R.id.email);
        Phone = (TextView) findViewById(R.id.phone);
        Birthday = (TextView) findViewById(R.id.birthday);
        Address = (TextView) findViewById(R.id.address);
        Name = (TextView) findViewById(R.id.name);

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

        RegistrationData registrationData = CurrentUser.getInstance().getRegistrationData();
        Email.setText(registrationData.getEmail());
        Phone.setText(registrationData.getPhone());
        Birthday.setText(registrationData.getBirthday());
        Address.setText(registrationData.getAddress());
        Name.setText(registrationData.getName());

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
        Intent intent = new Intent(this, MyPetsView.class);
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
