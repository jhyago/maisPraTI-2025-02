package com.t2.apiexample.auth.dto;

public class AuthResponse {
    private String token;
    private String tokenType = "Bearer";

    public AuthResponse() {}
    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getTokenType() {
        return tokenType;
    }
}
