package com.example.vetsertification.ui.addpet;

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
import com.example.vetsertification.ui.mypets.MyPetsData;
import com.example.vetsertification.ui.userMainPage.UserMainPagePresenter;
import com.example.vetsertification.ui.userMainPage.UserMainPageView;

import java.io.IOException;

public class AddPetView extends AppCompatActivity {

    public EditText Breed;
    public EditText Name;
    public EditText Gender;
    public EditText Birthday;
    public EditText Address;
    public EditText IdentificationSystem;
    public EditText Number;
    public EditText DateOfChipping;
    public EditText KindOfAnimal;
    public EditText CountryOfOrigin;
    private TextView Message;
    private ProgressDialog progressDialog;

    private AddPetPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_pet);
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() throws IOException {

        Name = (EditText) findViewById(R.id.name);
        KindOfAnimal = (EditText) findViewById(R.id.kindOfAnimal);
        Breed = (EditText) findViewById(R.id.breed);
        Address = (EditText) findViewById(R.id.address);
        Birthday = (EditText) findViewById(R.id.birthday);
        Birthday.addTextChangedListener(new RegexMaskTextWatcher(Birthday, "(0?[1-9]|[12][0-9]|3[01])([\\.\\\\\\/-])(0?[1-9]|1[012])\\2(((19|20)\\d\\d)|(\\d\\d))"));
        CountryOfOrigin = (EditText) findViewById(R.id.countryOfOrigin);
        IdentificationSystem = (EditText) findViewById(R.id.identificationSystem);
        Number = (EditText) findViewById(R.id.number);
        DateOfChipping = (EditText) findViewById(R.id.dateOfChipping);
        DateOfChipping.addTextChangedListener(new RegexMaskTextWatcher(DateOfChipping, "(0?[1-9]|[12][0-9]|3[01])([\\.\\\\\\/-])(0?[1-9]|1[012])\\2(((19|20)\\d\\d)|(\\d\\d))"));
        Gender = (EditText) findViewById(R.id.gender);

        findViewById(R.id.addpet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addPet();
            }
        });

        AddPetModel addPetModel = new AddPetModel();
        presenter = new AddPetPresenter(addPetModel);
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
        myPetsData.setBreed(Breed.getText().toString());
        myPetsData.setIdentificationSystem(IdentificationSystem.getText().toString());
        myPetsData.setName(Name.getText().toString());
        myPetsData.setAddress(Address.getText().toString());
        myPetsData.setBirthday(Birthday.getText().toString());
        myPetsData.setCountryOfOrigin(CountryOfOrigin.getText().toString());
        myPetsData.setDateOfChipping(DateOfChipping.getText().toString());
        myPetsData.setGender(Gender.getText().toString());
        myPetsData.setKindOfAnimal(KindOfAnimal.getText().toString());
        myPetsData.setNumber(Number.getText().toString());
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
