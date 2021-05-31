package com.example.vetsertification.api;

import java.io.IOException;

import com.example.vetsertification.ui.CurrentPet;
import com.example.vetsertification.ui.CurrentUser;
import com.example.vetsertification.ui.mypets.MyPetsData;
import com.example.vetsertification.ui.mypets.MyPetsView;
import com.example.vetsertification.ui.registration.RegistrationData;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class InfoManager {
    private static String httpAddress = "http://192.168.43.112:8089";

    //POST авторизация
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
        Response response = null;
        Integer id = 0;
        try {
            RegistrationData registrationData = CurrentUser.getInstance().getRegistrationData();
            registrationData.setResult(false);
            registrationData.setEmail(email);

            response = client.newCall(request).execute();

            if(response.code() == 200) {
                registrationData.setResult(true);
                id = Integer.parseInt(response.headers().values("id").get(0));
                registrationData.setId(id);
                registrationData.setName(response.headers().values("name").get(0));
                registrationData.setAddress(response.headers().values("address").get(0));
                registrationData.setBirthday(response.headers().values("birthday").get(0));
                registrationData.setPhone(response.headers().values("phone").get(0));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return;
        }
        if(response.code() == 200) {
            details(id);
        }
    }

    //PUT редактирование аккаунта
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

    //GET инф о животном
    public static void details(Integer id) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(httpAddress + "/petdetails?id=" + id)
                .get()
                .build();
        try {
            MyPetsData myPetsData = CurrentPet.getInstance().getMyPetsData();
            myPetsData.setResult(false);

            Response response = client.newCall(request).execute();

            if(response.code() == 200) {
                myPetsData.setBreed(response.headers().values("breed").get(0));
                myPetsData.setName(response.headers().values("name").get(0));
                myPetsData.setAddress(response.headers().values("address").get(0));
                myPetsData.setBirthday(response.headers().values("birthday").get(0));
                myPetsData.setGender(response.headers().values("gender").get(0));
                myPetsData.setNumber(response.headers().values("number").get(0));
                myPetsData.setDateOfChipping(response.headers().values("date").get(0));
                myPetsData.setCountryOfOrigin(response.headers().values("country").get(0));
                myPetsData.setKindOfAnimal(response.headers().values("kind").get(0));
                myPetsData.setIdentificationSystem(response.headers().values("identification").get(0));
               }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    //POST регистрация
    public static void registration(String name, String birthday, String phone, String address, String email, String password) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder().build();
        Request request = new Request.Builder()
                .url(httpAddress + "/registration?name=" + name + "&email=" + email + "&birthday=" + birthday  + "&phone=" + phone
                        + "&address=" + address + "&password" + password)
                .post(formBody)
                .build();
        Response response = null;
        try {
            RegistrationData registrationData = CurrentUser.getInstance().getRegistrationData();
            registrationData.setResult(false);

            response = client.newCall(request).execute();

            if(response.code() == 200) {
                registrationData.setResult(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return;
        }
    }

    //POST добавление животного
    public static void addpet(Integer userId, String name, String birthday, String breed, String address, String kindOfAnimal,
                              String identificationSystem, String countryOfOrigin, String dateOfChipping, String number,
                              String gender) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder().build();
        Request request = new Request.Builder()
                .url(httpAddress + "/addpet?userid=" + userId + "&name=" + name + "&breed=" + breed + "&kindofanimal=" + kindOfAnimal
                        + "&identificationsystem=" + identificationSystem + "&address=" + address + "&birthday=" + birthday
                        + "&countryoforigin=" + countryOfOrigin + "&dateofchipping=" + dateOfChipping + "&number=" + number+ "&gender=" + gender)
                .post(formBody)
                .build();
        Response response = null;
        userId = 0;
        try {
            RegistrationData registrationData = CurrentUser.getInstance().getRegistrationData();
            registrationData.setResult(false);

            MyPetsData myPetsData = CurrentPet.getInstance().getMyPetsData();
            myPetsData.setResult(false);

            response = client.newCall(request).execute();

            if(response.code() == 200) {
                registrationData.setResult(true);
                userId = Integer.parseInt(response.headers().values("id").get(0));
                registrationData.setId(userId);
                myPetsData.setResult(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return;
        }
    }
}