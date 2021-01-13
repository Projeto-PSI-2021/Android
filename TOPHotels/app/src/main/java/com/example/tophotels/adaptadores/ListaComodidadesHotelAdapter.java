package com.example.tophotels.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tophotels.R;
import com.example.tophotels.modelos.ComodidadesHotel;
import java.util.ArrayList;

public class ListaComodidadesHotelAdapter extends BaseAdapter {
    private Context contexto;
    private LayoutInflater inflater;
    private ArrayList<ComodidadesHotel> listaComodidadesHotel;

    public ListaComodidadesHotelAdapter(Context contexto, ArrayList<ComodidadesHotel> lista){
        this.contexto = contexto;
        this.listaComodidadesHotel = lista;
    }

    @Override
    public int getCount() {
        return this.listaComodidadesHotel.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listaComodidadesHotel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.listaComodidadesHotel.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null){
            this.inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_lista_comodidades_hotel, null);

        }
        ViewHolderComodidadesHotel vHolder = (ViewHolderComodidadesHotel) convertView.getTag();

        if(vHolder == null){
            vHolder = new ViewHolderComodidadesHotel(convertView);
            convertView.setTag(vHolder);
        }

        vHolder.update(this.listaComodidadesHotel.get(position));

        return convertView;
    }

    private class ViewHolderComodidadesHotel{
        private TextView tvDescricao;

        public ViewHolderComodidadesHotel(View view){
            tvDescricao = view.findViewById(R.id.tvNomeComodidadeHotel);
        }

        public void update(ComodidadesHotel comodidadesHotel){
            this.tvDescricao.setText(comodidadesHotel.getDescricao());

        }
    }
}
