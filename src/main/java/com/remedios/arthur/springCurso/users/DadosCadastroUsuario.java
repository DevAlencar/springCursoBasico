package com.remedios.arthur.springCurso.users;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(@NotBlank String login, @NotBlank String password) {

}
