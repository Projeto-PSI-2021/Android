package com.example.tophotels.modelos;

public class Hotel {
    private int id;
    private String nome;
    private String descricao;
    private int contacto;
    private String website;
    private int cp4;
    private int cp3;
    private String morada;
    private int estado;
    private double preco_min;
    private double preco_max;
    private String img;

    public Hotel(int id, String nome, String descricao, int contacto, String website, int cp4, int cp3, String morada, int estado, double preco_min, double preco_max, String img) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.contacto = contacto;
        this.website = website;
        this.cp4 = cp4;
        this.cp3 = cp3;
        this.morada = morada;
        this.estado = estado;
        this.preco_min = preco_min;
        this.preco_max = preco_max;
        this.img = img;
    }

    public Hotel(int id, String nome, String descricao, int contacto, String website, int cp4, int cp3, String morada, String img) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.contacto = contacto;
        this.website = website;
        this.cp4 = cp4;
        this.cp3 = cp3;
        this.morada = morada;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getContacto() {
        return contacto;
    }

    public void setContacto(int contacto) {
        this.contacto = contacto;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getCp4() {
        return cp4;
    }

    public void setCp4(int cp4) {
        this.cp4 = cp4;
    }

    public int getCp3() {
        return cp3;
    }

    public void setCp3(int cp3) {
        this.cp3 = cp3;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public double getPreco_min() {
        return preco_min;
    }

    public void setPreco_min(double preco_min) {
        this.preco_min = preco_min;
    }

    public double getPreco_max() {
        return preco_max;
    }

    public void setPreco_max(double preco_max) {
        this.preco_max = preco_max;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
