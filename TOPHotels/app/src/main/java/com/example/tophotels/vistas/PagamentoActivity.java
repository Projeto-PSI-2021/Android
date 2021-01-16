package com.example.tophotels.vistas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tophotels.R;
import com.example.tophotels.listeners.QuartoListener;
import com.example.tophotels.modelos.ComodidadesQuarto;
import com.example.tophotels.modelos.Quarto;
import com.example.tophotels.modelos.Singleton;

import java.util.ArrayList;

public class PagamentoActivity extends AppCompatActivity {

    public static final String ID = "com.example.tophotels.vistas.id";
    private TextView etPreco;
    private TextView data_checkin, data_checkout;

    private Quarto quarto;
    private String token;

    private String data_inicial;
    private String data_final;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);
        setTitle("Pagamento");

        Intent i = this.getIntent();
        Double preco = i.getDoubleExtra("precoNoite", 0);

        etPreco = findViewById(R.id.etPrecoPagamento);

        data_checkin = findViewById(R.id.etDataCheckinPagamento);
        data_checkout = findViewById(R.id.etDataCheckoutPagamento);

        SharedPreferences sharedPreferencesUser = getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
        token = sharedPreferencesUser.getString(MenuMainActivity.TOKEN, null);

        SharedPreferences sharedPreferencesPesquisarHotel = getSharedPreferences(PesquisarHotelFragment.PESQUISA_HOTEL, Context.MODE_PRIVATE);
        data_inicial = sharedPreferencesPesquisarHotel.getString(PesquisarHotelFragment.DATA_INICIAL, null);
        data_final = sharedPreferencesPesquisarHotel.getString(PesquisarHotelFragment.DATA_FINAL, null);

        data_checkin.setText(data_inicial);
        data_checkout.setText(data_final);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayList<String> array = new ArrayList<>();
        array.add("1");
        array.add("2");
        array.add("3");
        array.add("4");
        array.add("5");
        array.add("6");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int nrPessoas = Integer.parseInt(parent.getItemAtPosition(position).toString());

                if (nrPessoas == 1) {
                    etPreco.setText("" + preco);
                } else if (nrPessoas == 2) {
                    etPreco.setText("" + preco * 2);
                } else if (nrPessoas == 3) {
                    etPreco.setText("" + preco * 3);
                } else if (nrPessoas == 4) {
                    etPreco.setText("" + preco * 4);
                } else if (nrPessoas == 5) {
                    etPreco.setText("" + preco * 5);
                } else {
                    etPreco.setText("" + preco * 6);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
