package com.example.tophotels.vistas;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class QuartoDetalhesActivity extends AppCompatActivity implements QuartoListener, HotelListener {
    public static final String ID = "com.example.tophotels.vistas.id";

    private TextView tvNome, tvDescricao, tvContacto, tvMorada, tvWebsite, tvCodigoPostal, tvDescricaoQuarto, tvPrecoQuarto, tvCategoriaQuarto;
    private ImageView imgQuarto, imgHotel;
    private Quarto quarto;
    private ComodidadesQuarto comodidadesQuarto;
    private ListaComodidadesQuartoAdapter adapter;
    private ListView lvComodidadesQuarto;
    private ListView lvComodidadesHotel;
    private FloatingActionButton fabPagamento;

    // tabela user.access_token string
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quarto_detalhes);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Detalhes Hotel
        tvNome = findViewById(R.id.tvNome);
        tvDescricao = findViewById(R.id.tvDescricao);
        tvContacto = findViewById(R.id.tvContacto);
        tvMorada = findViewById(R.id.tvMorada);
        tvWebsite = findViewById(R.id.tvWebsite);
        tvCodigoPostal = findViewById(R.id.tvCodigoPostal);
        imgHotel = findViewById(R.id.imgHotel);

        //Detalhes Quarto
        imgQuarto = findViewById(R.id.imgQuarto);
        tvDescricaoQuarto = findViewById(R.id.tvDescricaoQuarto);
        tvPrecoQuarto = findViewById(R.id.tvPrecoQuarto);
        tvCategoriaQuarto = findViewById(R.id.tvCategoriaQuarto);


        fabPagamento = findViewById(R.id.fab);
        lvComodidadesQuarto = findViewById(R.id.lvComodidadesQuarto);
        lvComodidadesHotel = findViewById(R.id.lvComodidadesHotel);

        SharedPreferences sharedPreferencesUser = getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
        token = sharedPreferencesUser.getString(MenuMainActivity.TOKEN, null);

        int id = (int) getIntent().getLongExtra(ID, -1);

        if (id == -1) {
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


        fabPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFab();
            }
        });
    }

    private void dialogFab(){
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Prosseguir para pagamento")
                .setMessage("Deseja mesmo avançar com o pagamento?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(QuartoDetalhesActivity.this, PagamentoActivity.class);
                        startActivity(i);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
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
            tvDescricaoQuarto.setText("Descrição: " + quarto.getDescricao());
            tvPrecoQuarto.setText("Preço por noite: " + quarto.getPrecoNoite() + "€");
            tvCategoriaQuarto.setText("Categoria: " + quarto.getCategoria());
        }
    }

    private void setAlturaListViewComodidadesQuarto(ListView listView) {
        ListAdapter listAdapter = lvComodidadesQuarto.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;

        for (int i = 0,len = listAdapter.getCount(); i < len; i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        // listView.getDividerHeight() gets the height occupied by the separator between children
        // params.height finally gets the height of the entire ListView full display
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() *  (listAdapter .getCount() - 1));
        listView.setLayoutParams(params);
    }

    @Override
    public void onLoadComodidadesQuarto(ArrayList<ComodidadesQuarto> listaComodidadesQuarto) {
        if (listaComodidadesQuarto != null) {
            lvComodidadesQuarto.setAdapter(new ListaComodidadesQuartoAdapter(getApplicationContext(), listaComodidadesQuarto));
            setAlturaListViewComodidadesQuarto(lvComodidadesQuarto);
        }
    }

    @Override
    public void onRefreshListaHotel(ArrayList<Hotel> listahoteis) {


    }

    @Override
    public void onLoadDetalhes(Hotel hotel) {
        if (hotel != null) {
            tvNome.setText("Nome: " + hotel.getNome());
            tvDescricao.setText("Descrição: " + hotel.getDescricao());
            tvContacto.setText("Contacto: " + hotel.getContacto());
            tvMorada.setText("Morada: " +hotel.getMorada());
            Glide.with(getApplicationContext())
                    .load(hotel.getImg())
                    .placeholder(R.mipmap.ic_launcher)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgHotel);
            tvCodigoPostal.setText("Código-postal: " +hotel.getCp4() + "-" + hotel.getCp3());
            tvWebsite.setText("Website: " + hotel.getWebsite());
        }
    }

    private void setAlturaListViewComodidadesHotel(ListView listView) {
        ListAdapter listAdapter = lvComodidadesHotel.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;

        for (int i = 0,len = listAdapter.getCount(); i < len; i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        // listView.getDividerHeight() gets the height occupied by the separator between children
        // params.height finally gets the height of the entire ListView full display
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() *  (listAdapter .getCount() - 1));
        listView.setLayoutParams(params);
    }

    @Override
    public void onLoadComodidadesHotel(ArrayList<ComodidadesHotel> listaComodidadesHotel) {
        if (listaComodidadesHotel != null) {
            lvComodidadesHotel.setAdapter(new ListaComodidadesHotelAdapter(getApplicationContext(), listaComodidadesHotel));
            setAlturaListViewComodidadesHotel(lvComodidadesHotel);
        }
    }
}