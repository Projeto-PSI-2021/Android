package com.example.tophotels.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tophotels.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
    }

    public void mostrarEsqueceuSenha(View view) {
        Toast.makeText(getApplicationContext(),"Esqueceu senha clicado",Toast.LENGTH_LONG).show();
    }

    public void mostrarActivityPaginaInicial(View view) {
        Intent i = new Intent(LoginActivity.this, TelaPosLogin.class);
        startActivity(i);
    }

    public void mostrarActivityRegistarConta(View view) {
        Intent i = new Intent(LoginActivity.this, RegistarContaActivity.class);
        startActivity(i);
    }
}