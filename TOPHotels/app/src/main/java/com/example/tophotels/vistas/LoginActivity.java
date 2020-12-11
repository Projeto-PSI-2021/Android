package com.example.tophotels.vistas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tophotels.listeners.LoginListener;
import com.example.tophotels.modelos.SingletonHotel;
import com.example.tophotels.R;
import com.example.tophotels.utils.HotelJsonParser;

public class LoginActivity extends AppCompatActivity implements LoginListener {
    private EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        this.etUsername.setText("joaoneves");
        this.etPassword.setText("12345678");

        SingletonHotel.getInstance(getApplicationContext()).setLoginListener(this);
    }

    public void mostrarEsqueceuSenha(View view) {
        Toast.makeText(getApplicationContext(), "Esqueceu senha clicado", Toast.LENGTH_LONG).show();
    }

    public void mostrarActivityRegistarConta(View view) {
        Intent i = new Intent(LoginActivity.this, RegistarContaActivity.class);
        startActivity(i);
    }

    public void onClickLogin(View view) {
        if (!HotelJsonParser.isConnectionInternet(getApplicationContext())) {
            Toast.makeText(getApplicationContext(), "Não tem ligação à internet.", Toast.LENGTH_SHORT).show();
        } else {
            if (!etUsername.getText().toString().matches("")) {
                if (!etPassword.getText().toString().matches("")) {
                    SingletonHotel.getInstance(getApplicationContext()).loginAPI(getApplicationContext(), etUsername.getText().toString(), etPassword.getText().toString());
                } else {
                    Toast.makeText(this, "Preencher password", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Preencher username", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onValidateLogin(String token, String username) {
        if (token != null) {
            saveSharePreferencesUserInfo(token, username);
            Intent intentMenu = new Intent(getApplicationContext(), MenuMainActivity.class);
            startActivity(intentMenu);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Login Inválido", Toast.LENGTH_SHORT).show();
            etPassword.setText("");
        }
    }

    private void saveSharePreferencesUserInfo(String token, String username) {
        SharedPreferences sharedPreferencesInfoUser = getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferencesInfoUser.edit();
        editor.putString(MenuMainActivity.USERNAME, username);
        editor.putString(MenuMainActivity.TOKEN, token);
        editor.apply();
    }
}