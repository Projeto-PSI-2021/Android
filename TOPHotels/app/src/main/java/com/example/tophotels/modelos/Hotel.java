package com.example.tophotels.modelos;


public class Hotel {
    private int id;
    private int capa;
    private String nomeHotel;
    private String localidade;

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    private double preco;

    public Hotel (int id, int capa, String nomeHotel, String Localidade, double preco){
        this.id = id;
        this.capa = capa;
        this.nomeHotel = nomeHotel;
        this.localidade = Localidade;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapa() {
        return capa;
    }

    public void setCapa(int capa) {
        this.capa = capa;
    }

    public String getNomeHotel() {
        return nomeHotel;
    }

    public void setNomeHotel(String nomeHotel) {
        this.nomeHotel = nomeHotel;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }


}
