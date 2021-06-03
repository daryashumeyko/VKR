package com.example.vetsertification.ui.mypets;

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
import com.example.vetsertification.api.InfoManager;
import com.example.vetsertification.ui.CurrentPet;
import com.example.vetsertification.ui.CurrentUser;
import com.example.vetsertification.ui.account.AccountData;
import com.example.vetsertification.ui.account.AccountModel;
import com.example.vetsertification.ui.account.AccountPresenter;
import com.example.vetsertification.ui.addpet.AddPetView;
import com.example.vetsertification.ui.forgetpassword.ForgetPasswordView;
import com.example.vetsertification.ui.mainpage.MainPageView;
import com.example.vetsertification.ui.registration.RegistrationData;
import com.example.vetsertification.ui.registration.RegistrationView;
import com.example.vetsertification.ui.seeaccount.SeeAccountView;
import com.example.vetsertification.ui.userMainPage.UserMainPagePresenter;
import com.example.vetsertification.ui.userMainPage.UserMainPageView;

import java.io.IOException;
import java.util.List;

public class MyPetsView extends AppCompatActivity {

    private TextView KindOfAnimal;
    private TextView Birthday;
    private TextView Name;
    private TextView Message;
    private ProgressDialog progressDialog;

    private MyPetsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypets);
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() throws IOException {

        KindOfAnimal = (TextView) findViewById(R.id.kindOfAnimal);
        Birthday = (TextView) findViewById(R.id.birthday);
        Name = (TextView) findViewById(R.id.name);

        findViewById(R.id.details).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.details();
            }
        });

        findViewById(R.id.newPet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.newPet();
            }
        });

        List<MyPetsData> myPetsData = CurrentPet.getInstance().getMyPetsData();
        KindOfAnimal.setText(myPetsData.get(0).getKindOfAnimal());
        Birthday.setText(myPetsData.get(0).getBirthday());
        Name.setText(myPetsData.get(0).getName());

        MyPetsModel myPetsModel = new MyPetsModel();
        presenter = new MyPetsPresenter(myPetsModel);
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
            case R.id.see_account:
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

    public MyPetsData getMyPetsData() {
        MyPetsData myPetsData = new MyPetsData();
        //myPetsData.setId(Id.getText().toString());
        return myPetsData;
    }

    public void showMessage(String text) {
        Message = (TextView) findViewById(R.id.message);
        Message.setText(text);
    }

    public void startPetDetails(){
        Intent intent = new Intent(this, UserMainPageView.class);
        startActivity(intent);
    }

    public void startNewPet(){
        Intent intent = new Intent(this, AddPetView.class);
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
        Intent intent = new Intent(getApplicationContext(), SeeAccountView.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
