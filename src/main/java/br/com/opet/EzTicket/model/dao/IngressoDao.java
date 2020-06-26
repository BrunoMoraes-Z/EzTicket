package br.com.opet.EzTicket.model.dao;

import java.sql.PreparedStatement;

import br.com.opet.EzTicket.database.Driver;
import br.com.opet.EzTicket.database.DriverConnection;
import br.com.opet.EzTicket.model.Ingresso;

public class IngressoDao {

	public void save(Ingresso ticket) {
		DriverConnection con = Driver.getStatement("insert into ingresso (id_ingresso, slot, id_evento, id_cliente) values (?, ?, ?, ?)");
		PreparedStatement stm = con.getStatement();
		try {
			stm.setString(1, ticket.getId());
			stm.setInt(2, ticket.getSlot());
			stm.setString(3, ticket.getEvento().getId());
			stm.setString(4, ticket.getCliente().getId());
			int rolls = stm.executeUpdate();
			if (rolls != 1) {
				con.roolback();
			}
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		con.close(null);
	}
	
	public void delete(Ingresso ticket) {
		DriverConnection con = Driver.getStatement("delete ingresso where id_ingresso = ? and id_evento = ? and id_cliente = ?");
		PreparedStatement stm = con.getStatement();
		try {
			stm.setString(1, ticket.getId());
			stm.setString(2, ticket.getEvento().getId());
			stm.setString(3, ticket.getCliente().getId());
			int rolls = stm.executeUpdate();
			if (rolls != 1) {
				con.roolback();
			}
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		con.close(null);
	}
	
	public void update(Ingresso ticket) {
		
	}
	
}
