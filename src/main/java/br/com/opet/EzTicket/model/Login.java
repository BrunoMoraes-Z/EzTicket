package br.com.opet.EzTicket.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;

import br.com.opet.EzTicket.database.Driver;
import br.com.opet.EzTicket.database.DriverConnection;

@ManagedBean
public class Login {

	private String email, senha;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String autenticar(String type) {
		DriverConnection con = null;
		if (type.equalsIgnoreCase("cliente")) {
			con = Driver.getStatement("Select id_cliente from cliente where nm_email = ? and senha = ?");
			PreparedStatement stm = con.getStatement();
			try {
				stm.setString(1, this.email);
				stm.setString(2, this.senha);
				ResultSet result = stm.executeQuery();
				while(result.next()) {
					return "index.xhtml?faces-redirect=true&amp;client_id=" + result.getString("id_cliente") + "&amp;type=client";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			con = Driver.getStatement("Select id_organizador from organizador where nm_email = ? and senha = ?");
			PreparedStatement stm = con.getStatement();
			try {
				stm.setString(1, this.email);
				stm.setString(2, this.senha);
				ResultSet result = stm.executeQuery();
				while(result.next()) {
					return "index.xhtml?faces-redirect=true&amp;org_id=" + result.getString("id_organizador") + "&amp;type=org";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return type.equalsIgnoreCase("cliente") ? "logincliente.xhtml" : "loginorganizador.xhtml";
	}
	
}
