package com.example.tophotels.vistas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.tophotels.R;
import com.example.tophotels.modelos.SingleTon;
import com.example.tophotels.adaptadores.ListaHotelAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaHoteisFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaHoteisFragment extends Fragment {
    private ListaHotelAdapter adapter;
    private ListView lvLivros;


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

        lvLivros = view.findViewById(R.id.lvHoteis);

        SingleTon gestor = SingleTon.getInstance();

        adapter = new ListaHotelAdapter(getActivity(), gestor.getListaLivros());
        lvLivros.setAdapter(adapter);

        //Vai escutar até clicar na imagem
       /* lvLivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detalhe = new Intent(getActivity().getApplicationContext(), DetalhesLivros.class);
                //detalhe.putExtra(DetalhesLivros.INDICE, position);
                detalhe.putExtra(DetalhesLivros.ID, id);
                startActivity(detalhe);
            }
        });*/
        return view;
    }
}