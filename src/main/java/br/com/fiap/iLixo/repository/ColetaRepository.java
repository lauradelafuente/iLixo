package br.com.fiap.iLixo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.iLixo.model.Coleta;

public interface ColetaRepository extends JpaRepository<Coleta, Long>{

	@Query("SELECT c FROM Coleta c WHERE c.dataColeta BETWEEN :dataInicial AND :dataFinal")
	public List<Coleta> findByDataColetaBetween(
			@Param("dataInicial") LocalDate datainicial, 
			@Param("dataFinal") LocalDate datafinal
			); 
}
