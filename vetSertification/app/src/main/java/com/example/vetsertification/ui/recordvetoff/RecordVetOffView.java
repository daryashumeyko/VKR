package com.example.vetsertification.ui.recordvetoff;

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
import com.example.vetsertification.ui.registration.RegistrationView;
import com.example.vetsertification.ui.userMainPage.UserMainPagePresenter;
import com.example.vetsertification.ui.userMainPage.UserMainPageView;

import java.util.ArrayList;
import java.util.List;

public class RecordVetOffView extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner City;
    private Spinner VetOff;
    private Spinner Date;
    private Spinner Time;
    private TextView Message;

    private ProgressDialog progressDialog;

    private RecordVetOffPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rec_vet_off);
        init();

        //Инициализируем элемент Spinner:
        Spinner spinnerCity = (Spinner) findViewById(R.id.spinnerCity);
        Spinner spinnerVetOff = (Spinner) findViewById(R.id.spinnerVetOff);
        Spinner spinnerDate = (Spinner) findViewById(R.id.spinnerDate);
        Spinner spinnerTime = (Spinner) findViewById(R.id.spinnerTime);

        //Настраиваем слушатель нажатий Spinner(Click Listener):
        spinnerCity.setOnItemSelectedListener(this);
        spinnerVetOff.setOnItemSelectedListener(this);
        spinnerDate.setOnItemSelectedListener(this);
        spinnerTime.setOnItemSelectedListener(this);

        //Создаем массив элементов выпадающего списка:
        List<String> city = new ArrayList<String>();
        city.add(""); city.add("Абинск"); city.add("Азов"); city.add("Алдан"); city.add("Александров");
        city.add("Амурск"); city.add("Балашиха"); city.add("Балтийск"); city.add("Барнаул"); city.add("Балтийск");
        city.add("Белгород"); city.add("Благовещинск"); city.add("Бор"); city.add("Великий новгород"); city.add("Владивосток");
        city.add("Владикавказ"); city.add("Владимир"); city.add("Вологда"); city.add("Воронеж");
        city.add("Вязники"); city.add("Городец"); city.add("Гурьевск"); city.add("Данилов"); city.add("Дубна");
        city.add("Ессентуки"); city.add("Железноводск"); city.add("Иваново"); city.add("Иркутск"); city.add("Калуга");

        List<String> vetOff = new ArrayList<String>();
        vetOff.add(""); vetOff.add("ул.Сакко и Ванцетти,60");

        List<String> date = new ArrayList<String>();
        date.add(""); date.add("06.05.2021"); date.add("07.05.2021"); date.add("08.05.2021");
        date.add("09.05.2021"); date.add("10.05.2021"); date.add("11.05.2021"); date.add("12.05.2021");
        date.add("13.05.2021"); date.add("14.05.2021"); date.add("15.05.2021"); date.add("16.05.2021");
        date.add("17.05.2021"); date.add("18.05.2021"); date.add("19.05.2021"); date.add("20.05.2021");
        date.add("21.05.2021"); date.add("22.05.2021"); date.add("23.05.2021"); date.add("24.05.2021");

        List<String> time = new ArrayList<String>();
        time.add(""); time.add("09:10"); time.add("11:30"); time.add("13:40");
        time.add("14:00"); time.add("15:20"); time.add("16:10"); time.add("16:20");
        time.add("16:50"); time.add("17:10"); time.add("18:50"); time.add("20:00");
        date.add("21:300"); date.add("23:00"); date.add("23:40"); date.add("00:00");
        date.add("01:10"); date.add("03:30"); date.add("05:40"); date.add("06:10");

        //Создаем для spinner адаптер:
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, city);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, vetOff);
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, date);
        ArrayAdapter<String> dataAdapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, time);
        //Настраиваем внешний вид выпадающего списка, используя готовый системный шаблон:
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Присоединяем адаптер данных к spinner:
        spinnerCity.setAdapter(dataAdapter);
        spinnerVetOff.setAdapter(dataAdapter2);
        spinnerDate.setAdapter(dataAdapter3);
        spinnerTime.setAdapter(dataAdapter4);
    }

    private void init() {

        City = (Spinner) findViewById(R.id.spinnerCity);
        VetOff = (Spinner) findViewById(R.id.spinnerVetOff);
        Date = (Spinner) findViewById(R.id.spinnerDate);
        Time = (Spinner) findViewById(R.id.spinnerTime);

        findViewById(R.id.doRecVetOff).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.recVetOff();
            }
        });

        RecordVetOffModel recordVetOffModel = new RecordVetOffModel();
        presenter = new RecordVetOffPresenter(recordVetOffModel);
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

    public RecordVetOffData getRecordVetOffData() {
        RecordVetOffData recordVetOffData = new RecordVetOffData();
        //recordVetOffData.setVetOff(VetOff.getText().toString());
        //recordVetOffData.setDate(Date.getText().toString());
        //recordVetOffData.setTime(Time.getText().toString());
        return recordVetOffData;
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

    public void showMessage(String text) {
        Message = (TextView) findViewById(R.id.message);
        Message.setText(text);
    }

    public void startUserMainActivity(){
        Intent intent = new Intent(this, UserMainPageView.class);
        startActivity(intent);
    }

    public void startRegistrationActivity(){
        Intent intent = new Intent(this, RegistrationView.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), UserMainPageView.class);
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