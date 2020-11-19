package com.example.tophotels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;




public class TelaPosLogin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private NavigationView nav_view;
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_pos_login);


        nav_view = findViewById(R.id.nav_view);
        drawerLayout= findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawerLayout, R.string.ndOpen, R.string.ndClose);

        toogle.syncState();
        drawerLayout.addDrawerListener(toogle);
        nav_view.setNavigationItemSelectedListener(this);
        carregarInicio();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    private void carregarInicio() {
        Fragment frag = null;
        if(frag != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment, frag).commit();
        }
    }
}