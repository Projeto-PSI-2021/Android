package com.example.tophotels.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.tophotels.modelos.Quarto;
import com.example.tophotels.modelos.Reserva;


import java.util.ArrayList;

public class ListaQuartosAdapter extends BaseAdapter {

    private Context contexto;
    private LayoutInflater inflater;
    private ArrayList<Quarto> listaQuartos;


    public ListaQuartosAdapter(Context contexto, ArrayList<Quarto> lista){
        this.contexto = contexto;
        this.listaQuartos = lista;
    }
    
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
