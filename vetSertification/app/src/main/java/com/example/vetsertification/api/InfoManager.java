package com.example.vetsertification.api;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import com.example.vetsertification.ui.CurrentPet;
import com.example.vetsertification.ui.CurrentUser;
import com.example.vetsertification.ui.mypets.MyPetsData;
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
                .url(httpAddress + "/auth?email=" + email + "&password=" + password )
                .addHeader("Content-Type","text/json;Charset=UTF-8")
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
            Response response = client.newCall(request).execute();

            if(response.code() == 200) {
                MyPetsData petsData = new MyPetsData();
                petsData.setUserId(id);
                petsData.setName(response.headers().values("name").get(0));
                petsData.setBreed(response.headers().values("breed").get(0));
                petsData.setKindOfAnimal(response.headers().values("kind").get(0));
                petsData.setIdentificationSystem(response.headers().values("identification").get(0));
                petsData.setAddress(response.headers().values("address").get(0));
                petsData.setBirthday(response.headers().values("birthday").get(0));
                petsData.setCountryOfOrigin(response.headers().values("country").get(0));
                petsData.setDateOfChipping(response.headers().values("date").get(0));
                petsData.setNumber(response.headers().values("number").get(0));
                petsData.setGender(response.headers().values("gender").get(0));
                petsData.setResult(true);
                CurrentPet.getInstance().addPet(0, petsData);
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
                .addHeader("Content-Type","text/json;Charset=UTF-8")
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
        try {
            response = client.newCall(request).execute();

            if(response.code() == 200) {
                Integer PetsId = CurrentPet.getInstance().size();
                MyPetsData petsData = new MyPetsData();
                petsData.setUserId(userId);
                petsData.setName(name);
                petsData.setBreed(breed);
                petsData.setKindOfAnimal(kindOfAnimal);
                petsData.setIdentificationSystem(identificationSystem);
                petsData.setAddress(address);
                petsData.setBirthday(birthday);
                petsData.setCountryOfOrigin(countryOfOrigin);
                petsData.setDateOfChipping(dateOfChipping);
                petsData.setNumber(number);
                petsData.setGender(gender);
                CurrentPet.getInstance().addPet(PetsId, petsData);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return;
        }
    }

    //POST добавление заявки
    public static void addapplication(String fromCountry, String toCountry, Date dateOfExport, String name, Date birthday,
    String email, String phone, String address, String typeOfTransport, String numberOfTransport, String kindOfAnimal,
    String petName, String breed, Date petBirthday, String petAddress, String countryOfOrigin, String identificationSystem,
    Date dateOfChipping, String number, String methodOfResearch, Date date, String nameOfDesease, String result,
    String nameOfVaccine, Date date2, String nameOfDesease2, String seriesOfVaccine, Date shelfLifeVaccine)throws IOException{
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder().build();
        Request request = new Request.Builder()
                .url(httpAddress + "/addapplication?fromcountry=" + fromCountry + "&tocountry=" + toCountry + "&dateofexport=" +
                        dateOfExport + "&name=" + name + "&birthday=" + birthday + "&email=" + email +
                        "&phone=" + phone + "&address=" + address + "&typeoftransport=" + typeOfTransport +
                        "&numberoftransport=" + numberOfTransport + "&kindofanimal=" + kindOfAnimal + "&petname=" + petName +
                        "&breed=" + breed +"&petbirthday=" + petBirthday + "&petaddress=" + petAddress + "&countryoforigin=" + countryOfOrigin +
                        "&identificationsystem=" + identificationSystem + "&dateofchipping=" + dateOfChipping + "&number=" + number +
                        "&methodofresearch=" + methodOfResearch + "&result=" + result + "&date=" + date + "&nameofdesease=" + nameOfDesease +
                        "&nameofvaccine=" + nameOfVaccine + "&date2=" + date2 + "&nameofdesease2=" + nameOfDesease2
                        + "&seriesofvaccine=" + seriesOfVaccine + "&shelflifevaccine=" + shelfLifeVaccine )
                .post(formBody)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            if(response.code() == 200) {
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return;
        }
    }

    //POST добавление лабораторного исследования
    public static void addlabresearch(String methodOfResearch, Date date, String nameOfDesease, String result)throws IOException{
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder().build();
        Request request = new Request.Builder()
                .url(httpAddress + "/addlabresearch?methodofresearch=" + methodOfResearch +
                        "&result=" + result + "&date=" + date + "&nameofdesease=" + nameOfDesease )
                .post(formBody)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            if(response.code() == 200) {
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return;
        }
    }

    //POST добавление вакцины
    public static void addvaccination (String nameOfVaccine, Date date2, String nameOfDesease2, String seriesOfVaccine, Date shelfLifeVaccine){
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder().build();
        Request request = new Request.Builder()
                .url(httpAddress + "/addvaccination?date2=" + date2 + "&nameofdesease2=" + nameOfDesease2
                + "&seriesofvaccine=" + seriesOfVaccine + "&shelflifevaccine=" + shelfLifeVaccine)
                .post(formBody)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            if(response.code() == 200) {
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return;
        }
    }

    //POST добавление записи в управление Россельхознадзора
    public static void addrectorosselchoz(String rosselchoz, String city, Date date, Time time)throws IOException{
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder().build();
        Request request = new Request.Builder()
                .url(httpAddress + "/addrectorosselchoz?rosselchoz=" + rosselchoz + "&city=" + city
                        + "&date=" + date + "&time=" + time)
                .post(formBody)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            if(response.code() == 200) {
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return;
        }
    }

    //POST добавление записи в ветеринарное управление
    public static void addrectovetoff (String vetoff, String city, Date date, Time time)throws IOException{
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder().build();
        Request request = new Request.Builder()
                .url(httpAddress + "/addrectovetoff?vetoff=" + vetoff + "&city=" + city
                        + "&date=" + date + "&time=" + time)
                .post(formBody)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            if(response.code() == 200) {
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return;
        }
    }

    //PUT редактирование животного
    public static void editpet (Integer userId, String name, String birthday, String breed, String address, String kindOfAnimal,
    String identificationSystem, String countryOfOrigin, String dateOfChipping, String number,
    String gender)throws IOException{
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder().build();
        Request request = new Request.Builder()
                .url(httpAddress + "/addpet?userid=" + userId + "&name=" + name + "&breed=" + breed + "&kindofanimal=" + kindOfAnimal
                        + "&identificationsystem=" + identificationSystem + "&address=" + address + "&birthday=" + birthday
                        + "&countryoforigin=" + countryOfOrigin + "&dateofchipping=" + dateOfChipping + "&number=" + number+ "&gender=" + gender)
                .put(formBody)
                .build();
        Response response = null;
        try {

        } catch (IllegalStateException e) {
            e.printStackTrace();
            return;
        }
    }

}