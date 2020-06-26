package br.com.opet.EzTicket.model.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.opet.EzTicket.database.Driver;
import br.com.opet.EzTicket.database.DriverConnection;
import br.com.opet.EzTicket.model.Cliente;

public class ClienteDao {

	public void update(Cliente cliente) {
		DriverConnection connection = Driver.getStatement("update cliente set nm_cliente = ?, endereco = ?, nm_email = ?, id_sexo = ?, senha = ? where id_cliente = ?");
		PreparedStatement stm = connection.getStatement();
		try {
			stm.setString(1, cliente.isAdmin() ? cliente.getName().concat(" adm") : cliente.getName());
			stm.setString(2, cliente.getEndereco());
			stm.setString(3, cliente.getEmail());
			stm.setInt(4, cliente.getSexo().getId());
			stm.setString(5, cliente.getSenha());
			stm.setString(6, cliente.getId());
			int rolls = stm.executeUpdate();
			if (rolls != 1) {
				connection.roolback();
			}
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connection.close(null);
	}
	
	public void save(Cliente cliente) {
		DriverConnection connection = Driver.getStatement("insert into cliente (id_cliente, nm_cliente, dt_nascimento, nr_cpf, endereco, nm_email, id_sexo, senha) values (?, ?, ?, ?, ?, ?, ?, ?)");
		PreparedStatement stm = connection.getStatement();
		try {
			stm.setString(1, cliente.getId());
			stm.setString(2, cliente.isAdmin() ? cliente.getName().concat(" adm") : cliente.getName());
			stm.setDate(3,  new java.sql.Date(cliente.getDt_nascimento().getTime()));
			stm.setString(4, cliente.getCpf());
			stm.setString(5, cliente.getEndereco());
			stm.setString(6, cliente.getEmail());
			stm.setInt(7, cliente.getSexo().getId());
			stm.setString(8, cliente.getSenha());
			int rolls = stm.executeUpdate();
			if (rolls != 1) {
				connection.roolback();
			}
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connection.close(null);
	}
	
	public void delete(Cliente cliente) {
		DriverConnection connection = Driver.getStatement("delete from cliente where id_cliente=?");
		PreparedStatement stm = connection.getStatement();
		try {
			stm.setString(1, cliente.getId());
			int rolls = stm.executeUpdate();
			if (rolls != 1) {
				connection.roolback();
			}
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connection.close(null);
//		Utils.deleteSession();
//		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
	
}
