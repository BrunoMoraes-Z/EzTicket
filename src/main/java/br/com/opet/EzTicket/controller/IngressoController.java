package br.com.opet.EzTicket.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.opet.EzTicket.database.Driver;
import br.com.opet.EzTicket.database.DriverConnection;
import br.com.opet.EzTicket.model.Cliente;
import br.com.opet.EzTicket.model.Evento;

public class IngressoController {

	public boolean hasIngresso(Cliente client, Evento event) {
		DriverConnection con = Driver.getStatement("select id_ingresso from ingresso where id_cliente = ? and id_evento = ?");
		PreparedStatement stm = con.getStatement();
		ResultSet result = null;
		try {
			stm.setString(1, client.getId());
			stm.setString(2, event.getId());
			result = stm.executeQuery();
			while(result.next()) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		con.close(result);
		return false;
	}
	
	public void delete(String evento_id, String client_id) {
		DriverConnection con = Driver.getStatement("delete ingresso where id_evento = ? and id_cliente = ?");
		try {
			PreparedStatement stm = con.getStatement();
			stm.setString(1, evento_id);
			stm.setString(2, client_id);
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
	
}
