package com.example.tophotels.listeners;

import com.example.tophotels.modelos.Reserva;
import com.example.tophotels.modelos.UserInfo;

import java.util.ArrayList;

public interface UserInfoListener {

    void onRefreshDetalhes(UserInfo userInfo);
    void onUpdateDetalhes(UserInfo userInfo);
    void onRefreshListaReserva(ArrayList<Reserva> listareservas);
}
