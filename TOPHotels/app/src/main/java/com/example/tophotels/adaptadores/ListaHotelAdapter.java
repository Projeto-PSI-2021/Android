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

import java.util.ArrayList;

public class ListaHotelAdapter extends BaseAdapter {

    private Context contexto;
    private LayoutInflater inflater;
    private ArrayList<Hotel> listaHoteis;

    public ListaHotelAdapter(Context contexto, ArrayList<Hotel> lista){
        this.contexto = contexto;
        this.listaHoteis = lista;
    }

    @Override
    public int getCount() {
        return this.listaHoteis.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listaHoteis.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.listaHoteis.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null){
            this.inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_lista_hoteis, null);

        }
        ViewHolderHotel vHolder = (ViewHolderHotel) convertView.getTag();

        if(vHolder == null){
            vHolder = new ViewHolderHotel(convertView);
            convertView.setTag(vHolder);
        }

        vHolder.update(this.listaHoteis.get(position));

        return convertView;
    }

    private class ViewHolderHotel{
        private TextView tvNome, tvDescricao, tvPreco;
        private ImageView img;

        public ViewHolderHotel(View view){
            tvNome = view.findViewById(R.id.tvNomeHotel);
            tvDescricao = view.findViewById(R.id.tvDescricaoHotelPesquisa);
            tvPreco = view.findViewById(R.id.tvPrecoHotelPesquisa);
            img = view.findViewById(R.id.imgListaQuartos);
        }

        public void update(Hotel hotel){
            this.tvNome.setText(hotel.getNome());
            this.tvDescricao.setText(hotel.getDescricao());
            this.tvPreco.setText("Preços: "+hotel.getPreco_min()+ "€ - "+hotel.getPreco_max()+"€");
            Glide.with(contexto)
                .load(hotel.getImg())
                    .placeholder(R.drawable.hotel)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img);
        }
    }
}

