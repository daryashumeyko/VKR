package com.example.vetsertification.api;

import java.io.IOException;
import com.example.vetsertification.ui.CurrentUser;
import com.example.vetsertification.ui.registration.RegistrationData;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class InfoManager {
    private static String httpAddress = "http://192.168.43.111:8089";

    public static void signIn(String email, String password) throws IOException {
        //через клиент шлём запрос
        OkHttpClient client = new OkHttpClient();
        //в formBody кладём параметры запроса
        RequestBody formBody = new FormBody.Builder().build();
        //создаём запрос, указывая адрес и параметры
        Request request = new Request.Builder()
                .url(httpAddress + "/auth?email=" + email + "&password=" + password)
                .post(formBody)
                .build();
        //исполняем запрос синхронно, в том потоке, в котором вызываем `execute`
        try {
            RegistrationData registrationData = CurrentUser.getInstance().getRegistrationData();
            registrationData.setResult(false);
            registrationData.setEmail(email);

            Response response = client.newCall(request).execute();

            if(response.code() == 200) {
                registrationData.setResult(true);
                registrationData.setId(Integer.parseInt(response.headers().values("id").get(0)));
                registrationData.setName(response.headers().values("name").get(0));
                registrationData.setAddress(response.headers().values("address").get(0));
                registrationData.setBirthday(response.headers().values("birthday").get(0));
                registrationData.setPhone(response.headers().values("phone").get(0));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public static void editAccount(String phone, String name, String address, String birthday) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder().build();
        Request request = new Request.Builder()
                .url(httpAddress + "/editaccount?name=" + name + "&address=" + address + "&phone=" + phone
                        + "&birthday=" + birthday )
                .put(formBody)
                .build();
        try {
            RegistrationData registrationData = CurrentUser.getInstance().getRegistrationData();
            registrationData.setResult(false);

            Response response = client.newCall(request).execute();

            if(response.code() == 200) {
                registrationData.setResult(true); registrationData.setName(name);
                registrationData.setAddress(address); registrationData.setBirthday(birthday); registrationData.setPhone(phone);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
}