package br.com.opet.EzTicket.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.opet.EzTicket.database.Driver;
import br.com.opet.EzTicket.database.DriverConnection;
import br.com.opet.EzTicket.model.Cliente;
import br.com.opet.EzTicket.model.Organizador;
import br.com.opet.EzTicket.model.Sexo;
import br.com.opet.EzTicket.utils.Utils;

@ManagedBean
@SessionScoped
public class MainController {

	private Organizador organizador;
	private Cliente cliente;
	
	public void load(String id, String type) {
		if ((id != null && type != null) && (id.length() > 0 && type.length() > 0)) {
			if (type.equalsIgnoreCase("client")) {
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
			} else {
				DriverConnection connection = Driver.getStatement("select nm_organizador, endereco, cnpj, nm_email, senha from organizador where id_organizador = ?");
				ResultSet result = null;
				try {
					PreparedStatement stm = connection.getStatement();
					stm.setString(1, id);
					result = stm.executeQuery();
					while(result.next()) {
						String name = result.getString("nm_organizador");
						String endereco = result.getString("endereco");
						String cnpj = result.getString("cnpj");
						String email = result.getString("nm_email");
						String senha = result.getString("senha");
						Organizador o = new Organizador(id, name, endereco, cnpj, senha, email);
						setOrganizador(o);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public String logout() {
		Utils.deleteSession();
		return "index.xhtml";
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Organizador getOrganizador() {
		return organizador;
	}
	
	public void setOrganizador(Organizador organizador) {
		this.organizador = organizador;
	}
	
}
