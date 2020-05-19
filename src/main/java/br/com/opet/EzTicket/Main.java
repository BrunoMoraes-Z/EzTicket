package br.com.opet.EzTicket;

import java.util.Date;

import br.com.opet.EzTicket.Model.Cliente;
import br.com.opet.EzTicket.Model.Sexo;

public class Main {

    public static void main(String[] args) {
    	Cliente c = new Cliente("Bruno Moraes", new Date(), "11111111111", "teste, 123", Sexo.MASCULINO, "123456");
    	System.out.println(c.toString());
    }

}
