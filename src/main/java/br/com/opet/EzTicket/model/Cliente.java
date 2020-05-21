package br.com.opet.EzTicket.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.faces.bean.ManagedBean;

import br.com.opet.EzTicket.database.Driver;
import br.com.opet.EzTicket.database.DriverConnection;
import br.com.opet.EzTicket.utils.Utils;

@ManagedBean
public class Cliente {

	private String id, name, cpf, endereco, senha, email;
	private Date dt_nascimento;
	private int sexoId = 1;
	private Sexo sexo;
	
	public Cliente() {
		this.id = UUID.randomUUID().toString();
	}

	public Cliente(String id, String name, Date dt_nascimento, String cpf, String endereco, String email, Sexo sexo, String senha) {
		this.id = id;
		this.name = name;
		this.dt_nascimento = dt_nascimento;
		this.cpf = cpf;
		this.endereco = endereco;
		this.email = email;
		this.sexo = sexo;
		this.senha = senha;
		this.sexoId = sexo.getId();
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

	public Date getDt_nascimento() {
		return dt_nascimento;
	}

	public void setDt_nascimento(Date dt_nascimento) {
		this.dt_nascimento = dt_nascimento;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public int getSexoId() {
		return sexoId;
	}
	
	public void setSexoId(int sexoId) {
		this.sexoId = sexoId;
		this.sexo = Sexo.getSexoById(sexoId);
	}
	
	public void setSexo(int sexo_id) {
		this.sexo = Sexo.getSexoById(sexo_id);
	}

	public String getId() {
		return id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf.replace(".", "").replace("-", "");
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFormatedDtNascimento() {
		return new SimpleDateFormat("dd/MM/YYYY").format(this.dt_nascimento);
	}
	
	public String getFormatedCpf() {
		return Utils.formatter(cpf, "###.###.###-##");
	}
	
	public String update() {
		DriverConnection connection = Driver.getStatement("update cliente set nm_cliente = ?, endereco = ?, nm_email = ?, id_sexo = ?, senha = ? where id_cliente = ?");
		PreparedStatement stm = connection.getStatement();
		try {
			stm.setString(1, this.name);
			stm.setString(2, this.endereco);
			stm.setString(3, this.email);
			stm.setInt(4, this.sexo.getId());
			stm.setString(5, this.senha);
			stm.setString(6, this.id);
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
	
	public String salvar() {
		System.out.println(this);
		DriverConnection connection = Driver.getStatement("insert into cliente (id_cliente, nm_cliente, dt_nascimento, nr_cpf, endereco, nm_email, id_sexo, senha) values (?, ?, ?, ?, ?, ?, ?, ?)");
		PreparedStatement stm = connection.getStatement();
		try {
			stm.setString(1, this.id);
			stm.setString(2, this.name);
			stm.setDate(3,  new java.sql.Date(this.dt_nascimento.getTime()));
			stm.setString(4, this.cpf);
			stm.setString(5, this.endereco);
			stm.setString(6, this.email);
			stm.setInt(7, this.sexo.getId());
			stm.setString(8, this.senha);
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
	
	public Sexo[] getSexos() {
		return Sexo.values();
	}
	
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", name=" + name + ", cpf=" + cpf + ", endereco=" + endereco + ", senha=" + senha
				+ ", dt_nascimento=" + dt_nascimento + ", sexo=" + sexo + ", sexoID=" + sexoId + "]";
	}
}
