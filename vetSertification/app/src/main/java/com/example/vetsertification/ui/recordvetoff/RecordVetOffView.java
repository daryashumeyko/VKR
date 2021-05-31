package com.example.vetsertification.ui.recordvetoff;

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
import com.example.vetsertification.ui.registration.RegistrationView;
import com.example.vetsertification.ui.userMainPage.UserMainPagePresenter;
import com.example.vetsertification.ui.userMainPage.UserMainPageView;

public class RecordVetOffView extends AppCompatActivity {

    private EditText VetOff;
    private EditText Date;
    private EditText Time;
    private TextView Message;

    private ProgressDialog progressDialog;

    private RecordVetOffPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rec_vet_off);
        init();
    }

    private void init() {

        VetOff = (EditText) findViewById(R.id.vetOff);
        Date = (EditText) findViewById(R.id.date);
        Time = (EditText) findViewById(R.id.time);

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
        recordVetOffData.setVetOff(VetOff.getText().toString());
        //recordVetOffData.setDate(Date.getText().toString());
        //recordVetOffData.setTime(Time.getText().toString());
        return recordVetOffData;
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