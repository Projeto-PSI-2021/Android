package com.example.tophotels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
            this.inflater = (LayoutInflater)contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(convertView == null){
            convertView = inflater.inflate(R.layout.app_bar_main, null);

        }
        ViewHolderLivro vHolder = (ViewHolderLivro) convertView.getTag();

        if(vHolder == null){
            vHolder = new ViewHolderLivro(convertView);
            convertView.setTag(vHolder);
        }

        vHolder.update(this.listaHoteis.get(position));

        return convertView;
    }

    class ViewHolderLivro{
        private TextView tvNomeHotel, tvLocalidade, tvPreco;
        private ImageView inCapa;

        public ViewHolderLivro(View view){
            tvNomeHotel = view.findViewById(R.id.tvNomeHotel);
            tvLocalidade = view.findViewById(R.id.tvLocalidade);
            tvPreco = view.findViewById(R.id.tvPreco);
            inCapa = view.findViewById(R.id.ivCapa);

        }

        public void update(Hotel hotel){
            this.tvNomeHotel.setText(hotel.getNomeHotel());
            this.tvLocalidade.setText(hotel.getLocalidade());
            this.tvPreco.setText(""+hotel.getPreco());
            this.inCapa.setImageResource(hotel.getCapa());
        }
    }
}

