package com.example.tophotels;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class HotelSelecionadoActivity extends AppCompatActivity{

    ListView listaComodidades;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_selecionado);

        listaComodidades = findViewById(R.id.listaComodidades);

        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("WIFI");
        arrayList.add("WIFI");
        arrayList.add("WIFI");
        arrayList.add("WIFI");
        arrayList.add("WIFI");

        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);

        listaComodidades.setAdapter(arrayAdapter);





    }
}