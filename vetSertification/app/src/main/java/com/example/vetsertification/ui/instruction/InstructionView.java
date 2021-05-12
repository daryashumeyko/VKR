package com.example.vetsertification.ui.instruction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.vetsertification.R;

public class InstructionView extends AppCompatActivity {
    // private UserAdapter userAdapter;

    private EditText Country;
    private TextView Message;
    private ProgressDialog progressDialog;

    private String[] countries = { "Бразилия", "Аргентина", "Колумбия", "Чили", "Уругвай"};

    private InstructionPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_instruction);
        init();

        final TextView selection = (TextView) findViewById(R.id.selection);

        //выпадающий список со странами
        Spinner spinner = (Spinner) findViewById(R.id.spinnerCountry);
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countries);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinner.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Получаем выбранный объект
                String item = (String)parent.getItemAtPosition(position);
                selection.setText(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);
    }

    private void init() {

        Country = (EditText) findViewById(R.id.country);

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
        instructionData.setCountry(Country.getText().toString());
        return instructionData;
    }

    public void showMessage(String text) {
        Message = (TextView) findViewById(R.id.message);
        Message.setText(text);
    }

    public void startInstructionActivity(){
        Intent intent = new Intent(this, InstructionView.class);
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