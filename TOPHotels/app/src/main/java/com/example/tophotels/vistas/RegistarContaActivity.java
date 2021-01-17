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

public class RegistarContaActivity extends AppCompatActivity implements UserListener {
    private EditText etUsername, etPassword, etPasswordConfirmar, etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registar_conta_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etUsername = findViewById(R.id.etUsernameRegistar);
        etEmail = findViewById(R.id.etEmailRegisto);
        etPassword = findViewById(R.id.etPasswordRegisto);
        etPasswordConfirmar = findViewById(R.id.etConfirmaPasswordRegisto);

        Singleton.getInstance(getApplicationContext()).setUserListener(this);
    }

    public void onClickRegistar(View view) {
        if (!JsonParser.isConnectionInternet(getApplicationContext())){
            Toast.makeText(getApplicationContext(), "Não está ligado à internet.", Toast.LENGTH_SHORT).show();
        } else {
            if (!etUsername.getText().toString().matches("")){
                if(isEmailValido(etEmail.getText().toString())){
                    if (!etPassword.getText().toString().matches("")){
                        if (!etPasswordConfirmar.getText().toString().matches("")){
                            if (etPassword.getText().toString().contentEquals(etPasswordConfirmar.getText())){
                                Singleton.getInstance(getApplicationContext()).postRegistoAPI(getApplicationContext(), etUsername.getText().toString(), etEmail.getText().toString(), etPassword.getText().toString());
                            } else {
                                Toast.makeText(getApplicationContext(), "Palavras passe não são iguais, tente outra vez.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Preencher confirmar palavra passe.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Preencher palavra passe.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Endereço de email inválido.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Preencher nome de utilizador.", Toast.LENGTH_SHORT).show();
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
        if (flag) {
            etUsername.setText("");
            etEmail.setText("");
            etPassword.setText("");
            etPasswordConfirmar.setText("");
            Toast.makeText(getApplicationContext(), "Registado com sucesso.", Toast.LENGTH_SHORT).show();
        } else {
            etPassword.setText("");
            etPasswordConfirmar.setText("");
            Toast.makeText(getApplicationContext(), "Erro ao registar.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onForgotPassword(Boolean flag) {

    }
}


