package com.courseproject.demo.services;

import com.courseproject.demo.dtos.LoginUserDto;
import com.courseproject.demo.dtos.RegisterUserDto;
import com.courseproject.demo.entities.User;
import com.courseproject.demo.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AuthenticationService(

            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User signup(RegisterUserDto input) {
        User user = new User();
        user.setLogin(input.getFullName());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getLogin(),
                        input.getPassword()
                )
        );
        return userRepository.findByLogin(input.getLogin());
    }
}