package br.com.opet.EzTicket.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.faces.bean.ManagedBean;

import br.com.opet.EzTicket.controller.IngressoController;
import br.com.opet.EzTicket.model.dao.ClienteDao;
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
	
	public boolean isAdmin() {
		return this.name.toLowerCase().contains(" adm");
	}

	public String getName() {
		return name != null ? name.contains(" adm") ? new String(name).replace(" adm", "") : name : null;
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
		new ClienteDao().update(this);
		return "index.xhtml";
	}
	
	public String salvar() {
		new ClienteDao().save(this);
		return "index.xhtml";
	}
	
	public String delete() {
		new ClienteDao().delete(this);
		return "consultacliente.xhtml";
	}
	
	public boolean hasEvent(Evento evento) {
		return evento != null ? new IngressoController().hasIngresso(this, evento) : false;
	}
	
	public String removeEvent(Evento evento) {
		new IngressoController().delete(evento.getId(), this.getId());
		return "cconsultaeventosclient.xhtml";
	}
	
	public String addEvent(Evento evento) {
		if (evento.hasSlot()) {
			evento.updateSlot();
			new Ingresso(UUID.randomUUID().toString(), evento.getCurrent() - 1, evento, this).save();
		}
		return "consultaeventos.xhtml";
	}
	
	public Sexo[] getSexos() {
		return Sexo.values();
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", name=" + name + ", cpf=" + cpf + ", endereco=" + endereco + ", senha=" + senha
				+ ", email=" + email + ", dt_nascimento=" + dt_nascimento + ", sexoId=" + sexoId + ", sexo=" + sexo
				+ "]";
	}
	
	
}
