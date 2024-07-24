package com.remedios.arthur.springCurso.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.remedios.arthur.springCurso.users.DadosCadastroUsuario;
import com.remedios.arthur.springCurso.users.User;
import com.remedios.arthur.springCurso.users.UserRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository repository;

	@PostMapping("/newUser")
	@Transactional
	public ResponseEntity<DadosCadastroUsuario> cadastrar(@RequestBody @Valid DadosCadastroUsuario dados,
			UriComponentsBuilder uriBuilder) {

		// criptografar senha do usuario antes de salvar no bd;
		var user = new User(dados);
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		repository.save(user);

		var uri = uriBuilder.path("/user/newUser/{id}").buildAndExpand(user.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

}
