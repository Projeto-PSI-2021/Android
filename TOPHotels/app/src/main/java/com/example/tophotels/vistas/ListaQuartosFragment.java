package com.example.tophotels.vistas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tophotels.R;
import com.example.tophotels.listeners.QuartoListener;
import com.example.tophotels.modelos.Quarto;
import com.example.tophotels.modelos.Singleton;
import com.example.tophotels.adaptadores.ListaQuartoAdapter;

import java.util.ArrayList;

public class ListaQuartosFragment extends Fragment implements QuartoListener {
    private ListaQuartoAdapter adapter;
    private ListView lvQuartos;


    public ListaQuartosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_quartos, container, false);
        lvQuartos = view.findViewById(R.id.lvQuartos);

        String localidade = getArguments().getString("localidade", "");
        String data_inicial = getArguments().getString("data_inicial", "");
        String data_final = getArguments().getString("data_final", "");

        SharedPreferences sharedPreferencesUser = this.getActivity().getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
        // tabela user.access_token string
        String token = sharedPreferencesUser.getString(MenuMainActivity.TOKEN, null);

        Singleton.getInstance(getContext()).setQuartoListener(this);
        Singleton.getInstance(getContext()).postPesquisaHotel(getContext(), localidade, data_inicial, data_final, token);

        adapter = new ListaQuartoAdapter(getActivity(), Singleton.getInstance(getContext()).getListaQuartos());
        lvQuartos.setAdapter(adapter);
        //Vai escutar até clicar na imagem
        lvQuartos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detalhe = new Intent(getActivity().getApplicationContext(), QuartoSelecionadoActivity.class);
                //detalhe.putExtra(DetalhesLivros.INDICE, position);
                //detalhe.putExtra(HotelSelecionadoActivity.ID, id);
                detalhe.putExtra(QuartoSelecionadoActivity.ID, id);
                startActivity(detalhe);
            }
        });

        return view;
    }

    public static ListaQuartosFragment newInstance(String localidade, String data_inicial, String data_final) {
        ListaQuartosFragment listaQuartosFragment = new ListaQuartosFragment();
        Bundle args = new Bundle();
        args.putString("localidade", localidade);
        args.putString("data_inicial", data_inicial);
        args.putString("data_final", data_final);
        listaQuartosFragment.setArguments(args);
        return listaQuartosFragment;
    }

    @Override
    public void onRefreshListaQuarto(ArrayList<Quarto> listaQuartos) {
        if (listaQuartos != null) {
            lvQuartos.setAdapter(new ListaQuartoAdapter(getContext(), listaQuartos));
        }
    }
}