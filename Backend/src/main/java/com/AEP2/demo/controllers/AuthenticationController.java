package com.AEP2.demo.controllers;

import com.AEP2.demo.DTO.*;
import com.AEP2.demo.models.User;
import com.AEP2.demo.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        User user = (User) auth.getPrincipal();
        return ResponseEntity.ok(new LoginResponseDTO(user.getRole().toString(), user.getNome()));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO data) {
        if (this.userRepository.findByLogin(data.login()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = passwordEncoder.encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role(), data.nome());
        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/dados/{login}")
    public ResponseEntity<LoginResponseDTO> buscarDados(@PathVariable String login) {
        User user = (User) userRepository.findByLogin(login);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new LoginResponseDTO(user.getRole().toString(), user.getNome()));
    }

    @PutMapping("/dados/{login}")
    public ResponseEntity<Void> atualizarDados(
            @PathVariable String login,
            @RequestBody AtualizarDadosDTO data) {

        User user = (User) userRepository.findByLogin(login);
        if (user == null) return ResponseEntity.notFound().build();

        user.setNome(data.nome());

        if (data.novaSenha() != null && !data.novaSenha().isBlank()) {
            user.setPassword(passwordEncoder.encode(data.novaSenha()));
        }

        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/dados/{login}")
    public ResponseEntity<Void> excluirConta(@PathVariable String login) {
        User user = (User) userRepository.findByLogin(login);
        if (user == null) return ResponseEntity.notFound().build();
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }
}
