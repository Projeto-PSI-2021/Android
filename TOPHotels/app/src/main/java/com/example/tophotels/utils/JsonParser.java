package com.example.tophotels.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.tophotels.modelos.ComodidadesHotel;
import com.example.tophotels.modelos.ComodidadesQuarto;
import com.example.tophotels.modelos.Hotel;
import com.example.tophotels.modelos.Quarto;
import com.example.tophotels.modelos.Regiao;
import com.example.tophotels.modelos.Reserva;
import com.example.tophotels.modelos.Singleton;
import com.example.tophotels.modelos.User;
import com.example.tophotels.modelos.UserInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonParser {

    public static ArrayList<Hotel> jsonParserListaHoteis(String resposta) {
        ArrayList<Hotel> listaHotel = new ArrayList<>();

        try {
            JSONArray respostaArray = new JSONArray(resposta);
            for (int i = 0; i < respostaArray.length(); i++) {
                // receber objeto da resposta (array)
                JSONObject array = (JSONObject) respostaArray.get(i);
                JSONObject hotelJson = array.getJSONObject("hotel");
                // receber os valores do objeto
                int id = hotelJson.getInt("id");
                String nome = hotelJson.getString("nome");
                String descricao = hotelJson.getString("descricao");
                int contacto = hotelJson.getInt("contacto");
                String website = hotelJson.getString("website");
                int cp4 = hotelJson.getInt("cp4");
                int cp3 = hotelJson.getInt("cp3");
                String morada = hotelJson.getString("morada");
                int estado = hotelJson.getInt("estado");
                String img = Singleton.mUrl + "/assets/hoteis/" + hotelJson.getString("img");

                JSONObject preco = array.getJSONObject("preco");
                double preco_min = preco.getDouble("min");
                double preco_max = preco.getDouble("max");

                Hotel hotel = new Hotel(id, nome, descricao, contacto, website, cp4, cp3, morada, estado, preco_min, preco_max, img);
                listaHotel.add(hotel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listaHotel;
    }

    public static Hotel jsonParserDetalhesQuarto_hotel(String resposta) {
        Hotel hotel = null;

        try {
            JSONObject jsonObject = new JSONObject(resposta);

            // receber objeto da resposta (array)
            JSONObject hotelJson = jsonObject.getJSONObject("hotel");

            // receber os valores do objeto
            int id = hotelJson.getInt("id");
            String nome = hotelJson.getString("nome");
            String descricao = hotelJson.getString("descricao");
            int contacto = hotelJson.getInt("contacto");
            String website = hotelJson.getString("website");
            int cp4 = hotelJson.getInt("cp4");
            int cp3 = hotelJson.getInt("cp3");
            String morada = hotelJson.getString("morada");
            String img = Singleton.mUrl + "/assets/hoteis/" + hotelJson.getString("img");

            hotel = new Hotel(id, nome, descricao, contacto, website, cp4, cp3, morada, img);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return hotel;
    }

    public static Quarto jsonParserDetalhesQuarto(String resposta) {
        Quarto quarto = null;

        try {
            JSONObject jsonObject = new JSONObject(resposta);

            // receber objeto da resposta (array)
            JSONObject quartoJson = jsonObject.getJSONObject("quarto");
            JSONObject categoriaJson = jsonObject.getJSONObject("categoria");

            // receber os valores do objeto
            int id = quartoJson.getInt("id");
            String descricao = quartoJson.getString("descricao");
            double precoNoite = quartoJson.getDouble("precoNoite");
            String categoria = categoriaJson.getString("descricao");
            String img = Singleton.mUrl + "/assets/quartos/" + quartoJson.getString("img");


            quarto = new Quarto(id, descricao, precoNoite, categoria, img);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return quarto;
    }

    public static ArrayList<ComodidadesQuarto> jsonParserDetalhesQuarto_comodidadesQuarto(String resposta) {
        ArrayList<ComodidadesQuarto> listaComodidadesQuartos = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(resposta);

            JSONArray jsonArray = jsonObject.getJSONArray("comodidadesQuarto");

            for (int i = 0; i < jsonArray.length(); i++) {
                // receber objeto da resposta (array)
                JSONObject array = (JSONObject) jsonArray.get(i);
                JSONObject comodidadesQuartoJson = array.getJSONObject("descricao");

                // receber os valores do objeto
                int id = comodidadesQuartoJson.getInt("id");
                String descricao = comodidadesQuartoJson.getString("nome");

                ComodidadesQuarto comodidadesQuarto = new ComodidadesQuarto(id, descricao);
                listaComodidadesQuartos.add(comodidadesQuarto);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listaComodidadesQuartos;
    }

    public static ArrayList<ComodidadesHotel> jsonParserDetalhesQuarto_comodidadesHotel(String resposta) {
        ArrayList<ComodidadesHotel> listaComodidadesHoteis = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(resposta);

            JSONArray jsonArray = jsonObject.getJSONArray("comodidadesHotel");

            for (int i = 0; i < jsonArray.length(); i++) {
                // receber objeto da resposta (array)
                JSONObject array = (JSONObject) jsonArray.get(i);
                JSONObject comodidadesHotelJson = array.getJSONObject("descricao");

                // receber os valores do objeto
                int id = comodidadesHotelJson.getInt("id");
                String descricao = comodidadesHotelJson.getString("nome");

                ComodidadesHotel comodidadesHotel = new ComodidadesHotel(id, descricao);
                listaComodidadesHoteis.add(comodidadesHotel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listaComodidadesHoteis;
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
                String img = Singleton.mUrl + "/assets/quartos/" + quartoJson.getString("img");

                Quarto quarto = new Quarto(id, descricao, precoNoite, img);
                listaQuarto.add(quarto);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listaQuarto;
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
                if (!userInfoJson.isNull("nome")) {
                    nome = userInfoJson.getString("nome");
                }
                if (!userInfoJson.isNull("apelido")) {
                    apelido = userInfoJson.getString("apelido");
                }
                if (!userInfoJson.isNull("contactoTel")) {
                    contactoTel = userInfoJson.getInt("contactoTel");
                }
                if (!userInfoJson.isNull("morada")) {
                    morada = userInfoJson.getString("morada");
                }
                if (!userInfoJson.isNull("nif")) {
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

    public static ArrayList<Reserva> jsonParserListaReservas(String resposta) {
        ArrayList<Reserva> listaReserva = new ArrayList<>();

        try {
            JSONArray respostaArray = new JSONArray(resposta);
            for (int i = 0; i < respostaArray.length(); i++) {
                JSONObject array = (JSONObject) respostaArray.get(i);
                JSONObject reservaJson = array.getJSONObject("reserva");
                JSONObject hotelJson = array.getJSONObject("hotel");


                int id = reservaJson.getInt("id");
                int nPessoas = reservaJson.getInt("nPessoas");
                double preco = reservaJson.getDouble("preco");
                String dataCheckIn = reservaJson.getString("dataCheckin");
                String dataCheckOut = reservaJson.getString("dataCheckout");
                String estado = reservaJson.getString("estado");
                String hotel = hotelJson.getString("descricao");
                int userInfoId = reservaJson.getInt("userInfoId");

                Reserva reserva = new Reserva(id, nPessoas, preco, dataCheckIn, dataCheckOut, estado, hotel, userInfoId);
                listaReserva.add(reserva);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listaReserva;
    }

    public static ArrayList<Regiao> jsonParserListaRegioes(String resposta) {
        ArrayList<Regiao> listaRegiao = new ArrayList<>();

        try {
            JSONArray respostaArray = new JSONArray(resposta);
            for (int i = 0; i < respostaArray.length(); i++) {
                JSONObject reservaJson = (JSONObject) respostaArray.get(i);

                int id = reservaJson.getInt("id");
                String nome = reservaJson.getString("nome");

                Regiao reserva = new Regiao(id, nome);
                listaRegiao.add(reserva);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listaRegiao;
    }

    public static User jsonParserLogin(String resposta) {
        User user = null;

        try {
            JSONObject login = new JSONObject(resposta);
            JSONObject userJson = login.getJSONObject("user");

            int id = userJson.getInt("id");
            String username = userJson.getString("username");
            String email = userJson.getString("email");
            String access_token = userJson.getString("access_token");
            String img = Singleton.mUrl + "/assets/usersImage/" + login.getString("img");

            user = new User(id, username, email, access_token, img);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static Boolean jsonParserValidaLogin(String resposta) {
        Boolean flag = null;

        try {
            JSONObject login = new JSONObject(resposta);
            if (login.getBoolean("success")) {
                flag = true;
            } else {
                flag = false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return flag;
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

    public static Boolean jsonParserRegisterReserva(String resposta) {
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

    public static Boolean jsonParserForgot(String resposta) {
        Boolean flag = null;

        try {
            JSONObject forgot = new JSONObject(resposta);
            if (forgot.getBoolean("success")) {
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