package br.com.opet.EzTicket.model;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Ingresso {

	private String id;
	private int slot;
	private Evento evento;
	private Cliente cliente;
	
	public Ingresso(String id, int slot, Evento evento, Cliente cliente) {
		this.id = id;
		this.slot = slot;
		this.evento = evento;
		this.cliente = cliente;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public int getSlot() {
		return this.slot;
	}
	
	public void save() {
		
	}
	
}
