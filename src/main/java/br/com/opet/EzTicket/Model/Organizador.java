package br.com.opet.EzTicket.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Organizador {

	private String id, name, endereco, cnpj;
	private List<Evento> eventos;
	
	public Organizador(String name, String endereco, String cnpj) {
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.endereco = endereco;
		this.cnpj = cnpj;
	}
	
	public Organizador(String id, String name, String endereco, String cnpj) {
		this.id = id;
		this.name = name;
		this.endereco = endereco;
		this.cnpj = cnpj;
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

	public String getId() {
		return id;
	}
	
}
