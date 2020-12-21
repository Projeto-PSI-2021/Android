package com.example.tophotels.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tophotels.R;
import com.example.tophotels.listeners.UserListener;
import com.example.tophotels.modelos.Singleton;
import com.example.tophotels.modelos.User;
import com.example.tophotels.utils.JsonParser;

public class EsqueceuPasswordActivity extends AppCompatActivity implements UserListener {
    EditText etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueceu_password);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etEmail = findViewById(R.id.etEmailForgot);

        Singleton.getInstance(getApplicationContext()).setUserListener(this);
    }

    public void onClickForgot(View view){
        if (!JsonParser.isConnectionInternet(getApplicationContext())) {
            Toast.makeText(getApplicationContext(), "Não está ligado à internet.", Toast.LENGTH_SHORT).show();
        } else {
            if (isEmailValido(etEmail.getText().toString())) {
                Singleton.getInstance(getApplicationContext()).postEsqueceuPassword(getApplicationContext(), etEmail.getText().toString());
            }
        }
    }

    private boolean isEmailValido(String mail) {
        return Patterns.EMAIL_ADDRESS.matcher(mail).matches();
    }

    @Override
    public void onValidateLogin(User user) {

    }

    @Override
    public void onValidateRegister(Boolean flag) {

    }

    @Override
    public void onForgotPassword(Boolean flag) {
        if (flag) {
            etEmail.setText("");
            Toast.makeText(getApplicationContext(), "Seguir instruções enviadas para o seu email.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Endereço de email não encontrado.", Toast.LENGTH_SHORT).show();
        }
    }
}