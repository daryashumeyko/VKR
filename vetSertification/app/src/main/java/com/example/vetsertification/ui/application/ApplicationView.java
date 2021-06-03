package com.example.vetsertification.ui.application;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.vetsertification.R;
import com.example.vetsertification.ui.userMainPage.*;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class ApplicationView extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner FromCountry;
    private Spinner ToCountry;
    private TextInputEditText DateOfExport;

    private TextInputEditText Name;
    private TextInputEditText Birthday;
    private TextInputEditText Email;
    private TextInputEditText Phone;
    private TextInputEditText Address;

    private Spinner TypeOfTransport;
    private TextInputEditText NumberOfTransport;

    private TextInputEditText KindOfAnimal;
    private TextInputEditText PetName;
    private TextInputEditText Breed;
    //radioButtonMale;
    //radioButtonFemale;
    private TextInputEditText PetBirthday;
    private TextInputEditText PetAddress;
    private Spinner CountryOfOrigin;
    private Spinner IdentificationSystem;
    private TextInputEditText DateOfChipping;
    private TextInputEditText Number;

    private TextInputEditText MethodOfResearch;
    private TextInputEditText Date;
    private TextInputEditText NameOfDesease;
    private TextInputEditText Result;

    private TextInputEditText NameOfVaccine;
    private TextInputEditText Date2;
    private TextInputEditText NameOfDesease2;
    private TextInputEditText SeriesOfVaccine;
    private TextInputEditText ShelfLifeVaccine;

    private TextView Message;
    private ProgressDialog progressDialog;

    private ApplicationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.application);
        init();

        //Инициализируем элемент Spinner:
        Spinner spinnerFromCountry = (Spinner) findViewById(R.id.spinnerFromCountry);
        Spinner spinnerToCountry = (Spinner) findViewById(R.id.spinnerToCountry);
        Spinner spinnerTypeOfTransport = (Spinner) findViewById(R.id.spinnerTypeOfTransport);
        Spinner spinnerCountryOfOrigin = (Spinner) findViewById(R.id.spinnerCountryOfOrigin);
        Spinner spinnerIdentificationSystem = (Spinner) findViewById(R.id.spinnerIdentificationSystem);

        //Настраиваем слушатель нажатий Spinner(Click Listener):
        spinnerFromCountry.setOnItemSelectedListener(this);
        spinnerToCountry.setOnItemSelectedListener(this);
        spinnerTypeOfTransport.setOnItemSelectedListener(this);
        spinnerCountryOfOrigin.setOnItemSelectedListener(this);
        spinnerIdentificationSystem.setOnItemSelectedListener(this);

        //Создаем массив элементов выпадающего списка:
        List<String> country = new ArrayList<String>();
        country.add("Австралия"); country.add("Болгария"); country.add("Бразилия"); country.add("Вьетнам"); country.add("Германия"); country.add("Индонезия");
        country.add("Китай"); country.add("Ливан"); country.add("Литва"); country.add("Мексика"); country.add("Норвегия"); country.add("ОАЭ");
        country.add("Панама"); country.add("Парагвай"); country.add("Польша"); country.add("Португалия"); country.add("Российская Федерация");
        country.add("Румыния"); country.add("Саудовская Аравия"); country.add("Сербия"); country.add("Сербия");
        country.add("США"); country.add("Турция"); country.add("Узбекистан"); country.add("Финляндия"); country.add("Франция");
        country.add("Хорватия"); country.add("Чехия"); country.add("Эстония"); country.add("ЮАР"); country.add("Япония");

        List<String> transport = new ArrayList<String>();
        transport.add("Морской"); transport.add("Авиатранспортный"); transport.add("Речной"); transport.add("Железнодорожный"); transport.add("Автомобильный");

        List<String> identification = new ArrayList<String>();
        identification.add("Чипирование"); identification.add("Клеймение");

        //Создаем для spinner адаптер:
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, country);
        //Настраиваем внешний вид выпадающего списка, используя готовый системный шаблон:
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Присоединяем адаптер данных к spinner:
        spinnerFromCountry.setAdapter(dataAdapter);
        spinnerToCountry.setAdapter(dataAdapter);
        spinnerCountryOfOrigin.setAdapter(dataAdapter);

        //Создаем для spinnerTypeOfTransport адаптер:
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, transport);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTypeOfTransport.setAdapter(dataAdapter2);

        //Создаем для spinnerIdentificationSystem адаптер:
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, identification);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdentificationSystem.setAdapter(dataAdapter3);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Выбираем элемент выпадающего списка:
        String item = parent.getItemAtPosition(position).toString();
        //Показываем выбранный элемент с помощью Toast сообщения:
        Toast.makeText(parent.getContext(), "Выбрано: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    private void init() {

        FromCountry = (Spinner) findViewById(R.id.spinnerFromCountry);
        ToCountry = (Spinner) findViewById(R.id.spinnerToCountry);
        DateOfExport = (TextInputEditText) findViewById(R.id.dateOfExport);
        Name = (TextInputEditText) findViewById(R.id.name);
        Birthday = (TextInputEditText) findViewById(R.id.birthday);
        Email = (TextInputEditText) findViewById(R.id.email);
        Phone = (TextInputEditText) findViewById(R.id.phone);
        Address = (TextInputEditText) findViewById(R.id.address);
        TypeOfTransport = (Spinner) findViewById(R.id.spinnerTypeOfTransport);
        NumberOfTransport = (TextInputEditText) findViewById(R.id.numberOfTransport);
        KindOfAnimal = (TextInputEditText) findViewById(R.id.kindOfAnimal);
        PetName = (TextInputEditText) findViewById(R.id.petName);
        Breed = (TextInputEditText) findViewById(R.id.breed);
        PetBirthday = (TextInputEditText) findViewById(R.id.petBirthday);
        PetAddress = (TextInputEditText) findViewById(R.id.petAddress);
        CountryOfOrigin = (Spinner) findViewById(R.id.spinnerCountryOfOrigin);
        IdentificationSystem = (Spinner) findViewById(R.id.identificationSystem);
        DateOfChipping = (TextInputEditText) findViewById(R.id.dateOfChipping);
        Number = (TextInputEditText) findViewById(R.id.number);
        MethodOfResearch = (TextInputEditText) findViewById(R.id.methodOfResearch);
        Date = (TextInputEditText) findViewById(R.id.date);
        NameOfDesease = (TextInputEditText) findViewById(R.id.nameOfDesease);
        Result = (TextInputEditText) findViewById(R.id.result);
        NameOfVaccine = (TextInputEditText) findViewById(R.id.nameOfVaccine);
        Date2 = (TextInputEditText) findViewById(R.id.date2);
        NameOfDesease2 = (TextInputEditText) findViewById(R.id.nameOfDesease2);
        SeriesOfVaccine = (TextInputEditText) findViewById(R.id.seriesOfVaccine);
        ShelfLifeVaccine = (TextInputEditText) findViewById(R.id.shelfLifeVaccine);

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
        //applicationData.setEmail(FromCountry.getText().toString());
        //applicationData.setEmail(ToCountry.getText().toString());
        applicationData.setEmail(DateOfExport.getText().toString());
        applicationData.setEmail(Name.getText().toString());
        applicationData.setEmail(Birthday.getText().toString());
        applicationData.setEmail(Email.getText().toString());
        applicationData.setEmail(Phone.getText().toString());
        applicationData.setEmail(Address.getText().toString());
        //applicationData.setEmail(TypeOfTransport.getText().toString());
        applicationData.setEmail(NumberOfTransport.getText().toString());
        applicationData.setEmail(KindOfAnimal.getText().toString());
        applicationData.setEmail(PetName.getText().toString());
        applicationData.setEmail(Breed.getText().toString());
        applicationData.setEmail(PetBirthday.getText().toString());
        applicationData.setEmail(PetAddress.getText().toString());
        //applicationData.setEmail(CountryOfOrigin.getText().toString());
        //applicationData.setEmail(IdentificationSystem.getText().toString());
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
