package com.example.tophotels.modelos;

public class User {
    private int id;
    private String username;
    private String email;
    private String access_token;

    public User(int id, String username, String email, String access_token) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.access_token = access_token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
