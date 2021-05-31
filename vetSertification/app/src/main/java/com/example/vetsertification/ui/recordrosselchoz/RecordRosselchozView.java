package com.example.vetsertification.ui.recordrosselchoz;

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
import com.example.vetsertification.ui.userMainPage.UserMainPagePresenter;
import com.example.vetsertification.ui.userMainPage.UserMainPageView;

public class RecordRosselchozView extends AppCompatActivity {

    private EditText Rosselchoz;
    private EditText Date;
    private EditText Time;
    private TextView Message;

    private ProgressDialog progressDialog;

    private RecordRosselchozPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rec_rosselchoz);
        init();
    }

    private void init() {

        Rosselchoz = (EditText) findViewById(R.id.rosselchoz);
        Date = (EditText) findViewById(R.id.date);
        Time = (EditText) findViewById(R.id.time);

        findViewById(R.id.doRecRosselchoz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.recRosselchoz();
            }
        });

        RecordRosselchozModel recordRosselchozModel = new RecordRosselchozModel();
        presenter = new RecordRosselchozPresenter(recordRosselchozModel);
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

    public RecordRosselchozData getRecordRosselchozData() {
        RecordRosselchozData recordRosselchozData = new RecordRosselchozData();
        recordRosselchozData.setRosselchoz(Rosselchoz.getText().toString());
        //recordVetOffData.setDate(Date.getText().toString());
        //recordVetOffData.setTime(Time.getText().toString());
        return recordRosselchozData;
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