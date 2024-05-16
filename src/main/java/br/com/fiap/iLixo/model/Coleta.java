package br.com.fiap.iLixo.model;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_coleta")
public class Coleta{

	@Id
	//estrategia de criacao
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_COLETA_SEQ")
	//aponta a sequencia a ser utilizada no DB
	@SequenceGenerator(name = "TB_COLETA_SEQ", sequenceName = "TB_COLETA_SEQ", allocationSize = 1)
	public Long id;
    @Column(name = "cep_final")
    private String cepFinal;
    @Column(name = "cep_inicial")
    private String cepInicial;
    @Column(name = "data_coleta")
    private LocalDate dataColeta;
    @JsonIgnore
	@OneToMany(mappedBy = "coleta")
	private List<Cliente> clientes;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCepInicial() {
		return cepInicial;
	}
	public void setCepInicial(String cepInicial) {
		this.cepInicial = cepInicial;
	}
	public String getCepFinal() {
		return cepFinal;
	}
	public void setCepFinal(String cepFinal) {
		this.cepFinal = cepFinal;
	}
	public LocalDate getDataColeta() {
		return dataColeta;
	}
	public void setDataColeta(LocalDate dataColeta) {
		this.dataColeta = dataColeta;
	}	
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	@Override
	public String toString() {
	    return "Coleta [id=" + id + ", cepInicial=" + cepInicial + ", cepFinal=" + cepFinal + ", dataColeta="
	            + dataColeta + ", clientes=" + clientes.stream().map(Cliente::getId).collect(Collectors.toList()) + "]";
	}
}
