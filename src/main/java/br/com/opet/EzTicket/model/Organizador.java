package br.com.opet.EzTicket.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.opet.EzTicket.model.dao.OrganizadorDao;
import br.com.opet.EzTicket.utils.Utils;

@ManagedBean
@SessionScoped
public class Organizador {

	private String id, name, endereco, cnpj, senha, email;

	public Organizador() {
		this.id = UUID.randomUUID().toString();
	}

	public Organizador(String id, String name, String endereco, String cnpj, String senha, String email) {
		this.id = id;
		this.name = name;
		this.endereco = endereco;
		this.cnpj = cnpj;
		this.senha = senha;
		this.email = email;
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj.replace(".", "").replace("/", "").replace("-", "");
	}

	public List<Evento> getEventos() {
		return new ArrayList<>();
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFormatedCNPJ() {
		return Utils.formatter(cnpj, "##.###.###/####-##");
	}

	public String update() {
		new OrganizadorDao().update(this);
		return "consultaorganizador.xhtml";
	}

	public String salvar() {
		new OrganizadorDao().save(this);
		return "index.xhtml";
	}
	
	public String delete() {
		new OrganizadorDao().delete(this);
		return "consultaorganizador.xhtml";
	}

	public String toString() {
		return "Organizador [id=" + id + ", name=" + name + ", endereco=" + endereco + ", cnpj=" + cnpj + ", senha="
				+ senha + "]";
	}

}
