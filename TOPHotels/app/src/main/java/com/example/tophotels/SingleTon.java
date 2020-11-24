package com.example.tophotels;


import java.util.ArrayList;


public class SingleTon {
    private ArrayList<Hotel> listaLivros;
    private static SingleTon instance = null; //Instancia de objeto "static"

    public static synchronized SingleTon getInstance(){
        if(instance==null){
            instance = new SingleTon();
        }
        return instance;
    }

    private SingleTon(){
        this.listaLivros = new ArrayList<>();
        gerarFakeData();
    }

    public Hotel getLivro(long id){
        for(Hotel livro: this.listaLivros){
            if(livro.getId() == id){
                return livro;
            }

        }
        return null;
    }

    private void gerarFakeData() {
        this.listaLivros.add(new Hotel (2, R.drawable.hotel, "Programar ",
                "Torres Vedras", 70));
    }

    public ArrayList<Hotel> getListaLivros() {
        return listaLivros;
    }
}
