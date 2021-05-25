package com.example.vetsertification.ui.application;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

public class ApplicationView extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

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

        //FromCountry = (EditText) findViewById(R.id.fromCountry);
        //ToCountry = (EditText) findViewById(R.id.toCountry);
        DateOfExport = (EditText) findViewById(R.id.dateOfExport);
        Name = (EditText) findViewById(R.id.name);
        Birthday = (EditText) findViewById(R.id.birthday);
        Email = (EditText) findViewById(R.id.email);
        Phone = (EditText) findViewById(R.id.phone);
        Address = (EditText) findViewById(R.id.address);
        //TypeOfTransport = (EditText) findViewById(R.id.typeOfTransport);
        NumberOfTransport = (EditText) findViewById(R.id.numberOfTransport);
        KindOfAnimal = (EditText) findViewById(R.id.kindOfAnimal);
        PetName = (EditText) findViewById(R.id.petName);
        Breed = (EditText) findViewById(R.id.breed);
        PetBirthday = (EditText) findViewById(R.id.petBirthday);
        PetAddress = (EditText) findViewById(R.id.petAddress);
        //CountryOfOrigin = (EditText) findViewById(R.id.countryOfOrigin);
        //IdentificationSystem = (EditText) findViewById(R.id.identificationSystem);
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
