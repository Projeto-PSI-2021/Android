package com.example.tophotels.vistas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tophotels.R;


public class PesquisarHotelFragment extends Fragment {

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


        Button btPesquisar = (Button)view.findViewById(R.id.btPesquisar);
        btPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment frag = null;
                frag = new ListaHoteisFragment();
                if (frag != null) {
                    getFragmentManager().beginTransaction().replace(R.id.contentFragment, frag).commit();
                }
            }
        });


        return view;

    }

}