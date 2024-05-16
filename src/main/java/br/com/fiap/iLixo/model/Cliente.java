package br.com.fiap.iLixo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_cliente")
public class Cliente {

	@Id
	//estrategia de criacao
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_CLIENTE_SEQ")
	//aponta a sequencia a ser utilizada no DB
	@SequenceGenerator(name = "TB_CLIENTE_SEQ", sequenceName = "TB_CLIENTE_SEQ", allocationSize = 1)
	private Long id;
	private String nome;
	private String cpf;
	private String cep;
	private String endereco;
	private long telefone;
	private String email;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "coleta_id")
	private Coleta coleta;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public long getTelefone() {
		return telefone;
	}
	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Coleta getColeta() {
		return coleta;
	}
	public void setColeta(Coleta coleta) {
		this.coleta = coleta;
	}
	@Override
	public String toString() {
	    return "Cliente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", cep=" + cep + ", endereco=" + endereco + ", telefone="
	            + telefone + ", email=" + email + ", coleta=" + coleta.getId() + "]";
	}	
}
