package com.remedios.arthur.springCurso.remedio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RemedioRepository extends JpaRepository<Remedio, Long> {

	List<Remedio> findAllByValidTrue();

}
