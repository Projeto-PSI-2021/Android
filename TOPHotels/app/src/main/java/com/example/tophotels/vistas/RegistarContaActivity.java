package com.example.tophotels.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tophotels.R;

import com.android.volley.RequestQueue;
import com.android.volley.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RegistarContaActivity extends AppCompatActivity {
    private RequestQueue queue;
    private EditText etUsername, etPassword, etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registar_conta_activity);

        etUsername = findViewById(R.id.etUsernameRegistar);
        etEmail = findViewById(R.id.etEmailRegistar);
        etPassword = findViewById(R.id.etPasswordRegistar);

        queue = Volley.newRequestQueue(this);
    }

    public void onClickRegistar(View view) {
        if (isLigadoInternet()) {
            String url = "http://tophotelsfrontend.ddns.net/api/user/registar";

            Toast.makeText(this, "Ligado à internet", Toast.LENGTH_SHORT).show();

            final HashMap<String, String> data = new HashMap<String, String>();
            data.put("username", etUsername.getText().toString());
            data.put("password", etPassword.getText().toString());
            data.put("email", etEmail.getText().toString());

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST, url, new JSONObject(data),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(getApplicationContext(), "Sucesso - " + response.toString(), Toast.LENGTH_SHORT).show();
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
    }

    private boolean isLigadoInternet() {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        boolean isLigado = activeNetwork != null && activeNetwork.isConnected();
        return isLigado;
    }
}


