package com.example.tophotels.vistas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.tophotels.R;


public class PesquisarHotelFragment extends Fragment {
    EditText etDataInicial, etDataFinal, etLocalidade;

    // tabela user.access_token string
    private String token;

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

        etLocalidade = view.findViewById(R.id.etLocalidadePesquisa);
        etDataInicial = view.findViewById(R.id.etDataInicial);
        etDataFinal = view.findViewById(R.id.etDataFinal);

        etLocalidade.setText("Torres Vedras");
        etDataInicial.setText("2020-12-02");
        etDataFinal.setText("2020-12-05");

        Button btPesquisar = (Button) view.findViewById(R.id.btPesquisar);
        btPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Singleton.getInstance(getContext()).postPesquisaHotel(getContext(), etLocalidade.getText().toString(), etDataInicial.getText().toString(), etDataFinal.getText().toString(), token);
                ListaQuartosFragment listaQuartosFragment = ListaQuartosFragment.newInstance(etLocalidade.getText().toString(), etDataInicial.getText().toString(), etDataFinal.getText().toString());
                getFragmentManager().beginTransaction().replace(R.id.contentFragment, listaQuartosFragment).commit();
            }
        });

        return view;
    }
}