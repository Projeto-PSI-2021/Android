package com.example.tophotels.vistas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.tophotels.R;
import com.example.tophotels.vistas.ListaHoteisFragment;
import com.example.tophotels.vistas.PesquisarHotelFragment;
import com.google.android.material.navigation.NavigationView;




public class TelaPosLogin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private NavigationView nav_view;
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_pos_login);

        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        nav_view = findViewById(R.id.nav_view);
        drawerLayout= findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawerLayout, tb,R.string.ndOpen, R.string.ndClose);

        toogle.syncState();
        drawerLayout.addDrawerListener(toogle);
        nav_view.setNavigationItemSelectedListener(this);
        carregarInicio();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
       Fragment frag = null;
        switch (item.getItemId()){
            case R.id.nav_reservas:
                frag = new ListaHoteisFragment();
                setTitle(item.getTitle());
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        if(frag != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment, frag).commit();
        }

        return true;
    }

    private void carregarInicio() {
        Fragment frag = null;
        frag = new PesquisarHotelFragment();
        if (frag != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment, frag).commit();
        }else{

        }
    }





}