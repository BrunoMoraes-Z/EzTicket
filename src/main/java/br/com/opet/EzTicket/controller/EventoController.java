package br.com.opet.EzTicket.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.opet.EzTicket.database.Driver;
import br.com.opet.EzTicket.database.DriverConnection;
import br.com.opet.EzTicket.model.Classificacao;
import br.com.opet.EzTicket.model.Evento;
import br.com.opet.EzTicket.model.TipoEvento;
import br.com.opet.EzTicket.utils.Utils;

@ManagedBean
public class EventoController {

	public Evento getEventoByID(String id) {
		if (id != null && id.length() == 36) {
			DriverConnection con = Driver.getStatement("Select id_organizador, nm_evento, dt_evento, max_pessoas, id_tipo_evento, id_classificacao from evento where id_evento = ?");
			PreparedStatement stm = con.getStatement();
			ResultSet result = null;
			try {
				stm.setString(1, id);
				result = stm.executeQuery();
				
				while (result.next()) {
					String organizador = result.getString("id_organizador");
					String nome = result.getString("nm_evento");
					Date dt_evento = result.getDate("dt_evento");
					int max_pessoas = result.getInt("max_pessoas");
					TipoEvento te = TipoEvento.getTipoEventoById(result.getInt("id_tipo_evento"));
					Classificacao c = Classificacao.getClassificacaoById(result.getInt("id_classificacao"));
					return new Evento(id, organizador, nome, dt_evento, max_pessoas, te, c);
				}
			} catch (SQLException e) {
				return null;
			}
			con.close(result);
		}
		return null;
	}
	
	public List<Evento> getEventosFromOwner(String id_owner) {
		if (id_owner != null && id_owner.length() == 36) {
			List<Evento> list = new ArrayList<>();
			DriverConnection con = Driver.getStatement("Select id_evento, nm_evento, dt_evento, max_pessoas, id_tipo_evento, id_classificacao from evento where id_organizador = ?");
			PreparedStatement stm = con.getStatement();
			ResultSet result = null;
			try {
				stm.setString(1, id_owner);
				result = stm.executeQuery();
				
				while (result.next()) {
					String id = result.getString("id_evento");
					String nome = result.getString("nm_evento");
					Date dt_evento = result.getDate("dt_evento");
					int max_pessoas = result.getInt("max_pessoas");
					TipoEvento te = TipoEvento.getTipoEventoById(result.getInt("id_tipo_evento"));
					Classificacao c = Classificacao.getClassificacaoById(result.getInt("id_classificacao"));
					list.add(new Evento(id, id_owner, nome, dt_evento, max_pessoas, te, c));
				}
			} catch (SQLException e) {
				return null;
			}
			con.close(result);
			return list;
		}
		return null;
	}
	
	public List<Evento> getEventos() {
		List<Evento> list = new ArrayList<>();
		DriverConnection con = Driver.getStatement("Select id_evento, id_organizador, nm_evento, dt_evento, max_pessoas, id_tipo_evento, id_classificacao from evento where dt_evento >= ?");
		PreparedStatement stm = con.getStatement();
		ResultSet result = null;
		try {
			stm.setDate(1, new java.sql.Date(Utils.getDateFromString("today").getTime()));
			result = stm.executeQuery();
			while (result.next()) {
				String id = result.getString("id_evento");
				String id_owner = result.getString("id_organizador");
				String nome = result.getString("nm_evento");
				Date dt_evento = result.getDate("dt_evento");
				int max_pessoas = result.getInt("max_pessoas");
				TipoEvento te = TipoEvento.getTipoEventoById(result.getInt("id_tipo_evento"));
				Classificacao c = Classificacao.getClassificacaoById(result.getInt("id_classificacao"));
				list.add(new Evento(id, id_owner, nome, dt_evento, max_pessoas, te, c));
			}
		} catch (SQLException e) {
			return null;
		}
		con.close(result);
		return list;
	} 
	
}
