package com.consumo.api.client;

import com.consumo.api.util.Endpoints;

import java.util.Map;

public class JwtClient extends RequestHandler{
    public JwtClient(){

    }
    public <T> String sendAuthenticationRequest(T serializeObject){
        Map<String, String> mapToken = extractToken(serializeObject, Endpoints.getAUTHENTICATION_URL(), Endpoints.getContentType());
        String token = mapToken.get("token");
        return token;
    }
}
