package com.example.vetsertification.ui.registration;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vetsertification.R;
import com.example.vetsertification.ui.account.AccountView;
import com.example.vetsertification.ui.registration.*;

public class RegistrationView extends AppCompatActivity {

    private EditText Email;
    private EditText Name;
    private EditText Address;
    private EditText Phone;
    private EditText Birthday;
    private EditText Password;
    private TextView Message;
    private ProgressDialog progressDialog;

    private RegistrationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        init();
    }

    private void init() {

        Email = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.password);
        Name = (EditText) findViewById(R.id.name);
        Address = (EditText) findViewById(R.id.address);
        Birthday = (EditText) findViewById(R.id.birthday);
        Phone = (EditText) findViewById(R.id.phone);

        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.register();
            }
        });

        /*LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        userAdapter = new UserAdapter();

        RecyclerView userList = (RecyclerView) findViewById(R.id.list);
        userList.setLayoutManager(layoutManager);
        userList.setAdapter(userAdapter);


        DbHelper dbHelper = new DbHelper(this);*/
        RegistrationModel registrationModel = new RegistrationModel();
        presenter = new RegistrationPresenter(registrationModel);
        presenter.attachView(this);
        presenter.viewIsReady();
    }

    public RegistrationData getRegistrationData() {
        RegistrationData registrationData = new RegistrationData();
        registrationData.setEmail(Email.getText().toString());
        registrationData.setPassword(Password.getText().toString());
        registrationData.setName(Name.getText().toString());
        registrationData.setAddress(Address.getText().toString());
        //registrationData.setBirthday(Birthday.().toString());
        //registrationData.setPhone(Phone.().toString());
        //todo изменить тип на др и телефона
        return registrationData;
    }

    public void showMessage(String text) {
        Message = (TextView) findViewById(R.id.message);
        Message.setText(text);
    }

    //переход на страницу авторизации после прохождения регистрации
    public void startAccountActivity(){
        Intent intent = new Intent(this, AccountView.class);
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
