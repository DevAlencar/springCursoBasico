package com.remedios.arthur.springCurso.remedio;

import java.time.LocalDate;

public record DadosApresentarRemedio(Long id, String nome, Via via, String lote, Laboratorio laboratorio, LocalDate validade) {
	
	public DadosApresentarRemedio(Remedio remedio) {
		this(remedio.getId(),remedio.getNome(), remedio.getVia(), remedio.getLote(), remedio.getLaboratorio(), remedio.getValidade());
	}
}
