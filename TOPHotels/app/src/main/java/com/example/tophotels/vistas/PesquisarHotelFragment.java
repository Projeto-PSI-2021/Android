package com.example.tophotels.vistas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tophotels.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PesquisarHotelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
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

        return view;

    }

    private void pesquisarHotel(View view){
        Fragment frag = new ListaHoteisFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contentFragment, frag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}