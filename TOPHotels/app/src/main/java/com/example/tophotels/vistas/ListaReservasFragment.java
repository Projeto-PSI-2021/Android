package com.example.tophotels.vistas;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.tophotels.R;
import com.example.tophotels.adaptadores.ListaReservaAdapter;
import com.example.tophotels.listeners.UserInfoListener;
import com.example.tophotels.modelos.Reserva;
import com.example.tophotels.modelos.Singleton;
import com.example.tophotels.modelos.UserInfo;

import java.util.ArrayList;

public class ListaReservasFragment extends Fragment implements UserInfoListener {
    private ListaReservaAdapter adapter;
    private ListView lvReservas;


    public ListaReservasFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_reservas, container, false);

        lvReservas = view.findViewById(R.id.lvReservas);

        SharedPreferences sharedPreferencesUser = this.getActivity().getSharedPreferences(MenuMainActivity.PREF_USER, Context.MODE_PRIVATE);
        // tabela user.access_token string
        String token = sharedPreferencesUser.getString(MenuMainActivity.TOKEN, null);
        // tabela user.id int
        int user_id = sharedPreferencesUser.getInt(MenuMainActivity.USER_ID, 0);

        Singleton.getInstance(getContext()).setUserInfoListener(this);
        Singleton.getInstance(getContext()).getListaReservasAPI(getContext(), token, user_id);

        adapter = new ListaReservaAdapter(getActivity(), Singleton.getInstance(getContext()).getListaReservas());
        lvReservas.setAdapter(adapter);

        return view;
    }

    @Override
    public void onRefreshDetalhes(UserInfo userInfo) {

    }

    @Override
    public void onUpdateDetalhes(UserInfo userInfo) {

    }

    @Override
    public void onRefreshListaReserva(ArrayList<Reserva> listareservas) {
        if (listareservas != null) {
            lvReservas.setAdapter(new ListaReservaAdapter(getContext(), listareservas));
        }
    }
}