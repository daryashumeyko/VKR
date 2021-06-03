package com.example.vetsertification.ui.mainpage;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vetsertification.R;
import com.example.vetsertification.ui.account.AccountView;
import com.example.vetsertification.ui.getinstruction.InstructionView;

public class MainPageView extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private static MainPagePresenter presenter;

    public static MainPagePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.account:
                presenter.authorization();
                return true;
            case R.id.instruction:
                presenter.instruction();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {

        findViewById(R.id.authorization).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.authorization();
            }
        });
        findViewById(R.id.getInstruction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.instruction();
            }
        });

        MainPageModel mainPageModel = new MainPageModel();
        presenter = new MainPagePresenter(mainPageModel);
        presenter.attachView(this);
        presenter.viewIsReady();
    }

    public void startAuthorization(){
        Intent intent = new Intent(this, AccountView.class);
        startActivity(intent);
    }
    //переход на страницу просмотра инструкции
    public void startSeeInstruction(){
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
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Выход")
                .setMessage("Вы уверены что хотите выйти?")
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("Нет", null).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
