package br.com.opet.EzTicket.model;

import java.util.Arrays;
import java.util.Optional;

import javax.faces.bean.ManagedBean;

import br.com.opet.EzTicket.utils.Utils;

@ManagedBean
public enum Sexo {
	
	MASCULINO(1), FEMININO(2), INDEFINIDO(3); 
	
	private int id;
	
	Sexo() {
	}
	
	Sexo(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return Utils.capitalize(this.name().toLowerCase());
	}
	
	public static Sexo getSexoById(int id) {
		Optional<Sexo> result = Arrays.asList(values()).stream().filter(s -> s.getId() == id).findFirst();
		return result.isPresent() ? result.get() : MASCULINO;
	}
	
}
