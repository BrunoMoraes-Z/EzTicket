package br.com.opet.EzTicket.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.opet.EzTicket.database.Driver;
import br.com.opet.EzTicket.database.DriverConnection;
import br.com.opet.EzTicket.model.Organizador;

@ManagedBean
@SessionScoped
public class OrganizadorController {

	public HashSet<Organizador> orgs = new HashSet<Organizador>();
	
	public Organizador getFirst() {
		return (Organizador) orgs.toArray()[0];
	}
	
	public void addOrg(Organizador org) {
		this.orgs.add(org);
	}
	
	private Organizador organizador;
	
	public List<Organizador> getOrganizadores() {
		List<Organizador> list = new ArrayList<Organizador>();
		DriverConnection connection = Driver.getStatement("select id_organizador, nm_organizador, endereco, cnpj, eventos, nm_email, senha from organizador");
		ResultSet result = null;
		try {
			result = connection.getStatement().executeQuery();
			while(result.next()) {
				String id = result.getString("id_organizador");
				String name = result.getString("nm_organizador");
				String endereco = result.getString("endereco");
				String cnpj = result.getString("cnpj");
				String eventos = result.getString("eventos");
				String email = result.getString("nm_email");
				String senha = result.getString("senha");
				list.add(new Organizador(id, name, endereco, cnpj, senha, email, eventos));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connection.close(result);
		return list;
	}
	
	public void loadOrganizador(String id) {
		if (id != null && id.length() == 36) {
			DriverConnection connection = Driver.getStatement("select nm_organizador, endereco, cnpj, eventos, nm_email, senha from organizador where id_organizador = ?");
			ResultSet result = null;
			try {
				PreparedStatement stm = connection.getStatement();
				stm.setString(1, id);
				result = stm.executeQuery();
				while(result.next()) {
					String name = result.getString("nm_organizador");
					String endereco = result.getString("endereco");
					String cnpj = result.getString("cnpj");
					String eventos = result.getString("eventos");
					String email = result.getString("nm_email");
					String senha = result.getString("senha");
					Organizador o = new Organizador(id, name, endereco, cnpj, senha, email, eventos);
					setOrganizador(o);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String removeOrganizador(String id) {
		DriverConnection connection = Driver.getStatement("delete from organizador where id_organizador=?");
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
		return "consultaorganizador.xhtml";
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
