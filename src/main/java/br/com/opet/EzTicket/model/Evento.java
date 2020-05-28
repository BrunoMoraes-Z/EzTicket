package br.com.opet.EzTicket.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

import javax.faces.bean.ManagedBean;

import br.com.opet.EzTicket.database.Driver;
import br.com.opet.EzTicket.database.DriverConnection;
import br.com.opet.EzTicket.utils.Utils;

@ManagedBean
public class Evento {

	private String id, idOrganizador, name;
	private Date dt_evento;
	private int max_pessoas = 100;
	private int id_tipo_evento, id_tipo_classificacao;
	private TipoEvento tipoEvento;
	private Classificacao classificacao;
	
	public Evento() {
		this.id = UUID.randomUUID().toString();
	}
	
	public Evento(String idOrganizador, String name, Date dt_evento, int max_pessoas, TipoEvento tipoEvento, Classificacao classificacao) {
		this.id = UUID.randomUUID().toString();
		this.idOrganizador = idOrganizador;
		this.name = name;
		this.dt_evento = dt_evento;
		this.max_pessoas = max_pessoas;
		this.tipoEvento = tipoEvento;
		this.classificacao = classificacao;
	}
	
	public Evento(String id, String idOrganizador, String name, Date dt_evento, int max_pessoas, TipoEvento tipoEvento, Classificacao classificacao) {
		this.id = id;
		this.idOrganizador = idOrganizador;
		this.name = name;
		this.dt_evento = dt_evento;
		this.max_pessoas = max_pessoas;
		this.tipoEvento = tipoEvento;
		this.classificacao = classificacao;
	}

	public String getidOrganizador() {
		return idOrganizador;
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

	public String getOwnerName() {
		DriverConnection con = Driver.getStatement("select nm_organizador from organizador where id_organizador = ?");
		PreparedStatement stm = con.getStatement();
		ResultSet result = null;
		try {
			stm.setString(1, this.idOrganizador);
			result = stm.executeQuery();
			while (result.next()) {
				return Utils.capitalize(result.getString("nm_organizador"));
			}
		} catch (SQLException e) {
			return null;
		}
		return null;
	}
	
	public String salvar(String idOrganizador) {
		this.idOrganizador = idOrganizador;
		DriverConnection con = Driver.getStatement("insert into evento (id_evento, id_organizador, nm_evento, dt_evento, max_pessoas, id_tipo_evento, id_classificacao) values (?,?,?,?,?,?,?)");
		PreparedStatement stm = con.getStatement();
		try {
			stm.setString(1, this.id);
			stm.setString(2, this.idOrganizador);
			stm.setString(3, this.name);
			stm.setDate(4, new java.sql.Date(this.dt_evento.getTime()));
			stm.setInt(5, this.max_pessoas);
			stm.setInt(6, this.tipoEvento.getId());
			stm.setInt(7, this.classificacao.getId());
			int rolls = stm.executeUpdate();
			if (rolls != 1) {
				con.roolback();
			}
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con.close(null);
		return "index.xhtml";
	}

	@Override
	public String toString() {
		return "Evento [id=" + id + ", idOrganizador=" + idOrganizador + ", name=" + name + ", dt_evento=" + dt_evento
				+ ", max_pessoas=" + max_pessoas + ", tipoEvento=" + tipoEvento + ", classificacao=" + classificacao + "]";
	}
	
	
	
}
