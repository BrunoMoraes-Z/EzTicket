package br.com.opet.EzTicket.Model;

import java.util.Date;
import java.util.UUID;

public class Evento {

	private String id, id_organizador, name;
	private Date dt_evento;
	private int max_pessoas;
	private TipoEvento tipoEvento;
	private Classificacao classificacao;
	
	public Evento(String id_organizador, String name, Date dt_evento, int max_pessoas, TipoEvento tipoEvento, Classificacao classificacao) {
		this.id = UUID.randomUUID().toString();
		this.id_organizador = id_organizador;
		this.name = name;
		this.dt_evento = dt_evento;
		this.max_pessoas = max_pessoas;
		this.tipoEvento = tipoEvento;
		this.classificacao = classificacao;
	}
	
	public Evento(String id, String id_organizador, String name, Date dt_evento, int max_pessoas, TipoEvento tipoEvento, Classificacao classificacao) {
		this.id = id;
		this.id_organizador = id_organizador;
		this.name = name;
		this.dt_evento = dt_evento;
		this.max_pessoas = max_pessoas;
		this.tipoEvento = tipoEvento;
		this.classificacao = classificacao;
	}

	public String getId_organizador() {
		return id_organizador;
	}

	public void setId_organizador(String id_organizador) {
		this.id_organizador = id_organizador;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDt_evento() {
		return dt_evento;
	}

	public void setDt_evento(Date dt_evento) {
		this.dt_evento = dt_evento;
	}

	public int getMax_pessoas() {
		return max_pessoas;
	}

	public void setMax_pessoas(int max_pessoas) {
		this.max_pessoas = max_pessoas;
	}

	public TipoEvento getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public Classificacao getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(Classificacao classificacao) {
		this.classificacao = classificacao;
	}

	public String getId() {
		return id;
	}
	
	
}
