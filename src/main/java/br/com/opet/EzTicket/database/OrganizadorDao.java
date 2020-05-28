package br.com.opet.EzTicket.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.opet.EzTicket.model.Organizador;
import br.com.opet.EzTicket.utils.Utils;

public class OrganizadorDao {

	public void update(Organizador org) {
		DriverConnection connection = Driver.getStatement("update organizador set nm_organizador = ?, endereco = ?, nm_email = ?, senha = ? where id_organizador = ?");
		PreparedStatement stm = connection.getStatement();
		try {
			stm.setString(1, org.getName());
			stm.setString(2, org.getEndereco());
			stm.setString(3, org.getEmail());
			stm.setString(4, org.getSenha());
			stm.setString(5, org.getId());
			int rolls = stm.executeUpdate();
			if (rolls != 1) {
				connection.roolback();
			}
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connection.close(null);
		Utils.deleteSession();
	}

	public void salvar(Organizador org) {
		DriverConnection connection = Driver.getStatement("insert into organizador (id_organizador, nm_organizador, endereco, cnpj, senha, nm_email) values (?, ?, ?, ?, ?, ?)");
		PreparedStatement stm = connection.getStatement();
		try {
			stm.setString(1, org.getId());
			stm.setString(2, org.getName());
			stm.setString(3, org.getEndereco());
			stm.setString(4, org.getCnpj());
			stm.setString(5, org.getSenha());
			stm.setString(6, org.getEmail());
			
			int rolls = stm.executeUpdate();
			if (rolls != 1) {
				connection.roolback();
			}
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connection.close(null);
		Utils.deleteSession();
	}
	
	public void delete(Organizador org) {
		DriverConnection connection = Driver.getStatement("delete from organizador where id_organizador=?");
		PreparedStatement stm = connection.getStatement();
		try {
			stm.setString(1, org.getId());
			int rolls = stm.executeUpdate();
			if (rolls != 1) {
				connection.roolback();
			}
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connection.close(null);
		Utils.deleteSession();
	}
	
}
