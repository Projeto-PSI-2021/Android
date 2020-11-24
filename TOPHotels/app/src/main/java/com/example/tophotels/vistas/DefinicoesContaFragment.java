package com.example.tophotels.vistas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tophotels.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DefinicoesContaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DefinicoesContaFragment extends Fragment {

    public DefinicoesContaFragment() {
        // Required empty public constructor
    }

    public static DefinicoesContaFragment newInstance(String param1, String param2) {
        DefinicoesContaFragment fragment = new DefinicoesContaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_definicoes_conta, container, false);
    }
}