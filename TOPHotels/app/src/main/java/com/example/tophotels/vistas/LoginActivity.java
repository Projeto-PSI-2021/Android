package com.example.tophotels.vistas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.tophotels.AppController;
import com.example.tophotels.R;

import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();
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

    public void onClickLogin(View view) {
        if (!etUsername.getText().toString().matches("")) {
            if (!etPassword.getText().toString().matches("")) {
                String url = "http://tophotelsfrontend.ddns.net/api/user/login";

                final HashMap<String, String> data = new HashMap<String, String>();
                data.put("username", etUsername.getText().toString());
                data.put("password", etPassword.getText().toString());

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.POST, url, new JSONObject(data),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.i(TAG, response.toString());
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
                                Toast.makeText(getApplicationContext(), "Erro - " + error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                );
                AppController.gethInstance().addToQueue(jsonObjectRequest);
            } else {
                Toast.makeText(this, "Preencher password", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Preencher username", Toast.LENGTH_SHORT).show();
        }
    }
}