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
import com.example.tophotels.modelos.Hotel;
import com.example.tophotels.R;

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
        ViewHolderLivro vHolder = (ViewHolderLivro) convertView.getTag();

        if(vHolder == null){
            vHolder = new ViewHolderLivro(convertView);
            convertView.setTag(vHolder);
        }

        vHolder.update(this.listaHoteis.get(position));

        return convertView;
    }

    private class ViewHolderLivro{
        private TextView tvNomeHotel, tvLocalidade, tvPreco;
        private ImageView img;

        public ViewHolderLivro(View view){
            tvNomeHotel = view.findViewById(R.id.tvNomeHotel);
            tvLocalidade = view.findViewById(R.id.tvLocalidade);
            tvPreco = view.findViewById(R.id.tvPreco);
            img = view.findViewById(R.id.tvImagem);
        }

        public void update(Hotel hotel){
            this.tvNomeHotel.setText(hotel.getNome());
            this.tvLocalidade.setText(hotel.getLocalidade());
            //this.inCapa.setImageResource(hotel.getCapa());
            Glide.with(contexto)
                .load(hotel.getImg())
                    .placeholder(R.drawable.hotel)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img);
        }
    }
}

