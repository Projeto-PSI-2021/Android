package com.example.tophotels.vistas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tophotels.R;

import java.util.ArrayList;
import java.util.Calendar;

public class PagamentoActivity extends AppCompatActivity {
    public static final String ID = "com.example.tophotels.vistas.id";
    private TextView etPreco, data_checkin, data_checkout;
    private Button btPagamento;
    private String data_inicial, data_final;
    private Spinner spPessoas;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);
        setTitle("Pagamento");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etPreco = findViewById(R.id.etPrecoPagamento);

        data_checkin = findViewById(R.id.etDataCheckinPagamento);
        data_checkout = findViewById(R.id.etDataCheckoutPagamento);
        btPagamento = findViewById(R.id.bt_Pagamento);

        SharedPreferences sharedPreferencesInfoUser = getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
        userId = sharedPreferencesInfoUser.getInt(MenuMainActivity.USER_ID, 0);

        SharedPreferences sharedPreferencesPesquisarHotel = getSharedPreferences(PesquisarHotelFragment.PESQUISA_HOTEL, Context.MODE_PRIVATE);
        data_inicial = sharedPreferencesPesquisarHotel.getString(PesquisarHotelFragment.DATA_INICIAL, null);
        data_final = sharedPreferencesPesquisarHotel.getString(PesquisarHotelFragment.DATA_FINAL, null);

        data_checkin.setText(data_inicial);
        data_checkout.setText(data_final);

        String[] values_dataInicial = data_inicial.split("-");
        int year_inicial = Integer.parseInt(values_dataInicial[0]);
        int month_inicial = Integer.parseInt(values_dataInicial[1]);
        int day_inicial = Integer.parseInt(values_dataInicial[2]);

        String[] values_dataFinal = data_final.split("-");
        int year_final = Integer.parseInt(values_dataFinal[0]);
        int month_final = Integer.parseInt(values_dataFinal[1]);
        int day_final = Integer.parseInt(values_dataFinal[2]);


        Calendar data1 = Calendar.getInstance();
        Calendar data2 = Calendar.getInstance();

        data1.clear();
        data1.set(year_inicial, month_inicial, day_inicial);
        data2.clear();
        data2.set(year_final, month_final, day_final);


        long diferenca =  data2.getTimeInMillis() - data1.getTimeInMillis();

        float nrDias = (float) diferenca / (24 * 60 * 60 * 1000);

        Intent i = this.getIntent();
        Double preco = i.getDoubleExtra("precoNoite", 0);
        int id = i.getIntExtra("idQuarto", 0);

        spPessoas = findViewById(R.id.spPessoas);
        ArrayList<String> array = new ArrayList<>();
        array.add("1");
        array.add("2");
        array.add("3");
        array.add("4");
        array.add("5");
        array.add("6");


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPessoas.setAdapter(arrayAdapter);

        spPessoas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int nrPessoas = Integer.parseInt(parent.getItemAtPosition(position).toString());

                if (nrPessoas == 1) {
                    etPreco.setText("" + Math.round((preco * nrDias) * 1000.0) / 1000.0);
                } else if (nrPessoas == 2) {
                    etPreco.setText("" + Math.round(((preco * 2)* nrDias) * 1000.0) / 1000.0);
                } else if (nrPessoas == 3) {
                    etPreco.setText("" + Math.round(((preco * 3)* nrDias) * 1000.0) / 1000.0);
                } else if (nrPessoas == 4) {
                    etPreco.setText("" + Math.round(((preco * 4)* nrDias) * 1000.0) / 1000.0);
                } else if (nrPessoas == 5) {
                    etPreco.setText("" + Math.round(((preco * 5)* nrDias) * 1000.0) / 1000.0);
                } else {
                    etPreco.setText("" + Math.round(((preco * 6)* nrDias) * 1000.0) / 1000.0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openDialog();
                DialogCartao dialog = new DialogCartao();
                dialog.preco = etPreco.getText().toString();
                dialog.nrPessoas = spPessoas.getSelectedItem().toString();
                dialog.dataCheckin = data_checkin.getText().toString();
                dialog.dataCheckout = data_checkout.getText().toString();
                dialog.quartoId = id;
                dialog.userId = userId;
                dialog.show(getSupportFragmentManager(), "Dialog cartão");
            }
        });
    }

}
