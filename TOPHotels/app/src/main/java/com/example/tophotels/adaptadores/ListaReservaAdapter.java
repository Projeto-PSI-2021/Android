package com.example.tophotels.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tophotels.R;
import com.example.tophotels.modelos.Reserva;

import java.util.ArrayList;

public class ListaReservaAdapter extends BaseAdapter {

    private Context contexto;
    private LayoutInflater inflater;
    private ArrayList<Reserva> listaReservas;

    public ListaReservaAdapter(Context contexto, ArrayList<Reserva> lista){
        this.contexto = contexto;
        this.listaReservas = lista;
    }

    @Override
    public int getCount() {
        return this.listaReservas.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listaReservas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.listaReservas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null){
            this.inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_lista_reservas, null);

        }
        ViewHolderReserva vHolder = (ViewHolderReserva) convertView.getTag();

        if(vHolder == null){
            vHolder = new ViewHolderReserva(convertView);
            convertView.setTag(vHolder);
        }

        vHolder.update(this.listaReservas.get(position));

        return convertView;
    }

    private class ViewHolderReserva{
        private TextView tvNumPessoas, tvPreco, tvDataCheckIn, tvDataCheckOut, tvEstado, tvHotel;

        public ViewHolderReserva(View view){
            tvNumPessoas = view.findViewById(R.id.tvNumPessoasListaReservas);
            tvPreco = view.findViewById(R.id.tvPrecoListaReservas);
            tvDataCheckIn = view.findViewById(R.id.tvCheckInListaReservas);
            tvDataCheckOut = view.findViewById(R.id.tvCheckOutListaReservas);
            tvEstado = view.findViewById(R.id.tvEstadoListaReservas);
            tvHotel = view.findViewById(R.id.tvHotelDescricaoListaReservas);
        }

        public void update(Reserva reserva){
            this.tvNumPessoas.setText("Nº Pessoas: " + reserva.getnPessoas());
            this.tvPreco.setText("Preço: " + reserva.getPreco() + "€");
            this.tvDataCheckIn.setText("Data Check In: " + reserva.getDataCheckIn());
            this.tvDataCheckOut.setText("Data Check Out: " + reserva.getDataCheckOut());
            if (reserva.getEstado().matches("0")){
                this.tvEstado.setText("Estado: Inativo");
            } else if (reserva.getEstado().matches("1")) {
                this.tvEstado.setText("Estado: Recusado");
            } else if (reserva.getEstado().matches("2")) {
                this.tvEstado.setText("Estado: Pendente");
            } else if (reserva.getEstado().matches("3")) {
                this.tvEstado.setText("Estado: Ativo");
            }
            tvHotel.setText("Hotel: " + reserva.getHotel());
        }
    }
}
