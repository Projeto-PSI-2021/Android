package com.example.tophotels.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.tophotels.R;
import com.example.tophotels.modelos.Hotel;
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
        return this.listaQuartos.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listaQuartos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.listaQuartos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null){
            this.inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_lista_quartos, null);

        }
        ViewHolderQuarto vHolder = (ViewHolderQuarto) convertView.getTag();

        if(vHolder == null){
            vHolder = new ViewHolderQuarto(convertView);
            convertView.setTag(vHolder);
        }

        vHolder.update(this.listaQuartos.get(position));

        return convertView;
    }

    private class ViewHolderQuarto{
        private TextView tvNomeQuarto, tvPrecoQuarto;
        private ImageView img;

        public ViewHolderQuarto(View view){
            tvNomeQuarto = view.findViewById(R.id.tvNomeQuarto);
            tvPrecoQuarto = view.findViewById(R.id.tvPrecoNoite);
            img = view.findViewById(R.id.imgListaQuartos);
        }

        public void update(Quarto quarto){
            this.tvNomeQuarto.setText(quarto.getDescricao());
            this.tvPrecoQuarto.setText("Preço: " + quarto.getPrecoNoite() + " €");
            Glide.with(contexto)
                    .load(quarto.getImg())
                    .placeholder(R.drawable.hotel)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img);
        }
    }
}

