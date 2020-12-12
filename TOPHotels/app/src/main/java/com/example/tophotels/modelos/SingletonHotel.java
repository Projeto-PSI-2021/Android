package com.example.tophotels.modelos;


import android.content.Context;

import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tophotels.listeners.HotelListener;
import com.example.tophotels.listeners.LoginListener;
import com.example.tophotels.listeners.RegisterListener;
import com.example.tophotels.utils.HotelJsonParser;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SingletonHotel {
    private ArrayList<Hotel> listaHoteis;
    private static SingletonHotel instance = null; //Instancia de objeto "static"

    //Volley
    private static RequestQueue volleyQueue = null;

    //Endereços api
    private static final String mUrlAPILogin = "http://tophotelsfrontend.ddns.net/api/user/login";
    private static final String mUrlAPIRegisto = "http://tophotelsfrontend.ddns.net/api/user/registar";
    private static final String mUrlAPIHotel = "http://tophotelsfrontend.ddns.net/api/hotel";

    //Listeners
    private HotelListener hotelListener;
    private LoginListener loginListener;
    private RegisterListener registerListener;


    public static synchronized SingletonHotel getInstance(Context contexto) {
        if (instance == null) {
            instance = new SingletonHotel();
            volleyQueue = Volley.newRequestQueue(contexto);
        }
        return instance;
    }

    private SingletonHotel() {
        this.listaHoteis = new ArrayList<>();
        //gerarFakeData();
    }

    // ** API ** //

    public void loginAPI(final Context contexto, final String username, final String password) {
        StringRequest request = new StringRequest(Request.Method.POST,
                mUrlAPILogin,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loginListener.onValidateLogin(HotelJsonParser.parserJsonLogin(response), username);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(contexto, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("username", username);
                parametros.put("password", password);
                return parametros;
            }
        };
        volleyQueue.add(request);
    }

    public void registoAPI(final Context contexto, final String username, final String email, final String password) {
        StringRequest request = new StringRequest(Request.Method.POST,
                mUrlAPIRegisto,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        registerListener.onValidateRegister(HotelJsonParser.parserJsonRegister(response));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(contexto, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();

                parametros.put("username", username);
                parametros.put("password", password);
                parametros.put("email", email);

                return parametros;
            }
        };
        volleyQueue.add(request);
    }

    public void getAllHotelAPI(final Context contexto) {
        if (!HotelJsonParser.isConnectionInternet(contexto)) {
            Toast.makeText(contexto, "Não tem Internet.", Toast.LENGTH_SHORT).show();

            if (hotelListener != null) {
                hotelListener.onRefreshListaHotel(getListaHoteis());
            }
        } else {
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, mUrlAPIHotel, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            listaHoteis = HotelJsonParser.parserJsonHoteis(response);

                            if (hotelListener != null) {
                                hotelListener.onRefreshListaHotel(listaHoteis);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(contexto, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
            volleyQueue.add(request);
        }
    }

    // ** END-API ** //

    public Hotel getHotel(long id) {
        for (Hotel hotel : this.listaHoteis) {
            if (hotel.getId() == id) {
                return hotel;
            }

        }
        return null;
    }

    //private void gerarFakeData() {
    //    adicionarHotel(1, "Hotel Mexil ", null, null, 0, null, 0, 0, "Torres Vedras", null, 0);
    //}


    public ArrayList<Hotel> getListaHoteis() {
        return listaHoteis;
    }

    public void setHotelListener(HotelListener hotelListener) {
        this.hotelListener = hotelListener;
    }

    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }

    public void setRegisterListener(RegisterListener registerListener) {
        this.registerListener = registerListener;
    }
}