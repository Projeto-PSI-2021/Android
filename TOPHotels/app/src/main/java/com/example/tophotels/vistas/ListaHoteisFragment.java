package com.example.tophotels.vistas;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tophotels.R;
import com.example.tophotels.listeners.HotelListener;
import com.example.tophotels.modelos.Hotel;
import com.example.tophotels.modelos.SingletonHotel;
import com.example.tophotels.adaptadores.ListaHotelAdapter;

import java.util.ArrayList;

public class ListaHoteisFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, HotelListener {
    private ListaHotelAdapter adapter;
    private ListView lvHoteis;


    public ListaHoteisFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_hoteis, container, false);

        lvHoteis = view.findViewById(R.id.lvHoteis);

        SingletonHotel gestor = SingletonHotel.getInstance(getContext());

        adapter = new ListaHotelAdapter(getActivity(), gestor.getListaHoteis());

        lvHoteis.setAdapter(adapter);

        //Vai escutar até clicar na imagem
        lvHoteis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detalhe = new Intent(getActivity().getApplicationContext(), HotelSelecionadoActivity.class);
                //detalhe.putExtra(DetalhesLivros.INDICE, position);
                //detalhe.putExtra(HotelSelecionadoActivity.ID, id);
                startActivity(detalhe);
            }
        });

        SingletonHotel.getInstance(getContext()).setHotelListener(this);
        SingletonHotel.getInstance(getContext()).getAllHotelAPI(getContext());


        return view;
    }


    @Override
    public void onRefreshListaHotel(ArrayList<Hotel> listahoteis) {
        if(listahoteis != null){
            lvHoteis.setAdapter(new ListaHotelAdapter(getContext(), listahoteis));
        }
    }

    @Override
    public void onRefresh() {
        lvHoteis.setAdapter(new ListaHotelAdapter(getContext(),
        SingletonHotel.getInstance(getContext()).getListaHoteis()));
    }

    @Override
    public void onRefreshDetalhes() {

    }
}