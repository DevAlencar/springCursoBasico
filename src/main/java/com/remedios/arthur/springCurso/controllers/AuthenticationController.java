package com.remedios.arthur.springCurso.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.remedios.arthur.springCurso.infra.DadosTokenJWT;
import com.remedios.arthur.springCurso.infra.TokenService;
import com.remedios.arthur.springCurso.users.DadosAutenticacao;
import com.remedios.arthur.springCurso.users.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private TokenService tokenService;

	// metodo de autenticacao para login de usuario
	// é necessario um DTO para os dados de usuario, e apos, passar para o DTO do
	// manager, para que o spring entenda, o token
	@PostMapping
	public ResponseEntity<?> login(@RequestBody @Valid DadosAutenticacao dados) {
		var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.password());
		var authentication = manager.authenticate(token);
		
		var tokenJWT = tokenService.tokenGenerator((User) authentication.getPrincipal());

		// apos login, retorna o jwt para efetivar o login;
		// manda o usuario para o tokenGenerator usando o DTO do spring
		// authentication.getPrincipal;
		return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
	}

}
