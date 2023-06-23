package com.consumo.api.model.dto;


public class UsersDTO {
    private String username;
    private String password;
    public UsersDTO(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}