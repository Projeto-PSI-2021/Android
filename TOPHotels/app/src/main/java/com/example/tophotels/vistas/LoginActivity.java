package com.example.tophotels.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tophotels.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    private RequestQueue queue;
    private EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        SharedPreferences sharedPreferences = getSharedPreferences("username", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        this.etUsername.setText(username);

        queue = Volley.newRequestQueue(this);
    }

    public void mostrarEsqueceuSenha(View view) {
        Toast.makeText(getApplicationContext(), "Esqueceu senha clicado", Toast.LENGTH_LONG).show();
    }

    public void mostrarActivityPaginaInicial(View view) {
        Intent i = new Intent(LoginActivity.this, TelaPosLogin.class);
        startActivity(i);
    }

    public void mostrarActivityRegistarConta(View view) {
        Intent i = new Intent(LoginActivity.this, RegistarContaActivity.class);
        startActivity(i);
    }

    public void onCLickLogin(View view) {
        if (!etUsername.getText().toString().matches("")) {
            if (!etPassword.getText().toString().matches("")) {
                if (isLigadoInternet()) {
                    String url = "http://tophotelsfrontend.ddns.net/api/user/login";

                    final HashMap<String, String> data = new HashMap<String, String>();
                    data.put("username", etUsername.getText().toString());
                    data.put("password", etPassword.getText().toString());

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                            Request.Method.POST, url, new JSONObject(data),
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    // código de resposta enviado pela api
                                    int codigo = response.optInt("codigo");
                                    if (codigo == 200) {
                                        Intent intentPosLogin = new Intent(getApplicationContext(), TelaPosLogin.class);
                                        intentPosLogin.putExtra(TelaPosLogin.USERNAME, etUsername.getText().toString());
                                        startActivity(intentPosLogin);
                                    } else if (codigo == 406) {
                                        Toast.makeText(getApplicationContext(), "Utilizador ou password incorreto, tente outra vez", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getApplicationContext(), "Erro - " + error.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                    );
                    queue.add(jsonObjectRequest);
                } else {
                    Toast.makeText(this, "Erro de ligação! Não está ligado à internet", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Preencher password", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Preencher username", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isLigadoInternet() {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        boolean isLigado = activeNetwork != null && activeNetwork.isConnected();
        return isLigado;
    }
}