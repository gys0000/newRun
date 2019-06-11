package com.gystry.common.net;

public class TokenManager {
    private String token;
    private static TokenManager tokenManager;

    private TokenManager() {

    }

    public static TokenManager getInstance() {
        if (tokenManager == null) {
            synchronized (TokenManager.class) {
                if (tokenManager == null) {
                    tokenManager = new TokenManager();
                }
            }
        }
        return tokenManager;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
