package br.com.opet.EzTicket;

public class Main {

    public static void main(String[] args) {
        try {
            Class c = Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Classe encontrada.");
        } catch (ClassNotFoundException e) {
            System.out.println("Classe não foi encontrada.");
        }
    }

}
