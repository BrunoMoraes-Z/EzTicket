package br.com.opet.EzTicket.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.opet.EzTicket.database.Driver;
import br.com.opet.EzTicket.database.DriverConnection;
import br.com.opet.EzTicket.model.Cliente;
import br.com.opet.EzTicket.model.Ingresso;
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
	
	public List<Ingresso> getIngressosFromCliente(Cliente cliente) {
		List<Ingresso> list = new ArrayList<Ingresso>();
		DriverConnection connection = Driver.getStatement("select id_ingresso, slot, id_evento from ingresso where id_cliente = ?");
		ResultSet result = null;
		try {
			PreparedStatement stm = connection.getStatement();
			stm.setString(1, cliente.getId());
			result = stm.executeQuery();
			while(result.next()) {
				String id_ingresso = result.getString("id_ingresso");
				String id_evento = result.getString("id_evento");
				EventoController con = new EventoController();
				con.loadEvento(id_evento);
				int slot = result.getInt("slot");
				list.add(new Ingresso(id_ingresso, slot, con.getEvento(), cliente));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connection.close(result);
		return list;
	}
	
	public String delete(String id) {
		loadCliente(id);
		return this.cliente.delete();
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
