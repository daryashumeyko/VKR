package com.example.vetsertification.ui.editaccount;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.vetsertification.R;
import com.example.vetsertification.ui.CurrentUser;
import com.example.vetsertification.ui.registration.RegistrationData;
import com.example.vetsertification.ui.seeaccount.SeeAccountData;
import com.example.vetsertification.ui.seeaccount.SeeAccountView;
import com.example.vetsertification.ui.userMainPage.UserMainPagePresenter;
import com.example.vetsertification.ui.userMainPage.UserMainPageView;
import com.google.android.material.textfield.TextInputEditText;

public class EditAccountView extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private EditAccountPresenter presenter;
    private TextInputEditText Phone;
    private TextInputEditText Birthday;
    private TextInputEditText Address;
    private TextInputEditText Name;
    private TextView Message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_account);
        init();
    }

    private void init() {
        Phone = (TextInputEditText) findViewById(R.id.phone);
        Birthday = (TextInputEditText) findViewById(R.id.birthday);
        Address = (TextInputEditText) findViewById(R.id.address);
        Name = (TextInputEditText) findViewById(R.id.name);

        findViewById(R.id.saveAccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.saveAccount();
            }
        });

        RegistrationData registrationData = CurrentUser.getInstance().getRegistrationData();
        Phone.setText(registrationData.getPhone());
        Birthday.setText(registrationData.getBirthday());
        Address.setText(registrationData.getAddress());
        Name.setText(registrationData.getName());

        EditAccountModel editAccountModel = new EditAccountModel();
        presenter = new EditAccountPresenter(editAccountModel);
        presenter.attachView(this);
        presenter.viewIsReady();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.registered_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        UserMainPagePresenter presenter = UserMainPageView.getPresenter();
        int id = item.getItemId();
        switch(id){
            case R.id.account:
                presenter.seeAccount();
                return true;
            case R.id.get_instruction:
                presenter.instruction();
                return true;
            case R.id.application:
                presenter.application();
                return true;
            case R.id.recordVetOff:
                presenter.recVetOff();
                return true;
            case R.id.recordRosselchoz:
                presenter.recRosselchoz();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public SeeAccountData getSeeAccountData() {
        SeeAccountData seeAccountData = new SeeAccountData();
        seeAccountData.setPhone(Phone.getText().toString());
        seeAccountData.setBirthday(Birthday.getText().toString());
        seeAccountData.setAddress(Address.getText().toString());
        seeAccountData.setName(Name.getText().toString());
        return seeAccountData;
    }

    public void showMessage(String text) {
        Message = (TextView) findViewById(R.id.message);
        Message.setText(text);
    }

    public void startSeeAccountActivity(){
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

