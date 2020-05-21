package br.com.opet.EzTicket.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.opet.EzTicket.controller.OrganizadorController;
import br.com.opet.EzTicket.database.Driver;
import br.com.opet.EzTicket.database.DriverConnection;

@ManagedBean
@SessionScoped
public class Organizador {

	private String id, name, endereco, cnpj, senha, email;
	private List<Evento> eventos;
	
	public Organizador() {
		this.id = UUID.randomUUID().toString();
	}
	
	public Organizador(String id, String name, String endereco, String cnpj, String senha, String email) {
		this.id = id;
		this.name = name;
		this.endereco = endereco;
		this.cnpj = cnpj;
		this.senha = senha;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}
	
	public void addEvento(Evento evento) {
		if (this.eventos == null) {
			this.eventos = new ArrayList<Evento>();
		}
		this.eventos.add(evento);
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String salvar() {
		new OrganizadorController().addOrg(this);
		DriverConnection connection = Driver.getStatement("insert into organizador (id_organizador, nm_organizador, endereco, cnpj, senha, nm_email) values (?, ?, ?, ?, ?, ?)");
		PreparedStatement stm = connection.getStatement();
		try {
			stm.setString(1, this.id);
			stm.setString(2, this.name);
			stm.setString(3, this.endereco);
			stm.setString(4, this.cnpj);
			stm.setString(5, this.senha);
			stm.setString(6, this.email);
			
			int rolls = stm.executeUpdate();
			if (rolls != 1) {
				connection.roolback();
			}
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connection.close(null);
		return "index.xhtml";
	}

	public String toString() {
		return "Organizador [id=" + id + ", name=" + name + ", endereco=" + endereco + ", cnpj=" + cnpj + ", senha="
				+ senha + ", eventos=" + eventos + "]";
	}
	
}
