package com.remedios.arthur.springCurso.remedio;

import java.time.LocalDate;

public record DadosDetalharRemedio(Long id, String nome, Via via, String lote, Laboratorio laboratorio,
		LocalDate validade, int quantidade, boolean valid) {

	public DadosDetalharRemedio(Remedio remedio) {
		this(remedio.getId(), remedio.getNome(), remedio.getVia(), remedio.getLote(), remedio.getLaboratorio(),
				remedio.getValidade(), remedio.getQuantidade(), remedio.getValid());
	}
}
