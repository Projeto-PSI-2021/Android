package com.example.tophotels.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.tophotels.modelos.Hotel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HotelJsonParser {

    public static ArrayList<Hotel> parserJsonHoteis(JSONArray resposta) {
        ArrayList<Hotel> listaHotel = new ArrayList<>();

        try {
            for (int i = 0; i < resposta.length(); i++) {
                // receber objeto da respota (array)
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

    public static Hotel parserJsonHotel(String resposta) {
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

    public static String parserJsonLogin(String resposta) {
        String token = null;

        try {
            JSONObject login = new JSONObject(resposta);
            if (login.getBoolean("success")) {
                JSONObject user = login.getJSONObject("user");
                token = user.getString("access_token");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return token;
    }

    public static boolean isConnectionInternet(Context contexto) {
        ConnectivityManager cm = (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }
}