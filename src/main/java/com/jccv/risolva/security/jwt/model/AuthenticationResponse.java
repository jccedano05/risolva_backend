package com.jccv.risolva.security.jwt.model;


public class AuthenticationResponse {

    final private String token;

    public AuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
