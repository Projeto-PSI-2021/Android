package com.example.tophotels;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class AppController extends Application {
    public static final String TAG = Application.class.getSimpleName();
    private static AppController hInstance;
    private RequestQueue queue;

    @Override
    public void onCreate() {
        super.onCreate();
        hInstance = this;
    }

    public static synchronized AppController gethInstance() {
        return hInstance;
    }

    public RequestQueue getRequestQueue() {
        if (queue == null) {
            queue = Volley.newRequestQueue(getApplicationContext());
        }
        return queue;
    }

    public void addToQueue(Request req, String tag) {
        if (isLigadoInternet()) {
            // DEFINIR A TAG PREDEFINIDA SE ESTIVER VAZIA
            req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
            getRequestQueue().add(req);
        } else {
            Toast.makeText(getApplicationContext(), "Erro de ligação! Não está ligado à internet", Toast.LENGTH_SHORT).show();
        }
    }

    public void addToQueue(Request req) {
        if (isLigadoInternet()) {
            req.setTag(TAG);
            getRequestQueue().add(req);
        } else {
            Toast.makeText(getApplicationContext(), "Erro de ligação! Não está ligado à internet", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelarPedidosPendentes(Object tag) {
        if (isLigadoInternet()) {
            if (queue != null) {
                queue.cancelAll(tag);
            }
        } else {
            Toast.makeText(getApplicationContext(), "Erro de ligação! Não está ligado à internet", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isLigadoInternet() {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        boolean isLigado = activeNetwork != null && activeNetwork.isConnected();
        return isLigado;
    }
}