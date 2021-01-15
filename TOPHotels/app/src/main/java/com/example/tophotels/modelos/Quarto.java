package com.example.tophotels.modelos;

public class Quarto {
    private int id;
    private String descricao;
    private double precoNoite;
    private String categoria;
    private String img;

    public Quarto(int id, String descricao, double precoNoite, String categoria, String img) {
        this.id = id;
        this.descricao = descricao;
        this.precoNoite = precoNoite;
        this.categoria = categoria;
        this.img = img;
    }

    public Quarto(int id, String descricao, double precoNoite, String img) {
        this.id = id;
        this.descricao = descricao;
        this.precoNoite = precoNoite;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPrecoNoite() {
        return precoNoite;
    }

    public void setPrecoNoite(double precoNoite) {
        this.precoNoite = precoNoite;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
