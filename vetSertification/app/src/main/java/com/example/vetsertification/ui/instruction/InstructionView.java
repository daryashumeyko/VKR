package com.example.vetsertification.ui.instruction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vetsertification.R;
import com.example.vetsertification.ui.instruction.*;

public class InstructionView extends AppCompatActivity {
    // private UserAdapter userAdapter;

    private EditText Country;
    private TextView Message;
    private ProgressDialog progressDialog;

    private InstructionPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_instruction);
        init();
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