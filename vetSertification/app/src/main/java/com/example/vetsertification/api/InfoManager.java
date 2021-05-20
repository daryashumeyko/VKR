package com.example.vetsertification.api;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class InfoManager {
    public static void signIn(String email, String password) throws IOException {
        //через клиент шлём запрос
        OkHttpClient client = new OkHttpClient();
        //в formBody кладём параметры запроса
        RequestBody formBody = new FormBody.Builder()
                .add("email", email)
                .add("password", password)
                .build();
        //создаём запрос, указывая адрес и параметры
        Request request = new Request.Builder()
                .url("http://192.168.57.162:8089/auth")
                .post(formBody)
                .build();

        /*//исполняем запрос асинхронно
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String serverAnswer = response.body().string();
            }
        });*/
        //исполняем запрос синхронно, в том потоке, в коем вызываем `execute`
        try {
            Response response = client.newCall(request).execute();

            String serverAnswer = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
}