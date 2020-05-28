package br.com.opet.EzTicket.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.opet.EzTicket.database.Driver;
import br.com.opet.EzTicket.database.DriverConnection;
import br.com.opet.EzTicket.model.Organizador;

@ManagedBean
@SessionScoped
public class OrganizadorController {

	private Organizador organizador;
	
	public List<Organizador> getOrganizadores() {
		List<Organizador> list = new ArrayList<Organizador>();
		DriverConnection connection = Driver.getStatement("select id_organizador, nm_organizador, endereco, cnpj, nm_email, senha from organizador");
		ResultSet result = null;
		try {
			result = connection.getStatement().executeQuery();
			while(result.next()) {
				String id = result.getString("id_organizador");
				String name = result.getString("nm_organizador");
				String endereco = result.getString("endereco");
				String cnpj = result.getString("cnpj");
				String email = result.getString("nm_email");
				String senha = result.getString("senha");
				list.add(new Organizador(id, name, endereco, cnpj, senha, email));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connection.close(result);
		return list;
	}
	
	public void loadOrganizador(String id) {
		if (id != null && id.length() == 36) {
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
	
	public String editOrganizador(String id) {
		return "editarorganizador.xhtml?faces-redirect=true&amp;org_id=" + id;
	}

	public Organizador getOrganizador() {
		return this.organizador;
	}

	public void setOrganizador(Organizador organizador) {
		this.organizador = organizador;
	}
	
}
