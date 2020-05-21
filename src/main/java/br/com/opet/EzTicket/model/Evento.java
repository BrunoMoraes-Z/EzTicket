package br.com.opet.EzTicket.model;

import java.util.Date;
import java.util.UUID;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Evento {

	private String id, id_organizador, name;
	private Date dt_evento;
	private int max_pessoas;
	private int id_tipo_evento, id_tipo_classificacao;
	private TipoEvento tipoEvento;
	private Classificacao classificacao;
	
	public Evento() {
		this.id = UUID.randomUUID().toString();
	}
	
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
	
	public TipoEvento[] getTiposEventos() {
		return TipoEvento.values();
	}
	
	public Classificacao[] getClassificacoes() {
		return Classificacao.values();
	}

	public int getId_tipo_evento() {
		return id_tipo_evento;
	}

	public void setId_tipo_evento(int id_tipo_evento) {
		this.id_tipo_evento = id_tipo_evento;
		this.tipoEvento = TipoEvento.getTipoEventoById(id_tipo_evento);
	}

	public int getId_tipo_classificacao() {
		return id_tipo_classificacao;
	}

	public void setId_tipo_classificacao(int id_tipo_classificacao) {
		this.id_tipo_classificacao = id_tipo_classificacao;
		this.classificacao = Classificacao.getClassificacaoById(id_tipo_classificacao);
	}
	
	public String salvar(String id_organizador) {
		this.id_organizador = id_organizador;
		System.out.println(toString());
		
		return "index.xhtml";
	}

	@Override
	public String toString() {
		return "Evento [id=" + id + ", id_organizador=" + id_organizador + ", name=" + name + ", dt_evento=" + dt_evento
				+ ", max_pessoas=" + max_pessoas + ", tipoEvento=" + tipoEvento + ", classificacao=" + classificacao + "]";
	}
	
	
	
}
