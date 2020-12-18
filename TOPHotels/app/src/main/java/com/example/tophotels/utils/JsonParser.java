package com.example.tophotels.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.tophotels.modelos.Hotel;
import com.example.tophotels.modelos.Quarto;
import com.example.tophotels.modelos.User;
import com.example.tophotels.modelos.UserInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonParser {

    public static ArrayList<Hotel> jsonParserListaHoteis(JSONArray resposta) {
        ArrayList<Hotel> listaHotel = new ArrayList<>();

        try {
            for (int i = 0; i < resposta.length(); i++) {
                // receber objeto da resposta (array)
                JSONObject hotelJson = (JSONObject) resposta.get(i);

                // receber os valores do objeto
                int id = hotelJson.getInt("id");
                String nome = hotelJson.getString("nome");
                String proprietario = hotelJson.getString("proprietario");
                String descricao = hotelJson.getString("descricao");
                int contacto = hotelJson.getInt("contacto");
                String website = hotelJson.getString("website");
                int cp4 = hotelJson.getInt("cp4");
                int cp3 = hotelJson.getInt("cp3");
                String localidade = hotelJson.getString("localidade");
                String morada = hotelJson.getString("morada");
                int estado = hotelJson.getInt("estado");
                //String img = hotelJson.getString("img");

                Hotel hotel = new Hotel(id, nome, proprietario, descricao, contacto, website, cp4, cp3, localidade, morada, estado);
                listaHotel.add(hotel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listaHotel;
    }

    public static ArrayList<Quarto> jsonParserListaQuartos(String resposta) {
        ArrayList<Quarto> listaQuarto = new ArrayList<>();

        try {
            JSONArray respostaArray = new JSONArray(resposta);
            for (int i = 0; i < respostaArray.length(); i++) {
                // receber objeto da resposta (array)
                JSONObject quartoJson = (JSONObject) respostaArray.get(i);

                // receber os valores do objeto
                int id = quartoJson.getInt("id");
                String descricao = quartoJson.getString("descricao");
                double precoNoite = quartoJson.getDouble("precoNoite");
                String img = quartoJson.getString("img");

                Quarto quarto = new Quarto(id, descricao, precoNoite, img);
                listaQuarto.add(quarto);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listaQuarto;
    }

    public static Hotel jsonParserHotel(String resposta) {
        Hotel hotel = null;

        try {
            // receber objeto da respota (array)
            JSONObject hotelJson = new JSONObject(resposta);

            // receber os valores do objeto
            int id = hotelJson.getInt("id");
            String nome = hotelJson.getString("nome");
            String proprietario = hotelJson.getString("proprietario");
            String descricao = hotelJson.getString("descricao");
            int contacto = hotelJson.getInt("contacto");
            String website = hotelJson.getString("website");
            int cp4 = hotelJson.getInt("cp4");
            int cp3 = hotelJson.getInt("cp3");
            String localidade = hotelJson.getString("localidade");
            String morada = hotelJson.getString("morada");
            int estado = hotelJson.getInt("estado");
            //String img = hotelJson.getString("img");

            hotel = new Hotel(id, nome, proprietario, descricao, contacto, website, cp4, cp3, localidade, morada, estado);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return hotel;
    }

    public static UserInfo jsonParserUserInfo(String resposta) {
        UserInfo userInfo = null;
        try {
            JSONObject response = new JSONObject(resposta);
            if (response.getBoolean("success")) {

                JSONObject userInfoJson = response.getJSONObject("userInfo");

                int id = userInfoJson.getInt("id");
                String nome = "";
                String apelido = "";
                int contactoTel = 0;
                String morada = "";
                int nif = 0;
                String img = userInfoJson.getString("img");
                if (!userInfoJson.isNull("nome")){
                    nome = userInfoJson.getString("nome");
                }
                if (!userInfoJson.isNull("apelido")){
                    apelido = userInfoJson.getString("apelido");
                }
                if (!userInfoJson.isNull("contactoTel")){
                    contactoTel = userInfoJson.getInt("contactoTel");
                }
                if (!userInfoJson.isNull("morada")){
                    morada = userInfoJson.getString("morada");
                }
                if (!userInfoJson.isNull("nif")){
                    nif = userInfoJson.getInt("nif");
                }


                int userId = userInfoJson.getInt("userId");

                userInfo = new UserInfo(id, nome, apelido, contactoTel, morada, nif, img, userId);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return userInfo;
    }

    public static UserInfo jsonParserUserInfoPatch(String resposta) {
        UserInfo userInfo = null;

        try {
            JSONObject userInfoJson = new JSONObject(resposta);

            int id = userInfoJson.getInt("id");
            String nome = userInfoJson.getString("nome");
            String apelido = userInfoJson.getString("apelido");
            int contactoTel = userInfoJson.getInt("contactoTel");
            String morada = userInfoJson.getString("morada");
            int nif = userInfoJson.getInt("nif");
            String img = userInfoJson.getString("img");
            int userId = userInfoJson.getInt("userId");

            userInfo = new UserInfo(id, nome, apelido, contactoTel, morada, nif, img, userId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return userInfo;
    }

    public static User jsonParserLogin(String resposta) {
        User user = null;

        try {
            JSONObject login = new JSONObject(resposta);
            if (login.getBoolean("success")) {
                JSONObject userJson = login.getJSONObject("user");

                int id = userJson.getInt("id");
                String username = userJson.getString("username");
                String email = userJson.getString("email");
                String access_token = userJson.getString("access_token");

                user = new User(id, username, email, access_token);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static Boolean jsonParserRegister(String resposta) {
        Boolean flag = null;

        try {
            JSONObject registo = new JSONObject(resposta);
            if (registo.getBoolean("success")) {
                flag = true;
            } else {
                flag = false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static boolean isConnectionInternet(Context contexto) {
        ConnectivityManager cm = (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }
}