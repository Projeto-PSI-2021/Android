package com.example.tophotels.listeners;

import com.example.tophotels.modelos.UserInfo;

public interface UserInfoListener {

    void onRefreshDetalhes(UserInfo userInfo);
    void onUpdateDetalhes(UserInfo userInfo);
}
