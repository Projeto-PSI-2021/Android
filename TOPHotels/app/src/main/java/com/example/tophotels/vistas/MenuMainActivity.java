package com.example.tophotels.vistas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.tophotels.R;
import com.google.android.material.navigation.NavigationView;


public class MenuMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final int FRAGMENTO_PESQUISAHOTEL = 1;

    public static final int fragmentoAtual = FRAGMENTO_PESQUISAHOTEL;

    public static final String USERNAME = "USERNAME";
    public static final String PREF_USER = "PREF_USER";
    public static final String TOKEN = "TOKEN";

    private String user;

    private NavigationView nav_view;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_pos_login);
        setTitle("Pesquise o hotel");

        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        nav_view = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawerLayout, tb, R.string.ndOpen, R.string.ndClose);

        toogle.syncState();
        drawerLayout.addDrawerListener(toogle);
        nav_view.setNavigationItemSelectedListener(this);

        carregaFragmentoInicial();
        carregarCabecalho();
    }

    private void carregaFragmentoInicial() {
        if (fragmentoAtual == FRAGMENTO_PESQUISAHOTEL) {
            Fragment fragmento = new PesquisarHotelFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment, fragmento).commit();
        }
    }

    private void carregarCabecalho() {
        SharedPreferences sharedPreferencesUser = getSharedPreferences(PREF_USER, Context.MODE_PRIVATE);
        user = sharedPreferencesUser.getString(USERNAME, getString(R.string.semEmail));

        View cabecalho = nav_view.getHeaderView(0);
        TextView tvUser = cabecalho.findViewById(R.id.tvNavUser);
        tvUser.setText(user);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment frag = null;
        switch (item.getItemId()) {
            /*case R.id.nav_reservas:
                frag = new ListaHoteisFragment();
                setTitle(item.getTitle());
                break;*/

            case R.id.nav_conta:
                frag = new DefinicoesContaFragment();
                setTitle("Definições de conta");
                break;
        }

        if (frag != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment, frag).commit();
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}