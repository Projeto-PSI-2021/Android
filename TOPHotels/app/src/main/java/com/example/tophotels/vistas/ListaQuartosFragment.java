package com.example.tophotels.vistas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.tophotels.R;
import com.example.tophotels.adaptadores.ListaHotelAdapter;
import com.example.tophotels.adaptadores.ListaQuartosAdapter;
import com.example.tophotels.adaptadores.ListaReservaAdapter;
import com.example.tophotels.listeners.QuartoListener;
import com.example.tophotels.modelos.Quarto;
import com.example.tophotels.modelos.Singleton;

import java.util.ArrayList;

public class ListaQuartosFragment extends Fragment implements QuartoListener {


    private ListaQuartosAdapter adapter;
    private ListView lvQuartos;

    public static ListaQuartosFragment newInstance(int hotelId, String data_inicial, String data_final) {
        ListaQuartosFragment listaQuartosFragment = new ListaQuartosFragment();
        Bundle args = new Bundle();
        args.putInt("hotel_id", hotelId);
        args.putString("data_inicial", data_inicial);
        args.putString("data_final", data_final);
        listaQuartosFragment.setArguments(args);
        return listaQuartosFragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_quartos, container, false);
        lvQuartos = view.findViewById(R.id.lvQuartos);

        int hotelId = getArguments().getInt("hotel_id", 0);
        String data_inicial = getArguments().getString("data_inicial", "");
        String data_final = getArguments().getString("data_final", "");

        SharedPreferences sharedPreferencesUser = this.getActivity().getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
        // tabela user.access_token string
        String token = sharedPreferencesUser.getString(MenuMainActivity.TOKEN, null);

        Singleton.getInstance(getContext()).setQuartoListener(this);
        Singleton.getInstance(getContext()).postPesquisaQuartos(getContext(), hotelId, data_inicial, data_final, token);

        adapter = new ListaQuartosAdapter(getActivity(), Singleton.getInstance(getContext()).getListaQuartos());
        lvQuartos.setAdapter(adapter);

        return view;
    }

    @Override
    public void onRefreshListaQuarto(ArrayList<Quarto> listaQuartos) {
        if (listaQuartos != null) {
            lvQuartos.setAdapter(new ListaQuartosAdapter(getContext(), listaQuartos));
        }
    }
}
