package com.example.tophotels.vistas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.tophotels.R;
import com.example.tophotels.listeners.ReservaListener;
import com.example.tophotels.listeners.UserListener;
import com.example.tophotels.modelos.Singleton;
import com.example.tophotels.utils.JsonParser;

public class DialogCartao extends AppCompatDialogFragment {
    private EditText etNrCartao, etMM, etYY, etCVC;
    public String dataCheckin, dataCheckout;
    public double preco;
    public int nrPessoas, quartoId, userId;


    private String nrCartaoValido = "4242-4242-4242-4242";
    private String nrCartaoValidoMM = "10";
    private String nrCartaoValidoYY = "22";
    private String nrCartaoValidoCVC = "332";

    private String nrCartaoNoMoney = "4343-4343-4343-4343";
    private String nrCartaoExpirado = "4545-4545-4545-4545";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_pagamento, null);

        etMM = view.findViewById(R.id.etMMCartao);
        etYY = view.findViewById(R.id.etYYCartao);
        etCVC = view.findViewById(R.id.etCVCCartao);



        builder.setView(view)
                .setTitle("Detalhes do cartão")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Efetuar pagamento", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!JsonParser.isConnectionInternet(getActivity().getApplicationContext())) {
                            Toast.makeText(getActivity().getApplicationContext(), "Não está ligado à internet.", Toast.LENGTH_SHORT).show();
                        } else {
                            if (etNrCartao.getText().toString().matches("") || etMM.getText().toString().matches("")
                                    || etYY.getText().toString().matches("") || etCVC.getText().toString().matches("")) {
                                Toast.makeText(getActivity().getApplicationContext(), "Preencha todos os campos.", Toast.LENGTH_SHORT).show();

                            } else {
                                if (etNrCartao.getText().toString().equals(nrCartaoValido)) {
                                    if (etMM.getText().toString().equals(nrCartaoValidoMM)) {
                                        if (etYY.getText().toString().equals(nrCartaoValidoYY)) {
                                            if (etCVC.getText().toString().equals(nrCartaoValidoCVC)) {
                                                Singleton.getInstance(getActivity().getApplicationContext()).postCriarReservaAPI(getActivity().getApplicationContext(),
                                                        nrPessoas, preco, dataCheckin, dataCheckout, quartoId, userId);

                                            } else {
                                                Toast.makeText(getActivity().getApplicationContext(), "O código CVC inserido está incorreto.", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            Toast.makeText(getActivity().getApplicationContext(), "O ano inserido está incorreto.", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(getActivity().getApplicationContext(), "O mês inserido está incorreto.", Toast.LENGTH_SHORT).show();
                                    }
                                } else if (etNrCartao.getText().toString().equals(nrCartaoNoMoney)) {
                                    Toast.makeText(getActivity().getApplicationContext(), "O cartão não possui dinheiro.", Toast.LENGTH_SHORT).show();
                                } else if (etNrCartao.getText().toString().equals(nrCartaoExpirado)) {
                                    Toast.makeText(getActivity().getApplicationContext(), "O cartão inserido expirou.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getActivity().getApplicationContext(), "Erro ao processar pedido", Toast.LENGTH_SHORT).show();
                                }
                            }


                        }
                    }
                });

        etNrCartao = view.findViewById(R.id.etNumeroCartao);

        return builder.create();
    }




}
