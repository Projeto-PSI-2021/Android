package com.example.tophotels.vistas;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.tophotels.R;
import com.example.tophotels.adaptadores.ListaComodidadesHotelAdapter;
import com.example.tophotels.adaptadores.ListaComodidadesQuartoAdapter;
import com.example.tophotels.listeners.ComodidadesQuartoListener;
import com.example.tophotels.listeners.HotelListener;
import com.example.tophotels.listeners.QuartoListener;
import com.example.tophotels.modelos.ComodidadesHotel;
import com.example.tophotels.modelos.ComodidadesQuarto;
import com.example.tophotels.modelos.Hotel;
import com.example.tophotels.modelos.Quarto;
import com.example.tophotels.modelos.Singleton;
import com.example.tophotels.modelos.UserInfo;

import java.util.ArrayList;


public class QuartoDetalhesActivity extends AppCompatActivity implements QuartoListener, HotelListener {
    public static final String ID = "com.example.tophotels.vistas.id";

    private TextView tvNome, tvDescricao, tvContacto, tvMorada, tvWebsite, tvCodigoPostal;
    private ImageView imgQuarto, imgHotel;
    private Quarto quarto;
    private ComodidadesQuarto comodidadesQuarto;
    private ListaComodidadesQuartoAdapter adapter;
    private ListView lvComodidadesQuarto;
    private ListView lvComodidadesHotel;

    // tabela user.access_token string
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quarto_detalhes);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvNome = findViewById(R.id.tvNome);
        tvDescricao = findViewById(R.id.tvDescricao);
        tvContacto = findViewById(R.id.tvContacto);
        tvMorada = findViewById(R.id.tvMorada);
        tvWebsite = findViewById(R.id.tvWebsite);
        tvCodigoPostal = findViewById(R.id.tvCodigoPostal);
        imgHotel = findViewById(R.id.imgHotel);
        imgQuarto = findViewById(R.id.imgQuarto);

        lvComodidadesQuarto = findViewById(R.id.lvComodidadesQuarto);
        lvComodidadesHotel = findViewById(R.id.lvComodidadesHotel);

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
            //preencheDetalhes(quarto, comodidadesQuarto);

            Singleton.getInstance(getApplicationContext()).setQuartoListener(this);
            Singleton.getInstance(getApplicationContext()).setHotelListener(this);
            Singleton.getInstance(getApplicationContext()).getDetalhesQuartoAPI(getApplicationContext(), quarto.getId(), token);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_navbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onRefreshListaQuarto(ArrayList<Quarto> listaQuartos) {

    }



    @Override
    public void onLoadDetalhesQuarto(Quarto quarto) {
        if (quarto != null) {
            Glide.with(getApplicationContext())
                    .load(quarto.getImg())
                    .placeholder(R.mipmap.ic_launcher)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgQuarto);
        }
    }

    @Override
    public void onLoadComodidadesQuarto(ArrayList<ComodidadesQuarto> listaComodidadesQuarto) {
        if (listaComodidadesQuarto != null) {
            lvComodidadesQuarto.setAdapter(new ListaComodidadesQuartoAdapter(getApplicationContext(), listaComodidadesQuarto));
        }
    }

    @Override
    public void onRefreshListaHotel(ArrayList<Hotel> listahoteis) {


    }



    @Override
    public void onLoadDetalhes(Hotel hotel) {
        if (hotel != null) {
            tvNome.setText(hotel.getNome());
            tvDescricao.setText(hotel.getDescricao());
            tvContacto.setText("" + hotel.getContacto());
            tvMorada.setText(hotel.getMorada());
            Glide.with(getApplicationContext())
                    .load(hotel.getImg())
                    .placeholder(R.mipmap.ic_launcher)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgHotel);
            tvCodigoPostal.setText(hotel.getCp4() + "-" + hotel.getCp3());
            tvWebsite.setText(hotel.getWebsite());


        }
    }

    @Override
    public void onLoadComodidadesHotel(ArrayList<ComodidadesHotel> listaComodidadesHotel) {
        if (listaComodidadesHotel != null) {
            lvComodidadesHotel.setAdapter(new ListaComodidadesHotelAdapter(getApplicationContext(), listaComodidadesHotel));
        }
    }
}