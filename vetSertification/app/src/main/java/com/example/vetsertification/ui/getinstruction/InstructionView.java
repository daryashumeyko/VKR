package com.example.vetsertification.ui.getinstruction;

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
import com.example.vetsertification.ui.userMainPage.UserMainPageView;
import java.util.ArrayList;
import java.util.List;

public class InstructionView extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText Country;
    private TextView Message;

    private ProgressDialog progressDialog;

    private InstructionPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_instruction);
        init();

        //Инициализируем элемент Spinner:
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        //Настраиваем слушатель нажатий Spinner(Click Listener):
        spinner.setOnItemSelectedListener(this);

        //Создаем массив элементов выпадающего списка:
        List<String> elements = new ArrayList<String>();
        elements.add("Австралия"); elements.add("Болгария"); elements.add("Бразилия"); elements.add("Вьетнам"); elements.add("Германия"); elements.add("Индонезия");
        elements.add("Китай"); elements.add("Ливан"); elements.add("Литва"); elements.add("Мексика"); elements.add("Норвегия"); elements.add("ОАЭ");
        elements.add("Панама"); elements.add("Парагвай"); elements.add("Польша"); elements.add("Португалия"); elements.add("Российская Федерация");
        elements.add("Румыния"); elements.add("Саудовская Аравия"); elements.add("Сербия"); elements.add("Сербия");
        elements.add("США"); elements.add("Турция"); elements.add("Узбекистан"); elements.add("Финляндия"); elements.add("Франция");
        elements.add("Хорватия"); elements.add("Чехия"); elements.add("Эстония"); elements.add("ЮАР"); elements.add("Япония");

        //Создаем для spinner адаптер:
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, elements);

        //Настраиваем внешний вид выпадающего списка, используя готовый системный шаблон:
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Присоединяем адаптер данных к spinner:
        spinner.setAdapter(dataAdapter);
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

        //Country = (EditText) findViewById(R.id.country);
        findViewById(R.id.getInstruction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.instruction();
            }
        });

        InstructionModel instructionModel = new InstructionModel();
        presenter = new InstructionPresenter(instructionModel);
        presenter.attachView(this);
        presenter.viewIsReady();
    }

    public InstructionData getInstructionData() {
        InstructionData instructionData = new InstructionData();
        //instructionData.setCountry(Country.getText().toString());
        //recordVetOffData.setDate(Date.getText().toString());
        //recordVetOffData.setTime(Time.getText().toString());
        return instructionData;
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