package com.example.tophotels.listeners;

import com.example.tophotels.modelos.User;

public interface UserListener {

    void onValidateLogin(User user);
    void onValidateRegister(Boolean flag);
    void onForgotPassword(Boolean flag);
}
