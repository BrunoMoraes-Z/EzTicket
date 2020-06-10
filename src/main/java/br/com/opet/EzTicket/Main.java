package br.com.opet.EzTicket;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.json.JSONObject;

import br.com.opet.EzTicket.model.Cliente;
import br.com.opet.EzTicket.model.Organizador;
import br.com.opet.EzTicket.model.Sexo;
import br.com.opet.EzTicket.utils.Utils;
import br.com.opet.EzTicket.utils.restful.Method;
import br.com.opet.EzTicket.utils.restful.Property;
import br.com.opet.EzTicket.utils.restful.RestAPI;

public class Main {

    public static void main(String[] args) {
    	geradorCliente(1000);
    	geradorOrganizador(60);
    }

    private static void geradorOrganizador(int amount) {
    	JSONObject pessoa;
    	for (int i = 0; i < amount; i++) {
    		RestAPI api = new RestAPI(Method.POST, "https://www.4devs.com.br/ferramentas_online.php");
        	api.setHeader(new Property("content-type", "application/x-www-form-urlencoded"));
        	api.setUrlEncodedBody(new Property[] {
        			new Property("acao", "gerar_pessoa")
        	});
        	pessoa = api.execute().getJson().getObject();
        	String id = UUID.randomUUID().toString();
        	String nome = pessoa.getString("email").split("@")[0].replace("_", " ").replace(".", " ").replace("-", " ").split(" ")[0];
        	String cpf = pessoa.getString("cpf");
        	String endereco = pessoa.getString("endereco") + ", " + pessoa.getInt("numero") + ", " + pessoa.getString("bairro");
        	String email = cpf + "@gmail.com";
        	String senha = pessoa.getString("senha");
        	Organizador o = new Organizador(id, nome, endereco, cpf + "798", senha, email);
        	System.out.println(i + " - " + o.getName());
        	o.salvar();
    	}
    }
    
    private static void geradorCliente(int amount) {
    	JSONObject pessoa;
    	for (int i = 0; i < amount; i++) {
    		RestAPI api = new RestAPI(Method.POST, "https://www.4devs.com.br/ferramentas_online.php");
        	api.setHeader(new Property("content-type", "application/x-www-form-urlencoded"));
        	api.setUrlEncodedBody(new Property[] {
        			new Property("acao", "gerar_pessoa")
        	});
        	pessoa = api.execute().getJson().getObject();
        	String id = UUID.randomUUID().toString();
        	String nome = pessoa.getString("nome");
        	Date nasc = Utils.getDateFromString(pessoa.getString("data_nasc").replace("\\", ""));
        	String cpf = pessoa.getString("cpf");
        	String endereco = pessoa.getString("endereco") + ", " + pessoa.getInt("numero") + ", " + pessoa.getString("bairro");
        	String email = cpf + "@gmail.com";
        	String senha = pessoa.getString("senha");
        	Sexo sexo = Sexo.values()[new Random().nextInt(Sexo.values().length)];
        	Cliente c = new Cliente(id, nome, nasc, cpf, endereco, email, sexo, senha);
        	System.out.println(i + " - " + c.getName());
        	c.salvar();
    	}
    }
    
}
