package com.example.tophotels.vistas;

import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.tophotels.adaptadores.ListaQuartosAdapter;
import com.example.tophotels.adaptadores.ListaReservaAdapter;
import com.example.tophotels.listeners.QuartoListener;
import com.example.tophotels.modelos.Quarto;

import java.util.ArrayList;

public class ListaQuartosFragment extends Fragment implements QuartoListener {

    private ListaQuartosAdapter adapter;
    private ListView lvQuartos;

    @Override
    public void onRefreshListaQuarto(ArrayList<Quarto> listaQuartos) {
        if (listaQuartos != null) {
            lvQuartos.setAdapter(new ListaQuartosAdapter(getContext(), listaQuartos));
        }
    }
}
