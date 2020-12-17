package com.example.tophotels.vistas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
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
    Fragment fragment = null;

    // aceder a sharedpreference
    public static final String PREF_USER = "PREF_USER";
    // id user
    public static final String USER_ID = "USER_ID";
    // username
    public static final String USERNAME = "USERNAME";
    // access-token
    public static final String TOKEN = "TOKEN";
    // tabela username string
    private String user;

    private NavigationView nav_view;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);
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
        switch (item.getItemId()) {
            /*case R.id.nav_reservas:
                frag = new ListaHoteisFragment();
                setTitle(item.getTitle());
                break;*/

            case R.id.nav_conta:
                Intent intentMenu = new Intent(MenuMainActivity.this, DetalhesContaActivity.class);
                startActivity(intentMenu);
                break;

            case R.id.nav_logout:
                SharedPreferences SM = getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = SM.edit();
                edit.clear();
                edit.apply();

                Intent intent = new Intent(MenuMainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment, fragment).commit();
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        fragment = new PesquisarHotelFragment();
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment, fragment).commit();
        }
    }
}