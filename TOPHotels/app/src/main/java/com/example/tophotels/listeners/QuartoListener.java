package com.example.tophotels.listeners;

import com.example.tophotels.modelos.ComodidadesQuarto;
import com.example.tophotels.modelos.Quarto;

import java.util.ArrayList;

public interface QuartoListener {
    void onRefreshListaQuarto(ArrayList<Quarto> listaQuartos);
    void onLoadDetalhesQuarto(Quarto quarto);
    void onLoadComodidadesQuarto(ArrayList<ComodidadesQuarto> listaComodidadesQuarto);
}
