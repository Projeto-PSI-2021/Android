package com.example.tophotels.vistas;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.tophotels.R;
import com.example.tophotels.adaptadores.RegiaoAdapter;
import com.example.tophotels.listeners.RegiaoListener;
import com.example.tophotels.modelos.Regiao;
import com.example.tophotels.modelos.Singleton;

import java.util.ArrayList;
import java.util.List;


public class PesquisarHotelFragment extends Fragment implements RegiaoListener {
    EditText etDataInicial, etDataFinal;
    AutoCompleteTextView actvRegiao;

    // aceder a sharedpreference
    public static final String PESQUISA_HOTEL = "PESQUISA_HOTEL";
    // data_inicial
    public static final String DATA_INICIAL = "DATA_INICIAL";
    // data_final
    public static final String DATA_FINAL = "DATA_FINAL";

    public PesquisarHotelFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pesquisar_hotel, container, false);

        actvRegiao = (AutoCompleteTextView) view.findViewById(R.id.actvRegiaoPesquisa);
        etDataInicial = view.findViewById(R.id.etDataInicialPesquisa);
        etDataFinal = view.findViewById(R.id.etDataFinalPesquisa);

        SharedPreferences sharedPreferencesUser = this.getActivity().getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
        // tabela user.access_token string
        String token = sharedPreferencesUser.getString(MenuMainActivity.TOKEN, null);





        Button btPesquisar = view.findViewById(R.id.btPesquisar);
        btPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSharedPreferences(etDataInicial.getText().toString(), etDataFinal.getText().toString());
                ListaHoteisFragment listaHoteisFragment = ListaHoteisFragment.newInstance(actvRegiao.getText().toString(), etDataInicial.getText().toString(), etDataFinal.getText().toString());
                getFragmentManager().beginTransaction().replace(R.id.contentFragment, listaHoteisFragment).commit();
            }
        });

        Singleton.getInstance(getContext()).setRegiaoListener(this);
        Singleton.getInstance(getContext()).getListaRegioesAPI(getContext(), token);

        return view;
    }

    public void saveSharedPreferences(String data_inicial, String data_final) {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(PesquisarHotelFragment.PESQUISA_HOTEL, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PesquisarHotelFragment.DATA_INICIAL, data_inicial);
        editor.putString(PesquisarHotelFragment.DATA_FINAL, data_final);
        editor.apply();
    }

    @Override
    public void onRefreshListaRegiao(ArrayList<Regiao> listaRegioes) {
        if (listaRegioes != null) {
            List<Regiao> regioes = new ArrayList<>(listaRegioes);
            RegiaoAdapter adapter = new RegiaoAdapter(getContext(), R.layout.fragment_pesquisar_hotel, R.id.tvRegiaoAutoComplete, regioes);
            actvRegiao.setAdapter(adapter);
        }
    }
}