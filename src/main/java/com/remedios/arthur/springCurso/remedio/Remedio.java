package com.remedios.arthur.springCurso.remedio;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Remedio") // informa que a classe sera representada na tabela "Remedio" no BD;
@Entity(name = "remedios") // informa que a classe é um entidade chamada "remedios" no BD;

//anotacoes abaixo sao do lombok, para facilitar a criacao desses metodos em tempo de compilacao;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Remedio {

	public Remedio(DadosCadastroRemedio dados) {
		this.nome = dados.nome();
		this.via = dados.via();
		this.lote = dados.lote();
		this.quantidade = dados.quantidade();
		this.validade = dados.validade();
		this.laboratorio = dados.laboratorio();
		this.valid = true;
	}

	@Id // significa que o private Long id, será o ID da entidade no BD;
	@GeneratedValue(strategy = GenerationType.IDENTITY) // significa que o ID será gerado automaticamente;
	private Long id;

	@Enumerated(EnumType.STRING) // informa que esse tipo é um ENUM para mapear no BD;
	private Via via;

	@Enumerated(EnumType.STRING) // informa que esse tipo é um ENUM para mapear no BD;
	private Laboratorio laboratorio;

	private String nome;
	private String lote;
	private int quantidade;
	private Boolean valid;
	private LocalDate validade;
	// utilizando a dependencia "lombok", ele cria todos os getters, setters e
	// constructors em tempo de compilacao, tem as anotacões acima;

	public void atualizarInformacoes(@Valid DadosAtualizarRemedio dados) {
		if (dados.nome() != null) {
			this.nome = dados.nome();
		}
		if (dados.via() != null) {
			this.via = dados.via();
		}
		if (dados.laboratorio() != null) {
			this.laboratorio = dados.laboratorio();
		}
	}

	public void inativar() {	
		this.valid = false;
	}

	public void ativar() {
		this.valid = true;
	}

}
