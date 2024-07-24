package com.remedios.arthur.springCurso.users;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacao(@NotBlank String login, @NotBlank String password) {

}
