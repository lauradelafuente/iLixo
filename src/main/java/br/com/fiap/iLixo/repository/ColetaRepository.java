package br.com.fiap.iLixo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.iLixo.model.Coleta;

public interface ColetaRepository extends JpaRepository<Coleta, Long>{

	public List<Coleta> findByDataColetaBetween(LocalDate datainicial, LocalDate datafinal); 
}
