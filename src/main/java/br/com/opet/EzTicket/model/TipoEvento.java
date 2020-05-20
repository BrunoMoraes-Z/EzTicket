package br.com.opet.EzTicket.model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import br.com.opet.EzTicket.utils.Utils;

public enum TipoEvento {

	PALESTRA(1, new String[] {}), SHOW(2, new String[] {}), OUTROS(3, new String[] {}); 
	
	private int id;
	private String[] desc;
	
	TipoEvento(int id, String[] desc) {
		this.id = id;
		this.desc = desc;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return Utils.capitalize(this.name().toLowerCase());
	}
	
	public List<String> getDescription() {
		return Arrays.asList(this.desc);
	}
	
	public static TipoEvento getTipoEventoById(int id) {
		Optional<TipoEvento> result = Arrays.asList(values()).stream().filter(s -> s.getId() == id).findFirst();
		return result.isPresent() ? result.get() : OUTROS;
	}
	
}
