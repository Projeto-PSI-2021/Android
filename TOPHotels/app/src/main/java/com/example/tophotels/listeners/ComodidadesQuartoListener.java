package com.example.tophotels.listeners;

import com.example.tophotels.modelos.ComodidadesQuarto;

import java.util.ArrayList;

public interface ComodidadesQuartoListener {
    void onRefreshListaComodidadesQuarto(ArrayList<ComodidadesQuarto> listaComodidadesQuarto);
    void onRefreshDetalhes();
}
