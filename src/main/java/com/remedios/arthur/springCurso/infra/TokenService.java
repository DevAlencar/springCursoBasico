package com.remedios.arthur.springCurso.infra;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.remedios.arthur.springCurso.users.User;

@Configuration
@Service
public class TokenService {

	@Value("${api.security.token.secret}") // recebe o secret da variavel de ambiente informada no properties;
	private String secret;

	public String tokenGenerator(User user) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.create().withIssuer("remedios_api").withSubject(user.getLogin()).withClaim("id", user.getId())
					.withExpiresAt(expirationDate()).sign(algorithm); // escolhe os retornos com os with
		} catch (JWTCreationException exception) {
			throw new RuntimeException("Erro ao gerar token", exception);
		}
	}

	// define o tempo de expiracao do token, para o usuario logar novamente
	private Instant expirationDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

	// metodo para verificar se token é valido;\
	public String getSubject(String tokenJWT) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			var sub = JWT.require(algorithm).withIssuer("remedios_api").build().verify(tokenJWT).getSubject();
			return sub;
		} catch (JWTVerificationException exception) {
			throw new RuntimeException("Token invalido ou expirado", exception);
		}
	}

}
