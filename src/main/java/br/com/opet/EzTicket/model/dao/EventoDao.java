package br.com.opet.EzTicket.model.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.opet.EzTicket.database.Driver;
import br.com.opet.EzTicket.database.DriverConnection;
import br.com.opet.EzTicket.model.Evento;

public class EventoDao {

	public void update(Evento evento) {
		DriverConnection con = Driver.getStatement("update evento set nm_evento = ?, dt_evento = ?, max_pessoas = ?, id_tipo_evento = ?, id_classificacao = ?, filled_slots = ? where id_evento = ? and id_organizador = ?");
		PreparedStatement stm = con.getStatement();
		try {
			stm.setString(1, evento.getName());
			stm.setDate(2, new java.sql.Date(evento.getDt_evento().getTime()));
			stm.setInt(3, evento.getMax_pessoas());
			stm.setInt(4, evento.getTipoEvento().getId());
			stm.setInt(5, evento.getClassificacao().getId());
			stm.setInt(6, evento.getCurrent());
			stm.setString(7, evento.getId());
			stm.setString(8, evento.getidOrganizador());
			int rolls = stm.executeUpdate();
			if (rolls != 1) {
				con.roolback();
			}
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con.close(null);
	}
	
	public void save(Evento evento) {
		DriverConnection con = Driver.getStatement("insert into evento (id_evento, id_organizador, nm_evento, dt_evento, max_pessoas, id_tipo_evento, id_classificacao, filled_slots) values (?,?,?,?,?,?,?,?)");
		PreparedStatement stm = con.getStatement();
		try {
			stm.setString(1, evento.getId());
			stm.setString(2, evento.getidOrganizador());
			stm.setString(3, evento.getName());
			stm.setDate(4, new java.sql.Date(evento.getDt_evento().getTime()));
			stm.setInt(5, evento.getMax_pessoas());
			stm.setInt(6, evento.getTipoEvento().getId());
			stm.setInt(7, evento.getClassificacao().getId());
			stm.setInt(8, evento.getCurrent());
			int rolls = stm.executeUpdate();
			if (rolls != 1) {
				con.roolback();
			}
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con.close(null);
	}
	
	public void delete(Evento evento) {
		DriverConnection con = Driver.getStatement("delete evento where id_evento = ? where id_organizador = ?");
		PreparedStatement stm = con.getStatement();
		try {
			stm.setString(1, evento.getId());
			stm.setString(2, evento.getidOrganizador());
			int rolls = stm.executeUpdate();
			if (rolls != 1) {
				con.roolback();
			}
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con.close(null);
	}
	
}
