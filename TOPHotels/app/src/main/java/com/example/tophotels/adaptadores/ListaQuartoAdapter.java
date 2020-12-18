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
import com.example.tophotels.modelos.Quarto;

import java.util.ArrayList;

public class ListaQuartoAdapter extends BaseAdapter {

    private Context contexto;
    private LayoutInflater inflater;
    private ArrayList<Quarto> listaQuartos;

    public ListaQuartoAdapter(Context contexto, ArrayList<Quarto> lista){
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
            convertView = inflater.inflate(R.layout.item_lista_hoteis, null);

        }
        ViewHolderLivro vHolder = (ViewHolderLivro) convertView.getTag();

        if(vHolder == null){
            vHolder = new ViewHolderLivro(convertView);
            convertView.setTag(vHolder);
        }

        vHolder.update(this.listaQuartos.get(position));

        return convertView;
    }

    private class ViewHolderLivro{
        private TextView tvDescricaoQuarto, tvPrecoNoite;
        private ImageView img;

        public ViewHolderLivro(View view){
            tvDescricaoQuarto = view.findViewById(R.id.tvDescricaoQuarto);
            tvPrecoNoite = view.findViewById(R.id.tvPrecoNoite);
            img = view.findViewById(R.id.tvImagem);
        }

        public void update(Quarto quarto){
            this.tvDescricaoQuarto.setText(quarto.getDescricao());
            this.tvPrecoNoite.setText(""+quarto.getPrecoNoite());
            //this.inCapa.setImageResource(hotel.getCapa());
            Glide.with(contexto)
                .load(quarto.getImg())
                    .placeholder(R.drawable.hotel)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img);
        }
    }
}

