package com.example.tophotels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
    }

    public void mostrarEsqueceuSenha(View view) {
        Toast.makeText(getApplicationContext(),"Esqueceu senha clicado",Toast.LENGTH_LONG).show();
    }

    public void mostrarActivityRegistarConta(View view) {
        Intent intentRegistarConta = new Intent(LoginActivity.this, RegistarContaActivity.class);
        startActivity(intentRegistarConta);
    }
}