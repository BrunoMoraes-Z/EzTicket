package br.com.opet.EzTicket.controller;

import java.util.HashSet;

import javax.faces.bean.ManagedBean;

import br.com.opet.EzTicket.model.Organizador;

@ManagedBean
public class OrganizadorController {

	public HashSet<Organizador> orgs = new HashSet<Organizador>();
	
	public Organizador getFirst() {
		return (Organizador) orgs.toArray()[0];
	}
	
	public void addOrg(Organizador org) {
		this.orgs.add(org);
	}
	
}
