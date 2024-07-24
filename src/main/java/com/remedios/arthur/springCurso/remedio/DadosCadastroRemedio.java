package com.remedios.arthur.springCurso.remedio;

import java.time.LocalDate;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroRemedio(

		@NotBlank String nome,

		@Enumerated Via via, // utiliza o tipo enum para garantir o formato do dado recebido;

		@NotBlank String lote,

		@NotNull int quantidade,

		@Future LocalDate validade,

		@Enumerated Laboratorio laboratorio // utiliza o tipo enum para garantir o formato do dado recebido;
) {

}
