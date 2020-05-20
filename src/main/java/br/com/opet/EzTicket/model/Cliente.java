package br.com.opet.EzTicket.model;

import java.util.Date;
import java.util.UUID;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Cliente {

	private String id, name, cpf, endereco, senha;
	private Date dt_nascimento;
	private int sexoId;
	private Sexo sexo;
	
	public Cliente() {
		this.id = UUID.randomUUID().toString();
	}
	
	public Cliente(String name, Date dt_nascimento, String cpf, String endereco, Sexo sexo, String senha) {
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.dt_nascimento = dt_nascimento;
		this.cpf = cpf;
		this.endereco = endereco;
		this.sexo = sexo;
		this.senha = senha;
	}

	public Cliente(String id, String name, Date dt_nascimento, String cpf, String endereco, Sexo sexo, String senha) {
		this.id = id;
		this.name = name;
		this.dt_nascimento = dt_nascimento;
		this.cpf = cpf;
		this.endereco = endereco;
		this.sexo = sexo;
		this.senha = senha;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Date getDt_nascimento() {
		return dt_nascimento;
	}

	public void setDt_nascimento(Date dt_nascimento) {
		this.dt_nascimento = dt_nascimento;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public int getSexoId() {
		return sexoId;
	}
	
	public void setSexoId(int sexoId) {
		this.sexoId = sexoId;
		this.sexo = Sexo.getSexoById(sexoId);
	}
	
	public void setSexo(int sexo_id) {
		this.sexo = Sexo.getSexoById(sexo_id);
	}

	public String getId() {
		return id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", name=" + name + ", cpf=" + cpf + ", endereco=" + endereco + ", senha=" + senha
				+ ", dt_nascimento=" + dt_nascimento + ", sexo=" + sexo + "]";
	}
	
	public String salvar() {
		System.out.println(toString());
		return "index.xhtml";
	}
	
	public Sexo[] getSexos() {
		return Sexo.values();
	}
	
}
