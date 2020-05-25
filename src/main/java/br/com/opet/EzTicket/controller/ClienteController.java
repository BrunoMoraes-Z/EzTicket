package br.com.opet.EzTicket.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.opet.EzTicket.database.Driver;
import br.com.opet.EzTicket.database.DriverConnection;
import br.com.opet.EzTicket.model.Cliente;
import br.com.opet.EzTicket.model.Sexo;

@ManagedBean
@SessionScoped
public class ClienteController {
	
	private Cliente cliente;
	
	public List<Cliente> getClientes() {
		List<Cliente> list = new ArrayList<Cliente>();
		DriverConnection connection = Driver.getStatement("select id_cliente, nm_cliente, dt_nascimento, nr_cpf, endereco, nm_email, id_sexo, senha from cliente");
		ResultSet result = null;
		try {
			result = connection.getStatement().executeQuery();
			while(result.next()) {
				String id = result.getString("id_cliente");
				String name = result.getString("nm_cliente");
				Date dt_nascimento = result.getDate("dt_nascimento");
				String nr_cpf = result.getString("nr_cpf");
				String endereco = result.getString("endereco");
				String nm_email = result.getString("nm_email");
				Sexo sexo = Sexo.getSexoById(result.getInt("id_sexo"));
				String senha = result.getString("senha");
				list.add(new Cliente(id, name, dt_nascimento, nr_cpf, endereco, nm_email, sexo, senha));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connection.close(result);
		return list;
	}
	
	public void loadCliente(String id) {
		if (id != null && id.length() == 36) {
			DriverConnection connection = Driver.getStatement("select nm_cliente, dt_nascimento, nr_cpf, endereco, nm_email, id_sexo, senha from cliente where id_cliente = ?");
			ResultSet result = null;
			try {
				PreparedStatement stm = connection.getStatement();
				stm.setString(1, id);
				result = stm.executeQuery();
				while(result.next()) {
					String name = result.getString("nm_cliente");
					Date dt_nascimento = result.getDate("dt_nascimento");
					String nr_cpf = result.getString("nr_cpf");
					String endereco = result.getString("endereco");
					String nm_email = result.getString("nm_email");
					Sexo sexo = Sexo.getSexoById(result.getInt("id_sexo"));
					String senha = result.getString("senha");
					Cliente c = new Cliente(id, name, dt_nascimento, nr_cpf, endereco, nm_email, sexo, senha);
					setCliente(c);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String removeCliente(String id) {
		DriverConnection connection = Driver.getStatement("delete from cliente where id_cliente=?");
		PreparedStatement stm = connection.getStatement();
		try {
			stm.setString(1, id);
			int rolls = stm.executeUpdate();
			if (rolls != 1) {
				connection.roolback();
			}
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connection.close(null);
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "consultacliente.xhtml";
	}
	
	public String editCliente(String id) {
		return "editarcliente.xhtml?faces-redirect=true&amp;client_id=" + id;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
