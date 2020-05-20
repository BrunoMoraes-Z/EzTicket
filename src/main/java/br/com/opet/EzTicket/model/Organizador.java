package br.com.opet.EzTicket.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.opet.EzTicket.controller.OrganizadorController;

@ManagedBean
@SessionScoped
public class Organizador {

	private String id, name, endereco, cnpj, senha;
	private List<Evento> eventos;
	
	public Organizador() {
		this.id = UUID.randomUUID().toString();
	}
	
	public Organizador(String name, String endereco, String cnpj) {
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.endereco = endereco;
		this.cnpj = cnpj;
	}
	
	public Organizador(String id, String name, String endereco, String cnpj, String senha) {
		this.id = id;
		this.name = name;
		this.endereco = endereco;
		this.cnpj = cnpj;
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}
	
	public void addEvento(Evento evento) {
		if (this.eventos == null) {
			this.eventos = new ArrayList<Evento>();
		}
		this.eventos.add(evento);
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
	
	public String salvar() {
		System.out.println(toString());
		new OrganizadorController().addOrg(this);
		return "index.xhtml";
	}

	public String toString() {
		return "Organizador [id=" + id + ", name=" + name + ", endereco=" + endereco + ", cnpj=" + cnpj + ", senha="
				+ senha + ", eventos=" + eventos + "]";
	}
	
}
