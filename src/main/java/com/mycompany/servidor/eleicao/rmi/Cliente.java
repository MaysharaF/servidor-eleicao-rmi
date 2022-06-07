package com.mycompany.servidor.eleicao.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import com.mycompany.servidor.eleicao.rmi.Votar;

public class Cliente {

    public static void main(String args[]) {
        try {
            Registry registro = LocateRegistry.getRegistry("localhost", 1098);

            Votar stub = (Votar) registro.lookup("eleicao");

            do {
                String listaCandidatos = stub.listaCandidato();
                
                System.out.println("Lista de Candidatos: \n" + listaCandidatos);
                
                Scanner input = new Scanner(System.in);
                System.out.println("Digite sua opção: ");
                int op = input. nextInt();                
                
                String retorno = stub.votar(op);
                System.out.println("Servidor retornou: " + retorno + "\n");
            } while (true);

        } catch (Exception e) {
            System.out.println("Exceção remota: " + e);
        }
    }

}
