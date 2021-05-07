package com.example.vetsertification.ui.application;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.BidiRun;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vetsertification.R;
import com.example.vetsertification.ui.account.*;
import com.example.vetsertification.ui.registration.*;
import com.example.vetsertification.ui.userMainPage.*;

import java.util.Date;

public class ApplicationView extends AppCompatActivity {

    private EditText FromCountry;
    private EditText ToCountry;
    private EditText DateOfExport;

    private EditText Name;
    private EditText Birthday;
    private EditText Email;
    private EditText Phone;
    private EditText Address;

    private EditText TypeOfTransport;
    private EditText NumberOfTransport;

    private EditText KindOfAnimal;
    private EditText PetName;
    private EditText Breed;
    //radioButtonMale;
    //radioButtonFemale;
    private EditText PetBirthday;
    private EditText PetAddress;
    private EditText CountryOfOrigin;
    private EditText IdentificationSystem;
    private EditText DateOfChipping;
    private EditText Number;

    private EditText MethodOfResearch;
    private EditText Date;
    private EditText NameOfDesease;
    private EditText Result;

    private EditText NameOfVaccine;
    private EditText Date2;
    private EditText NameOfDesease2;
    private EditText SeriesOfVaccine;
    private EditText ShelfLifeVaccine;

    private TextView Message;
    private ProgressDialog progressDialog;

    private ApplicationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.application);
        init();
    }

    private void init() {

        FromCountry = (EditText) findViewById(R.id.fromCountry);
        ToCountry = (EditText) findViewById(R.id.toCountry);
        DateOfExport = (EditText) findViewById(R.id.dateOfExport);
        Name = (EditText) findViewById(R.id.name);
        Birthday = (EditText) findViewById(R.id.birthday);
        Email = (EditText) findViewById(R.id.email);
        Phone = (EditText) findViewById(R.id.phone);
        Address = (EditText) findViewById(R.id.address);
        TypeOfTransport = (EditText) findViewById(R.id.typeOfTransport);
        NumberOfTransport = (EditText) findViewById(R.id.numberOfTransport);
        KindOfAnimal = (EditText) findViewById(R.id.kindOfAnimal);
        PetName = (EditText) findViewById(R.id.petName);
        Breed = (EditText) findViewById(R.id.breed);
        PetBirthday = (EditText) findViewById(R.id.petBirthday);
        PetAddress = (EditText) findViewById(R.id.petAddress);
        CountryOfOrigin = (EditText) findViewById(R.id.countryOfOrigin);
        IdentificationSystem = (EditText) findViewById(R.id.identificationSystem);
        DateOfChipping = (EditText) findViewById(R.id.dateOfChipping);
        Number = (EditText) findViewById(R.id.number);
        MethodOfResearch = (EditText) findViewById(R.id.methodOfResearch);
        Date = (EditText) findViewById(R.id.date);
        NameOfDesease = (EditText) findViewById(R.id.nameOfDesease);
        Result = (EditText) findViewById(R.id.result);
        NameOfVaccine = (EditText) findViewById(R.id.nameOfVaccine);
        Date2 = (EditText) findViewById(R.id.date2);
        NameOfDesease2 = (EditText) findViewById(R.id.nameOfDesease2);
        SeriesOfVaccine = (EditText) findViewById(R.id.seriesOfVaccine);
        ShelfLifeVaccine = (EditText) findViewById(R.id.shelfLifeVaccine);

        findViewById(R.id.doApp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.doApp();
            }
        });

        ApplicationModel applicationModel = new ApplicationModel();
        presenter = new ApplicationPresenter(applicationModel);
        presenter.attachView(this);
        presenter.viewIsReady();
    }

    public ApplicationData getApplicationData() {
        ApplicationData applicationData = new ApplicationData();
        applicationData.setEmail(FromCountry.getText().toString());
        applicationData.setEmail(ToCountry.getText().toString());
        applicationData.setEmail(DateOfExport.getText().toString());
        applicationData.setEmail(Name.getText().toString());
        applicationData.setEmail(Birthday.getText().toString());
        applicationData.setEmail(Email.getText().toString());
        applicationData.setEmail(Phone.getText().toString());
        applicationData.setEmail(Address.getText().toString());
        applicationData.setEmail(TypeOfTransport.getText().toString());
        applicationData.setEmail(NumberOfTransport.getText().toString());
        applicationData.setEmail(KindOfAnimal.getText().toString());
        applicationData.setEmail(PetName.getText().toString());
        applicationData.setEmail(Breed.getText().toString());
        applicationData.setEmail(PetBirthday.getText().toString());
        applicationData.setEmail(PetAddress.getText().toString());
        applicationData.setEmail(CountryOfOrigin.getText().toString());
        applicationData.setEmail(IdentificationSystem.getText().toString());
        applicationData.setEmail(DateOfChipping.getText().toString());
        applicationData.setEmail(Number.getText().toString());
        applicationData.setEmail(MethodOfResearch.getText().toString());
        applicationData.setEmail(Date.getText().toString());
        applicationData.setEmail(NameOfDesease.getText().toString());
        applicationData.setEmail(Result.getText().toString());
        applicationData.setEmail(NameOfVaccine.getText().toString());
        applicationData.setEmail(Date2.getText().toString());
        applicationData.setEmail(NameOfDesease2.getText().toString());
        applicationData.setEmail(SeriesOfVaccine.getText().toString());
        applicationData.setEmail(ShelfLifeVaccine.getText().toString());
        return applicationData;
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
