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
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

public class AddPetView extends AppCompatActivity {

    public TextInputEditText Breed;
    public TextInputEditText Name;
    public TextInputEditText Gender;
    public TextInputEditText Birthday;
    public TextInputEditText Address;
    public TextInputEditText IdentificationSystem;
    public TextInputEditText Number;
    public TextInputEditText DateOfChipping;
    public TextInputEditText KindOfAnimal;
    public TextInputEditText CountryOfOrigin;
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

        Name = (TextInputEditText) findViewById(R.id.name);
        KindOfAnimal = (TextInputEditText) findViewById(R.id.kindOfAnimal);
        Breed = (TextInputEditText) findViewById(R.id.breed);
        Address = (TextInputEditText) findViewById(R.id.address);
        Birthday = (TextInputEditText) findViewById(R.id.birthday);
        Birthday.addTextChangedListener(new RegexMaskTextWatcher(Birthday, "(0?[1-9]|[12][0-9]|3[01])([\\.\\\\\\/-])(0?[1-9]|1[012])\\2(((19|20)\\d\\d)|(\\d\\d))"));
        CountryOfOrigin = (TextInputEditText) findViewById(R.id.countryOfOrigin);
        IdentificationSystem = (TextInputEditText) findViewById(R.id.identificationSystem);
        Number = (TextInputEditText) findViewById(R.id.number);
        DateOfChipping = (TextInputEditText) findViewById(R.id.dateOfChipping);
        DateOfChipping.addTextChangedListener(new RegexMaskTextWatcher(DateOfChipping, "(0?[1-9]|[12][0-9]|3[01])([\\.\\\\\\/-])(0?[1-9]|1[012])\\2(((19|20)\\d\\d)|(\\d\\d))"));
        Gender = (TextInputEditText) findViewById(R.id.gender);

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
