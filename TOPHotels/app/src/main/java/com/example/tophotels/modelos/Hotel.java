package com.example.tophotels.modelos;


public class Hotel {
    private int id;
    private String nome;
    private String proprietario;
    private String descricao;
    private int contacto;
    private String website;
    private int cp4;
    private int cp3;
    private String localidade;
    private String morada;
    private int estado;
    private int img;

    public Hotel(int id, String nome, String proprietario, String descricao, int contacto, String website, int cp4, int cp3, String localidade, String morada, int estado) {
        this.id = id;
        this.nome = nome;
        this.proprietario = proprietario;
        this.descricao = descricao;
        this.contacto = contacto;
        this.website = website;
        this.cp4 = cp4;
        this.cp3 = cp3;
        this.localidade = localidade;
        this.morada = morada;
        this.estado = estado;
        //this.img = img;
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

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
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

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
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

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
