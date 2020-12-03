package com.example.tophotels.modelos;


import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.tophotels.AppController;
import com.example.tophotels.vistas.ListaHoteisFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class SingletonHotel {
    private ArrayList<Hotel> listaHoteis;
    private static SingletonHotel instance = null; //Instancia de objeto "static"

    public static synchronized SingletonHotel getInstance(){
        if(instance==null){
            instance = new SingletonHotel();
        }
        return instance;
    }

    private SingletonHotel(){
        this.listaHoteis = new ArrayList<>();
        carregarHoteis();
        //gerarFakeData();
    }

    public Hotel getHotel(long id){
        for(Hotel hotel: this.listaHoteis){
            if(hotel.getId() == id){
                return hotel;
            }

        }
        return null;
    }

    //private void gerarFakeData() {
    //    adicionarHotel(1, "Hotel Mexil ", null, null, 0, null, 0, 0, "Torres Vedras", null, 0);
    //}

    private void adicionarHotel(int id, String nome, String proprietario, String descricao, int contacto, String website, int cp4, int cp3, String localidade, String morada, int estado) {
        this.listaHoteis.add(new Hotel(id, nome, proprietario, descricao, contacto, website, cp4, cp3, localidade, morada, estado));
    }

    private void carregarHoteis() {
        String url = "http://tophotelsfrontend.ddns.net/api/hotel";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject hotel_resposta = response.getJSONObject(i);
                                Log.e("RESPOSTA", hotel_resposta.getString("nome"));
                                adicionarHotel(hotel_resposta.getInt("id"), hotel_resposta.getString("nome"), hotel_resposta.getString("proprietario"), hotel_resposta.getString("descricao"), hotel_resposta.getInt("contacto"), hotel_resposta.getString("website"), hotel_resposta.getInt("cp4"), hotel_resposta.getInt("cp3"), hotel_resposta.getString("localidade"), hotel_resposta.getString("morada"), hotel_resposta.getInt("estado"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );
        AppController.gethInstance().addToQueue(jsonArrayRequest);
    }

    public ArrayList<Hotel> getListaHoteis() {
        return listaHoteis;
    }
}