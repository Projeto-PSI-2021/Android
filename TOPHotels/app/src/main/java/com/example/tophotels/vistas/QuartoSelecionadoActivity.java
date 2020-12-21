package com.example.tophotels.vistas;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.tophotels.R;
import com.example.tophotels.modelos.Quarto;
import com.example.tophotels.modelos.Singleton;


public class QuartoSelecionadoActivity extends AppCompatActivity{
    public static final String ID = "com.example.tophotels.vistas.id";

    private TextView tvDescricao;
    private ImageView imgQuarto;
    private Quarto quarto;

    // tabela user.access_token string
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quarto_selecionado);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvDescricao = findViewById(R.id.tvComodidades);
        imgQuarto = findViewById(R.id.imgQuartoSelecionado);

        SharedPreferences sharedPreferencesUser = getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
        token = sharedPreferencesUser.getString(MenuMainActivity.TOKEN, null);

        int id = (int) getIntent().getLongExtra(ID, -1);

        if (id == -1){
            quarto = null;
        } else {
            quarto = Singleton.getInstance(getApplicationContext()).getQuarto(id);
        }

        if (quarto != null) {
            setTitle(quarto.getDescricao());
            preencheDetalhes(quarto);
        }

    }

    private void preencheDetalhes(Quarto quarto) {
        tvDescricao.setText(quarto.getDescricao());
        Glide.with(getApplicationContext())
                .load(quarto.getImg())
                .placeholder(R.drawable.hotel)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgQuarto);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_navbar, menu);
        return super.onCreateOptionsMenu(menu);
    }
}