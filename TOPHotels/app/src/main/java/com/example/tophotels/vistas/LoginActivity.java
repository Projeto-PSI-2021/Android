package com.example.tophotels.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tophotels.listeners.UserListener;
import com.example.tophotels.modelos.Singleton;
import com.example.tophotels.R;
import com.example.tophotels.modelos.User;
import com.example.tophotels.utils.JsonParser;

public class LoginActivity extends AppCompatActivity implements UserListener {
    private EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_activity);

        etUsername = findViewById(R.id.etUsernameLogin);
        etPassword = findViewById(R.id.etPasswordLogin);

        this.etUsername.setText("joaoneves");
        this.etPassword.setText("12345678");

        Singleton.getInstance(getApplicationContext()).setUserListener(this);


        //verificar se a sharedpreference já possui login
        //se possuir chamar o método do login e enviar os dados inseridos na sharedpreference.
    }

    public void mostrarActivityEsqueceuSenha(View view) {
        Intent i = new Intent(LoginActivity.this, EsqueceuPasswordActivity.class);
        startActivity(i);
    }

    public void mostrarActivityRegistarConta(View view) {
        Intent i = new Intent(LoginActivity.this, RegistarContaActivity.class);
        startActivity(i);
    }

    public void onClickLogin(View view) {
        if (!JsonParser.isConnectionInternet(getApplicationContext())) {
            Toast.makeText(getApplicationContext(), "Não tem ligação à internet.", Toast.LENGTH_SHORT).show();
        } else {
            if (!etUsername.getText().toString().matches("")) {
                if (!etPassword.getText().toString().matches("")) {
                    Singleton.getInstance(getApplicationContext()).postLoginAPI(getApplicationContext(), etUsername.getText().toString(), etPassword.getText().toString());
                } else {
                    Toast.makeText(this, "Preencher password", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Preencher username", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onValidateLogin(User user) {
        if (user != null) {
            saveSharePreferencesUserInfo(user);
            Intent intentMenu = new Intent(getApplicationContext(), MenuMainActivity.class);
            startActivity(intentMenu);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Login Inválido", Toast.LENGTH_SHORT).show();
            etPassword.setText("");
        }
    }

    @Override
    public void onValidateRegister(Boolean flag) {

    }

    @Override
    public void onForgotPassword(Boolean flag) {

    }

    private void saveSharePreferencesUserInfo(User user) {
        SharedPreferences sharedPreferencesInfoUser = getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferencesInfoUser.edit();
        editor.clear();
        editor.putInt(MenuMainActivity.USER_ID, user.getId());
        editor.putString(MenuMainActivity.USERNAME, user.getUsername());
        editor.putString(MenuMainActivity.TOKEN, user.getAccess_token());
        editor.apply();
    }
}