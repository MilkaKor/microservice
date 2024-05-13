package com.courseproject.demo.controllers;

/**
 * Класс LoginResponse представляет объект ответа на аутентификацию, содержащий токен и время его действия.
 *
 * <p>
 * Автор: Korchanova
 * Версия: 1.0
 * </p>
 */
public class LoginResponse {
    private String token;

    private long expiresIn;

    /**
     * Получить токен.
     *
     * @return Токен.
     */
    public String getToken() {
        return token;
    }

    /**
     * Установить токен.
     *
     * @param jwtToken Токен JWT.
     */
    public void setToken(String jwtToken) {
        this.token = jwtToken;
    }

    /**
     * Установить время действия токена.
     *
     * @param expirationTime Время действия токена.
     */
    public void setExpiresIn(long expirationTime) {
        this.expiresIn = expirationTime;
    }
}