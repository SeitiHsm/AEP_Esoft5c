package com.AEP2.demo.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityFilter {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.csrf(csrf->csrf.disable())
                .sessionManagement(session->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth->
                        auth.requestMatchers(HttpMethod.POST, "/solicitacoes").permitAll()
                                .requestMatchers(HttpMethod.GET,"/solicitacoes").permitAll()
                                .requestMatchers(HttpMethod.DELETE,"/solicitacoes/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/solicitacoes/**").permitAll()
                                .anyRequest().authenticated()
                )
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {

        /*
         * Bean responsável por fornecer o AuthenticationManager.
         *
         * O AuthenticationManager é o componente do Spring Security
         * responsável por realizar o processo de autenticação.
         *
         * Ele verifica:
         * - usuário
         * - senha
         * - permissões
         *
         * Esse objeto geralmente é utilizado no login
         * para validar as credenciais do usuário.
         */

        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        /*
         * Bean responsável por criptografar senhas.
         *
         * BCryptPasswordEncoder utiliza o algoritmo BCrypt,
         * um dos mais seguros para armazenamento de senhas.
         *
         * A senha NÃO fica salva em texto puro no banco.
         *
         * Exemplo:
         * Senha digitada:
         * 123456
         *
         * Senha salva:
         * $2a$10$A8sd9asd...
         *
         * Também é utilizado para comparar a senha digitada
         * com a senha criptografada armazenada no banco.
         */

        return new BCryptPasswordEncoder();
    }
}
