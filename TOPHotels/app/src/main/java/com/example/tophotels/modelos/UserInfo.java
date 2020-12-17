package com.example.tophotels.modelos;

public class UserInfo {
    private int id;
    private String nome;
    private String apelido;
    private int contactoTel;
    private String morada;
    private int nif;
    private String img;
    private int userId;

    public UserInfo(int id, String nome, String apelido, int contactoTel, String morada, int nif, String img, int userId) {
        this.id = id;
        this.nome = nome;
        this.apelido = apelido;
        this.contactoTel = contactoTel;
        this.morada = morada;
        this.nif = nif;
        this.img = img;
        this.userId = userId;
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

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public int getContactoTel() {
        return contactoTel;
    }

    public void setContactoTel(int contactoTel) {
        this.contactoTel = contactoTel;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
