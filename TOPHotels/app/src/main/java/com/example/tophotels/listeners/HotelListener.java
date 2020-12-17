package com.example.tophotels.listeners;

import com.example.tophotels.modelos.Hotel;

import java.util.ArrayList;

public interface HotelListener {

    void onRefreshListaHotel(ArrayList<Hotel> listahoteis);
    void onRefreshDetalhes();
}
