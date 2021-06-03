package com.example.vetsertification.ui.registration;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.vetsertification.R;
import com.example.vetsertification.ui.RegexMaskTextWatcher;
import com.example.vetsertification.ui.account.AccountView;
import com.example.vetsertification.ui.mainpage.MainPageView;
import com.example.vetsertification.ui.userMainPage.UserMainPagePresenter;
import com.example.vetsertification.ui.userMainPage.UserMainPageView;
import com.google.android.material.textfield.TextInputEditText;

public class RegistrationView extends AppCompatActivity {

    private TextInputEditText Email;
    private TextInputEditText Name;
    private TextInputEditText Address;
    private TextInputEditText Phone;
    private TextInputEditText Birthday;
    private TextInputEditText Password;
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

        Email = (TextInputEditText) findViewById(R.id.email);
        Email.addTextChangedListener(new RegexMaskTextWatcher(Email, "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+"));
        Password = (TextInputEditText) findViewById(R.id.password);
        Name = (TextInputEditText) findViewById(R.id.name);
        Address = (TextInputEditText) findViewById(R.id.address);
        Birthday = (TextInputEditText) findViewById(R.id.birthday);
        Birthday.addTextChangedListener(new RegexMaskTextWatcher(Birthday, "(0?[1-9]|[12][0-9]|3[01])([\\.\\\\\\/-])(0?[1-9]|1[012])\\2(((19|20)\\d\\d)|(\\d\\d))"));
        Phone = (TextInputEditText) findViewById(R.id.phone);
        Phone.addTextChangedListener(new RegexMaskTextWatcher(Phone, "((8|\\+7))?(\\(?\\d{3}\\)?)?[\\d\\- ]{8,15}$"));

        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.register();
            }
        });

        RegistrationModel registrationModel = new RegistrationModel();
        presenter = new RegistrationPresenter(registrationModel);
        presenter.attachView(this);
        presenter.viewIsReady();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.account:
                MainPageView.getPresenter().authorization();
                return true;
            case R.id.instruction:
                MainPageView.getPresenter().instruction();
                return true;
        }
        return true;
    }

    public RegistrationData getRegistrationData() {
        RegistrationData registrationData = new RegistrationData();
        registrationData.setEmail(Email.getText().toString());
        registrationData.setPassword(Password.getText().toString());
        registrationData.setName(Name.getText().toString());
        registrationData.setAddress(Address.getText().toString());
        registrationData.setBirthday(Birthday.getText().toString());
        registrationData.setPhone(Phone.getText().toString());
        return registrationData;
    }

    public RegistrationData getTextColor() {
        RegistrationData registrationData = new RegistrationData();
        registrationData.setEmailColor(Email.getCurrentTextColor());
        registrationData.setBirthdayColor(Birthday.getCurrentTextColor());
        registrationData.setPhoneColor(Phone.getCurrentTextColor());
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
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainPageView.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
