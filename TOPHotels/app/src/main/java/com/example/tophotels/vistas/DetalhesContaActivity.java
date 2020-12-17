package com.example.tophotels.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tophotels.R;
import com.example.tophotels.listeners.UserInfoListener;
import com.example.tophotels.modelos.SingletonHotel;
import com.example.tophotels.modelos.UserInfo;
import com.example.tophotels.utils.JsonParser;

public class DetalhesContaActivity extends AppCompatActivity implements UserInfoListener {
    EditText etNome, etApelido, etTelefone, etCartaoCredito, etDataNascimento, etNif, etMorada;

    // aceder a sharedpreference
    public static final String PREF_USERINFO = "PREF_USERINFO";
    // id userinfo
    public static final String USERINFO_ID = "USERINFO_ID";
    // tabela user.access_token string
    private String token;
    // tabela user.id int
    private int userId;
    // tabela userInfo.id int
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_conta);

        SharedPreferences sharedPreferencesUser = getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
        // tabela user.access_token string
        token = sharedPreferencesUser.getString(MenuMainActivity.TOKEN, null);
        // tabela user.id int
        userId = sharedPreferencesUser.getInt(MenuMainActivity.USER_ID, 0);

        etNome = findViewById(R.id.etNomeDetalhe);
        etApelido = findViewById(R.id.etApelidoDetalhe);
        etTelefone = findViewById(R.id.etTelefoneDetalhe);
        etCartaoCredito = findViewById(R.id.etCartaoCreditoDetalhe);
        etDataNascimento = findViewById(R.id.etDataNascimentoDetalhe);
        etNif = findViewById(R.id.etNifDetalhe);
        etMorada = findViewById(R.id.etMoradaDetalhe);

        SingletonHotel.getInstance(getApplicationContext()).setUserInfoListener(this);
        SingletonHotel.getInstance(getApplicationContext()).getUserInfoAPI(getApplicationContext(), token);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onClickGuardarDetalhesConta(View view) {
        if (!JsonParser.isConnectionInternet(getApplicationContext())) {
            Toast.makeText(this, "Não tem ligação à internet.", Toast.LENGTH_SHORT).show();
        } else {
            UserInfo userInfo = null;
            String nome = etNome.getText().toString();
            String apelido = etApelido.getText().toString();
            int contactoTel = Integer.parseInt(etTelefone.getText().toString());
            String morada = etMorada.getText().toString();
            int nif = Integer.parseInt(etNif.getText().toString());
            String img = null;

            userInfo = new UserInfo(id, nome, apelido, contactoTel, morada, nif, img, userId);
            SingletonHotel.getInstance(getApplicationContext()).patchUserInfoAPI(getApplicationContext(), userInfo, token);
        }
    }

    @Override
    public void onRefreshDetalhes(UserInfo userInfo) {
        saveSharePreferencesUserInfo(userInfo);
        escreveDados(userInfo);
    }

    @Override
    public void onUpdateDetalhes(UserInfo userInfo) {
        saveSharePreferencesUserInfo(userInfo);
        escreveDados(userInfo);
    }

    public void escreveDados(UserInfo userInfo) {
        SharedPreferences sharedPreferencesUserInfo = getSharedPreferences(DetalhesContaActivity.PREF_USERINFO, Context.MODE_PRIVATE);
        // tabela userInfo.id int
        id = sharedPreferencesUserInfo.getInt(DetalhesContaActivity.USERINFO_ID, 0);

        etNome.setText(userInfo.getNome());
        etApelido.setText(userInfo.getApelido());
        if (userInfo.getContactoTel() != 0){
            etTelefone.setText(String.valueOf(userInfo.getContactoTel()));
        } else {
            etNif.setText("");
        }
        etMorada.setText(userInfo.getMorada());
        if (userInfo.getNif() != 0){
            etNif.setText(String.valueOf(userInfo.getNif()));
        } else {
            etNif.setText("");
        }

    }

    private void saveSharePreferencesUserInfo(UserInfo userInfo) {
        SharedPreferences sharedPreferencesInfoUser = getSharedPreferences(DetalhesContaActivity.PREF_USERINFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferencesInfoUser.edit();
        editor.putInt(DetalhesContaActivity.USERINFO_ID, userInfo.getId());
        editor.apply();
    }
}