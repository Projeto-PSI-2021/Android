package com.example.tophotels.modelos;

import android.content.Context;

import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tophotels.listeners.ComodidadesQuartoListener;
import com.example.tophotels.listeners.HotelListener;
import com.example.tophotels.listeners.QuartoListener;
import com.example.tophotels.listeners.RegiaoListener;
import com.example.tophotels.listeners.ReservaListener;
import com.example.tophotels.listeners.UserInfoListener;
import com.example.tophotels.listeners.UserListener;
import com.example.tophotels.utils.JsonParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Singleton {
    private ArrayList<Hotel> listaHoteis;
    private ArrayList<Quarto> listaQuartos;
    private ArrayList<Reserva> listaReservas;
    private ArrayList<Regiao> listaRegioes;
    private ArrayList<ComodidadesQuarto> listaComodidadesQuarto;
    private ArrayList<ComodidadesHotel> listaComodidadesHotel;
    private User user;

    private TophotelsBDHelper tophotelsBDHelper = null;

    private static Singleton instance = null; //Instancia de objeto "static"

    //Volley
    private static RequestQueue volleyQueue = null;

    //Endereços api
    // Endereço base
<<<<<<< Updated upstream
    public static final String mUrl = "http://4153568d536c.ngrok.io";
=======
    public static final String mUrl = "http://5e60b8d6d053.eu.ngrok.io";
>>>>>>> Stashed changes
    private static final String mUrlAPIUser = mUrl + "/api/user";
    private static final String mUrlAPIUserInfo = mUrl + "/api/user-info";
    private static final String mUrlAPIHotel = mUrl + "/api/hotel";
    private static final String mUrlAPIRegiao = mUrl + "/api/regiao-hotel";
    private static final String mUrlAPIQuarto = mUrl + "/api/quarto";
    private static final String mUrlAPIPedidoReserva = mUrl + "/api/pedido-reserva";




    //Listeners
    private HotelListener hotelListener;
    private QuartoListener quartoListener;
    private UserListener userListener;
    private UserInfoListener userInfoListener;
    private RegiaoListener regiaoListener;
    private ReservaListener reservaListener;
    private ComodidadesQuartoListener comodidadesQuartoListener;


    public static synchronized Singleton getInstance(Context contexto) {
        if (instance == null) {
            instance = new Singleton(contexto);
            volleyQueue = Volley.newRequestQueue(contexto);
        }
        return instance;
    }

    private Singleton(Context contexto) {
        this.listaHoteis = new ArrayList<>();
        this.listaQuartos = new ArrayList<>();
        this.listaReservas = new ArrayList<>();
        this.listaRegioes = new ArrayList<>();
        this.listaComodidadesQuarto = new ArrayList<>();
        this.listaComodidadesHotel = new ArrayList<>();
        this.tophotelsBDHelper = new TophotelsBDHelper(contexto);
    }

    // ** API ** //

    public void postLoginAPI(final Context contexto, final String username, final String password) {
        if (!JsonParser.isConnectionInternet(contexto)) {
            if (userListener != null) {
                userListener.onValidateLogin(getUserDB(username));
            }
        } else {
            StringRequest request = new StringRequest(Request.Method.POST,
                    mUrlAPIUser + "/login",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            user = JsonParser.jsonParserLogin(response);
                            adicionarUserBD(user);

                            if (userListener != null) {
                                userListener.onValidateLogin(user);
                            }
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
            Toast.makeText(contexto, "Não tem ligação à internet.", Toast.LENGTH_SHORT).show();
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

    public void postCriarReservaAPI(final Context contexto, final int nrPessoas, final double preco, final String dataCheckin, final String dataCheckout, final int quartoId, final int userId) {
        if (!JsonParser.isConnectionInternet(contexto)) {
            Toast.makeText(contexto, "Não tem ligação à internet.", Toast.LENGTH_SHORT).show();
        } else {
            StringRequest request = new StringRequest(Request.Method.POST,
                    mUrlAPIPedidoReserva + "/criar-reserva",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            reservaListener.onValidateRegisterReserva(JsonParser.jsonParserRegisterReserva(response));
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

                    parametros.put("nPessoas", ""+nrPessoas);
                    parametros.put("preco", ""+preco);
                    parametros.put("dataCheckIn", dataCheckin);
                    parametros.put("dataCheckOut", dataCheckout);
                    parametros.put("quartoId", ""+quartoId);
                    parametros.put("userId", ""+userId);

                    return parametros;
                }
            };
            volleyQueue.add(request);
        }
    }

    public void postEsqueceuPassword(final Context contexto, final String email) {
        if (!JsonParser.isConnectionInternet(contexto)) {
            Toast.makeText(contexto, "Não tem ligação à internet.", Toast.LENGTH_SHORT).show();
        } else {
            StringRequest request = new StringRequest(Request.Method.POST,
                    mUrlAPIUser + "/reset-password",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            userListener.onForgotPassword(JsonParser.jsonParserForgot(response));
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

                    parametros.put("email", email);

                    return parametros;
                }
            };
            volleyQueue.add(request);
        }
    }

    public void getUserInfoAPI(final Context contexto, final String access_token) {
        if (!JsonParser.isConnectionInternet(contexto)) {
            Toast.makeText(contexto, "Não tem ligação à internet.", Toast.LENGTH_SHORT).show();
        } else {
            StringRequest request = new StringRequest(Request.Method.GET,
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
            });
            volleyQueue.add(request);
        }
    }

    public void patchUserInfoAPI(final Context contexto, UserInfo userInfo, final String access_token) {
        if (!JsonParser.isConnectionInternet(contexto)) {
            Toast.makeText(contexto, "Não tem ligação à internet.", Toast.LENGTH_SHORT).show();
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

    public void getListaReservasAPI(final Context contexto, final String access_token, final int userInfo_id) {
        if (!JsonParser.isConnectionInternet(contexto)) {
            Toast.makeText(contexto, "Não tem ligação à internet.", Toast.LENGTH_SHORT).show();
            if (userInfoListener != null) {
                // receber com a base de dados local
                userInfoListener.onRefreshListaReserva(getListaReservasDB(userInfo_id));
            }
        } else {
            StringRequest request = new StringRequest(Request.Method.GET,
                    mUrlAPIUser + "/lista-reservas?access-token=" + access_token,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            listaReservas = JsonParser.jsonParserListaReservas(response);
                            adicionarReservasBD(listaReservas);

                            if (userInfoListener != null) {
                                userInfoListener.onRefreshListaReserva(listaReservas);
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(contexto, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            volleyQueue.add(request);
        }
    }

    public void getListaRegioesAPI(final Context contexto, final String access_token) {
        if (!JsonParser.isConnectionInternet(contexto)) {
            Toast.makeText(contexto, "Não tem ligação à internet.", Toast.LENGTH_SHORT).show();
        } else {
            StringRequest request = new StringRequest(Request.Method.GET,
                    mUrlAPIRegiao + "?sort=nome&access-token=" + access_token,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            listaRegioes = JsonParser.jsonParserListaRegioes(response);

                            if (regiaoListener != null) {
                                regiaoListener.onRefreshListaRegiao(listaRegioes);
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(contexto, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            volleyQueue.add(request);
        }
    }

    public void postPesquisaHotel(final Context contexto, final String localidade, final String data_inicial, final String data_final, final String access_token) {
        if (!JsonParser.isConnectionInternet(contexto)) {
            Toast.makeText(contexto, "Não tem ligação à internet.", Toast.LENGTH_SHORT).show();
            if (hotelListener != null) {
                // receber com a base de dados local
                //hotelListener.onRefreshListaHotel(getListaHoteis);
            }
        } else {
            StringRequest request = new StringRequest(Request.Method.POST,
                    mUrlAPIHotel + "/pesquisar?access-token=" + access_token,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            listaHoteis = JsonParser.jsonParserListaHoteis(response);

                            if (hotelListener != null) {
                                hotelListener.onRefreshListaHotel(listaHoteis);
                            }
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

                    parametros.put("localidade", localidade);
                    parametros.put("data_inicial", data_inicial);
                    parametros.put("data_final", data_final);

                    return parametros;
                }
            };

            volleyQueue.add(request);
        }
    }

    public void postPesquisaQuartos(final Context contexto, final int hotelId, final String data_inicial, final String data_final, final String access_token) {
        if (!JsonParser.isConnectionInternet(contexto)) {
            Toast.makeText(contexto, "Não tem ligação à internet.", Toast.LENGTH_SHORT).show();
            if (quartoListener != null) {
                // receber com a base de dados local
                //hotelListener.onRefreshListaHotel(getListaHoteis);
            }
        } else {
            StringRequest request = new StringRequest(Request.Method.POST,
                    mUrlAPIQuarto + "/receber?access-token=" + access_token,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            listaQuartos = JsonParser.jsonParserListaQuartos(response);

                            if (quartoListener != null) {
                                quartoListener.onRefreshListaQuarto(listaQuartos);
                            }
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

                    parametros.put("hotel_id", "" + hotelId);
                    parametros.put("data_inicial", data_inicial);
                    parametros.put("data_final", data_final);

                    return parametros;
                }
            };

            volleyQueue.add(request);
        }
    }

    public void getDetalhesQuartoAPI(final Context contexto, final int quartoId, final String access_token) {
        if (!JsonParser.isConnectionInternet(contexto)){
            Toast.makeText(contexto, "Não tem Internet.", Toast.LENGTH_SHORT).show();
            if (userInfoListener != null) {
                // receber com a base de dados local
                //quartoListener.onRefreshListaHotel(getListaQuartos);
            }
        } else {
            StringRequest request = new StringRequest(Request.Method.GET,
                    mUrlAPIQuarto + "/" + quartoId + "/detalhes?access-token="+access_token,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            listaComodidadesQuarto = JsonParser.jsonParserDetalhesQuarto_comodidadesQuarto(response);
                            listaComodidadesHotel = JsonParser.jsonParserDetalhesQuarto_comodidadesHotel(response);

                            if (quartoListener != null) {
                                quartoListener.onLoadComodidadesQuarto(listaComodidadesQuarto);
                                quartoListener.onLoadDetalhesQuarto(JsonParser.jsonParserDetalhesQuarto(response));
                            }
                            if (hotelListener != null) {
                                hotelListener.onLoadComodidadesHotel(listaComodidadesHotel);
                                hotelListener.onLoadDetalhes(JsonParser.jsonParserDetalhesQuarto_hotel(response));
                            }
                        }
                    }, new Response.ErrorListener() {
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

    public Quarto getQuarto(long id) {
        for (Quarto quarto : this.listaQuartos) {
            if (quarto.getId() == id) {
                return quarto;
            }
        }
        return null;
    }

    public Reserva getReserva(long id) {
        for (Reserva reserva : this.listaReservas) {
            if (reserva.getId() == id) {
                return reserva;
            }
        }
        return null;
    }

    public Regiao getRegiaoDB(long id) {
        for (Regiao regiao : this.listaRegioes) {
            if (regiao.getId() == id) {
                return regiao;
            }
        }
        return null;
    }

    public void adicionarUserBD(User user) {
        tophotelsBDHelper.adicionaUser(user);
    }

    public void adicionarReservasBD(ArrayList<Reserva> listaReservas) {
        tophotelsBDHelper.removerAllReservasBD();
        for (Reserva reserva : listaReservas) {
            tophotelsBDHelper.adicionaReserva(reserva);
        }
    }

    public ArrayList<Hotel> getListaHoteis() {
        return listaHoteis;
    }

    public ArrayList<ComodidadesQuarto> getListaComodidadesQuarto() {
        return listaComodidadesQuarto;
    }

    public ArrayList<ComodidadesHotel> getListaComodidadesHotel() {
        return listaComodidadesHotel;
    }

    public ArrayList<Quarto> getListaQuartos() {
        return listaQuartos;
    }

    public ArrayList<Reserva> getListaReservas() {
        return listaReservas;
    }

    public User getUserDB(String username) {
        user = tophotelsBDHelper.getUserDB(username);
        return user;
    }

    public ArrayList<Reserva> getListaReservasDB(int userInfo_id) {
        listaReservas = tophotelsBDHelper.getReservasBD(userInfo_id);
        return listaReservas;
    }

    // ** SET-LISTENERS ** //

    public void setHotelListener(HotelListener hotelListener) {
        this.hotelListener = hotelListener;
    }

    public void setQuartoListener(QuartoListener quartoListener) {
        this.quartoListener = quartoListener;
    }

    public void setUserListener(UserListener userListener) {
        this.userListener = userListener;
    }

    public void setUserInfoListener(UserInfoListener userInfoListener) {
        this.userInfoListener = userInfoListener;
    }

    public void setRegiaoListener(RegiaoListener regiaoListener) {
        this.regiaoListener = regiaoListener;
    }

    public void setListaComodidadesQuarto(ComodidadesQuartoListener comodidadesQuartoListener){
        this.comodidadesQuartoListener = comodidadesQuartoListener;
    }

    public void setReservaListener(ReservaListener reservaListener) {
        this.reservaListener = reservaListener;
    }

    // ** END SET-LISTENERS ** //
}