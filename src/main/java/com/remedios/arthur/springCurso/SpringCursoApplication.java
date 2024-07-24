package com.remedios.arthur.springCurso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.remedios.arthur.springCurso.users.User;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableWebMvc
public class SpringCursoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCursoApplication.class, args);
	}

	// nao sei pq, mas o indiano disse q precisava pra resolver o bug;
	@Bean
	public User user() {
		return new User();
	}

}
