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
import com.example.tophotels.listeners.UserInfoListener;
import com.example.tophotels.listeners.UserListener;
import com.example.tophotels.utils.JsonParser;

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
    //private static final String mUrlAPIUser = "http://tophotelsbackend.ddns.net/api/user";
    //private static final String mUrlAPIUserInfo = "http://tophotelsbackend.ddns.net/api/user-info";
    private static final String mUrlAPIUserInfo = "http://d62aa66b8dd3.eu.ngrok.io/api/user-info";
    private static final String mUrlAPIUser = "http://d62aa66b8dd3.eu.ngrok.io/api/user";
    //private static final String mUrlAPIHotel = "http://tophotelsbackend.ddns.net/api/hotel";
    private static final String mUrlAPIHotel = "http://d62aa66b8dd3.eu.ngrok.io/api/hotel";

    //Listeners
    private HotelListener hotelListener;
    private UserListener userListener;
    private UserInfoListener userInfoListener;


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

    public void postLoginAPI(final Context contexto, final String username, final String password) {
        if (!JsonParser.isConnectionInternet(contexto)) {
            Toast.makeText(contexto, "Não tem Internet.", Toast.LENGTH_SHORT).show();
        } else {
            StringRequest request = new StringRequest(Request.Method.POST,
                    mUrlAPIUser + "/login",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            userListener.onValidateLogin(JsonParser.jsonParserLogin(response));
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
    }

    public void postRegistoAPI(final Context contexto, final String username, final String email, final String password) {
        if (!JsonParser.isConnectionInternet(contexto)) {
            Toast.makeText(contexto, "Não tem Internet.", Toast.LENGTH_SHORT).show();
        } else {
            StringRequest request = new StringRequest(Request.Method.POST,
                    mUrlAPIUser + "/registar",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            userListener.onValidateRegister(JsonParser.jsonParserRegister(response));
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
                    parametros.put("email", email);

                    return parametros;
                }
            };
            volleyQueue.add(request);
        }
    }

    public void getUserInfoAPI(final Context contexto, final String access_token) {
        if (!JsonParser.isConnectionInternet(contexto)) {
            Toast.makeText(contexto, "Não tem Internet.", Toast.LENGTH_SHORT).show();
        } else {
            StringRequest request = new StringRequest(Request.Method.POST,
                    mUrlAPIUser + "/info?access-token=" + access_token,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            userInfoListener.onRefreshDetalhes(JsonParser.jsonParserUserInfo(response));
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

                    parametros.put("access_token", access_token);

                    return parametros;
                }
            };
            volleyQueue.add(request);
        }
    }

    public void patchUserInfoAPI(final Context contexto, UserInfo userInfo, final String access_token) {
        if (!JsonParser.isConnectionInternet(contexto)) {
            Toast.makeText(contexto, "Não tem Internet.", Toast.LENGTH_SHORT).show();
        } else {
            StringRequest request = new StringRequest(Request.Method.PATCH,
                    mUrlAPIUserInfo + "/" + userInfo.getId(),
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            userInfoListener.onUpdateDetalhes(JsonParser.jsonParserUserInfoPatch(response));
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

                    parametros.put("nome", userInfo.getNome());
                    parametros.put("apelido", userInfo.getApelido());
                    parametros.put("contactoTel", "" + userInfo.getContactoTel());
                    parametros.put("morada", userInfo.getMorada());
                    parametros.put("nif", "" + userInfo.getNif());

                    return parametros;
                }
            };
            volleyQueue.add(request);
        }
    }

    public void getAllHotelAPI(final Context contexto) {
        if (!JsonParser.isConnectionInternet(contexto)) {
            Toast.makeText(contexto, "Não tem Internet.", Toast.LENGTH_SHORT).show();

            if (hotelListener != null) {
                hotelListener.onRefreshListaHotel(getListaHoteis());
            }
        } else {
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, mUrlAPIHotel, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            listaHoteis = JsonParser.jsonParserListaHoteis(response);

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

    public ArrayList<Hotel> getListaHoteis() {
        return listaHoteis;
    }

    public void setHotelListener(HotelListener hotelListener) {
        this.hotelListener = hotelListener;
    }

    public void setUserListener(UserListener userListener) {
        this.userListener = userListener;
    }

    public void setUserInfoListener(UserInfoListener userInfoListener) {
        this.userInfoListener = userInfoListener;
    }
}