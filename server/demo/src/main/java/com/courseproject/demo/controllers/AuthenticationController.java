package com.courseproject.demo.controllers;

import com.courseproject.demo.dtos.LoginUserDto;
import com.courseproject.demo.dtos.RegisterUserDto;
import com.courseproject.demo.entities.User;
import com.courseproject.demo.services.AuthenticationService;
import com.courseproject.demo.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер аутентификации, обрабатывающий запросы на регистрацию и вход пользователей.
 *
 * <p>
 * Автор: Korchanova
 * Версия: 1.0
 * </p>
 */
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    /**
     * Конструктор контроллера.
     *
     * @param jwtService              Сервис для работы с JWT-токенами.
     * @param authenticationService  Сервис аутентификации пользователей.
     */
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    /**
     * Обрабатывает запрос на регистрацию нового пользователя.
     *
     * @param registerUserDto Данные нового пользователя для регистрации.
     * @return ResponseEntity с зарегистрированным пользователем.
     */
    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    /**
     * Обрабатывает запрос на аутентификацию пользователя.
     *
     * @param loginUserDto Данные пользователя для входа.
     * @return ResponseEntity с JWT-токеном и сроком его действия.
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}