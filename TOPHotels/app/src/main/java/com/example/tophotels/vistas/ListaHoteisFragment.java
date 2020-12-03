package com.example.tophotels.vistas;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.tophotels.AppController;
import com.example.tophotels.R;
import com.example.tophotels.modelos.SingletonHotel;
import com.example.tophotels.adaptadores.ListaHotelAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ListaHoteisFragment extends Fragment {
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

        SingletonHotel gestor = SingletonHotel.getInstance();

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
        return view;
    }
}