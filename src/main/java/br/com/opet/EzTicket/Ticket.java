package br.com.opet.EzTicket;

import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

@ManagedBean
@RequestScoped
public class Ticket {

	private String id;
	private String evento;
	
	public Ticket() {
		this.id = UUID.randomUUID().toString();
	}
	
	public void setEvento(String evento) {
		this.evento = evento;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getEvento() {
		return this.evento;
	}
	
	public void salvar() {
		System.out.println("ID: " + id + ", Evento: " + evento);
	}
	
}
