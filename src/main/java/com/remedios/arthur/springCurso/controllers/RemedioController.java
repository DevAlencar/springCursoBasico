package com.remedios.arthur.springCurso.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.remedios.arthur.springCurso.remedio.DadosApresentarRemedio;
import com.remedios.arthur.springCurso.remedio.DadosAtualizarRemedio;
import com.remedios.arthur.springCurso.remedio.DadosCadastroRemedio;
import com.remedios.arthur.springCurso.remedio.DadosDetalharRemedio;
import com.remedios.arthur.springCurso.remedio.Remedio;
import com.remedios.arthur.springCurso.remedio.RemedioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/remedios")
public class RemedioController {

	@Autowired // possibilita utilizar os metodos da interface RemedioRepository sem precisar;
				// intanciar;
				// JPA;
	private RemedioRepository repository;

	@PostMapping
	@Transactional // se tiver algum erro, ele da um rollback;
	public ResponseEntity<DadosDetalharRemedio> cadastrar(@RequestBody @Valid DadosCadastroRemedio dados,
			UriComponentsBuilder uriBuilder) {
		var remedio = new Remedio(dados);
		repository.save(remedio); // recebe via body e salva no BD;
		// utiliza o flyway para criar tabelas automaticamente e gerenciar as versoes,
		// boa prática;

		var uri = uriBuilder.path("/remedios/{id}").buildAndExpand(remedio.getId()).toUri();

		return ResponseEntity.created(uri).body(new DadosDetalharRemedio(remedio)); // maneira correta para retornar o
																					// codigo 201 e a uri do novo
																					// remedio criado para ser usado no
																					// front
	}

	@GetMapping
	public ResponseEntity<List<DadosApresentarRemedio>> listar() {
		return ResponseEntity.ok(repository.findAllByValidTrue().stream().map(DadosApresentarRemedio::new).toList());
	}

	@PutMapping
	@Transactional
	public ResponseEntity<DadosDetalharRemedio> atualizar(@RequestBody @Valid DadosAtualizarRemedio dados) {
		var remedio = repository.getReferenceById(dados.id());
		remedio.atualizarInformacoes(dados);

		return ResponseEntity.ok(new DadosDetalharRemedio(remedio));
	}

	// metodo de exclusao total, pode dar BO de constraint no BD
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		repository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

	// metodo de exclusao logica, não da BO com o BD
	@DeleteMapping("inativar/{id}")
	@Transactional
	public ResponseEntity<Void> inativar(@PathVariable Long id) {
		var remedio = repository.getReferenceById(id);
		remedio.inativar();

		return ResponseEntity.noContent().build();
	}

	@PutMapping("ativar/{id}")
	@Transactional
	public ResponseEntity<Void> ativar(@PathVariable Long id) {
		var remedio = repository.getReferenceById(id);
		remedio.ativar();

		return ResponseEntity.noContent().build();
	}

	@GetMapping("getRemedio/{id}")
	public ResponseEntity<DadosDetalharRemedio> getRemedio(@PathVariable Long id) {
		var remedio = repository.getReferenceById(id);

		return ResponseEntity.ok(new DadosDetalharRemedio(remedio));
	}

}
