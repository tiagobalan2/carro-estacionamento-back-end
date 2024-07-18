package com.example.projeto_carros.controllers;

import com.example.projeto_carros.domain.user.AuthenticationDTO;
import com.example.projeto_carros.domain.user.LoginResponseDTO;
import com.example.projeto_carros.domain.user.RegistroDTO;
import com.example.projeto_carros.domain.user.User;
import com.example.projeto_carros.infra.security.TokenService;
import com.example.projeto_carros.repositories.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO body) {
        var UsernamePassword = new UsernamePasswordAuthenticationToken(body.login(), body.password());
        var auth = this.authenticationManager.authenticate(UsernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/registro")
    public ResponseEntity registro(@RequestBody @Valid RegistroDTO body) {
        if (this.userRepository.findByLogin(body.login()) != null)
            return ResponseEntity.badRequest().build();

            String encryptedPassword = new BCryptPasswordEncoder().encode(body.password());
            User newUser = new User(body.login(), encryptedPassword, body.role());

            this.userRepository.save(newUser);
            return ResponseEntity.ok().build();
        }
}



