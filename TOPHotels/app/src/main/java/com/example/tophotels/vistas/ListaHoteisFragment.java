package com.example.tophotels.vistas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tophotels.R;
import com.example.tophotels.listeners.HotelListener;
import com.example.tophotels.modelos.Hotel;
import com.example.tophotels.modelos.Singleton;
import com.example.tophotels.adaptadores.ListaHotelAdapter;

import java.util.ArrayList;

public class ListaHoteisFragment extends Fragment implements HotelListener {
    private ListaHotelAdapter adapter;
    private ListView lvHoteis;


    public ListaHoteisFragment() {
        // Required empty public constructor
    }

    public static ListaHoteisFragment newInstance(String localidade, String data_inicial, String data_final) {
        ListaHoteisFragment listaHoteisFragment = new ListaHoteisFragment();
        Bundle args = new Bundle();
        args.putString("localidade", localidade);
        args.putString("data_inicial", data_inicial);
        args.putString("data_final", data_final);
        listaHoteisFragment.setArguments(args);
        return listaHoteisFragment;
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

        String localidade = getArguments().getString("localidade", "");
        String data_inicial = getArguments().getString("data_inicial", "");
        String data_final = getArguments().getString("data_final", "");

        SharedPreferences sharedPreferencesUser = this.getActivity().getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
        // tabela user.access_token string
        String token = sharedPreferencesUser.getString(MenuMainActivity.TOKEN, null);

        Singleton.getInstance(getContext()).setHotelListener(this);
        Singleton.getInstance(getContext()).postPesquisaHotel(getContext(), localidade, data_inicial, data_final, token);

        adapter = new ListaHotelAdapter(getActivity(), Singleton.getInstance(getContext()).getListaHoteis());
        lvHoteis.setAdapter(adapter);
        //Vai escutar até clicar na imagem
        lvHoteis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detalhe = new Intent(getActivity().getApplicationContext(), HotelDetalhesActivity.class);
                //detalhe.putExtra(DetalhesLivros.INDICE, position);
                //detalhe.putExtra(HotelSelecionadoActivity.ID, id);
                detalhe.putExtra(HotelDetalhesActivity.ID, id);
                startActivity(detalhe);
            }
        });

        return view;
    }

    @Override
    public void onRefreshListaHotel(ArrayList<Hotel> listahoteis) {
        if (listahoteis != null) {
            lvHoteis.setAdapter(new ListaHotelAdapter(getContext(), listahoteis));
        }
    }

    @Override
    public void onRefreshDetalhes() {

    }
}