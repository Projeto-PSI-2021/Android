package com.example.tophotels.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tophotels.R;
import com.example.tophotels.modelos.ComodidadesQuarto;

import java.util.ArrayList;

public class ListaComodidadesQuartoAdapter extends BaseAdapter {
    private Context contexto;
    private LayoutInflater inflater;
    private ArrayList<ComodidadesQuarto> listaComodidadesQuarto;

    public ListaComodidadesQuartoAdapter(Context contexto, ArrayList<ComodidadesQuarto> lista){
        this.contexto = contexto;
        this.listaComodidadesQuarto = lista;
    }

    @Override
    public int getCount() {
        return this.listaComodidadesQuarto.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listaComodidadesQuarto.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.listaComodidadesQuarto.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null){
            this.inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_lista_comodidades_quarto, null);

        }
        ViewHolderComodidadesQuarto vHolder = (ViewHolderComodidadesQuarto) convertView.getTag();

        if(vHolder == null){
            vHolder = new ViewHolderComodidadesQuarto(convertView);
            convertView.setTag(vHolder);
        }

        vHolder.update(this.listaComodidadesQuarto.get(position));

        return convertView;
    }

    private class ViewHolderComodidadesQuarto{
        private TextView tvDescricao;

        public ViewHolderComodidadesQuarto(View view){
            tvDescricao = view.findViewById(R.id.tvNomeComodidadeQuarto);
        }

        public void update(ComodidadesQuarto comodidadesQuarto){
            this.tvDescricao.setText(comodidadesQuarto.getDescricao());

        }
    }

}
