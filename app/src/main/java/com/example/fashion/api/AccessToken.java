package com.example.fashion.api;

public class AccessToken {
    private String access_token;

    public AccessToken(String access_token) {
        this.access_token = access_token;
    }

    public AccessToken() {
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
