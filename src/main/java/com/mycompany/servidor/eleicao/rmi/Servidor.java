package com.mycompany.servidor.eleicao.rmi;

import com.mycompany.servidor.eleicao.rmi.Candidato;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Servidor implements Votar {

    private static List<Candidato> candidatos = new ArrayList();

    @Override
    public String votar(int id) throws RemoteException {
        Candidato candidato = encontraCandidato(id);
        if (candidato == null) {
            return "Candidato não encontrado";
        }
        registraVoto(id);
        return "Voto realizado com sucesso";
    }

    @Override
    public String listaCandidato() throws RemoteException {
        String lista = "";
        for (Candidato _candidato : candidatos) {
            lista += _candidato.getId() + " - " + _candidato.getNome() + "\n";
        }
        return lista;
    }

    public static void main(String args[]) {
        try {
            Servidor serv = new Servidor();

            Votar conexao = (Votar) UnicastRemoteObject.exportObject(serv, 0);

            Registry registro = LocateRegistry.createRegistry(1098);

            candidatos.add(new Candidato(1, "Joao"));
            candidatos.add(new Candidato(2, "Maria"));

            registro.bind("eleicao", conexao);

            mostraVotacao();
            System.out.println("Servidor RMI aguardando requisições...");

        } catch (Exception e) {
            System.out.println("Exceção remota: " + e);
        }
    }

    private void registraVoto(int id) {
        Candidato candidato = encontraCandidato(id);
        int candidatoIndex = candidatos.indexOf(candidato);
        candidato.contaVoto();
        candidatos.remove(candidatoIndex);
        candidatos.add(candidato);
    }

    private Candidato encontraCandidato(int id) {
        for (Candidato _candidato : candidatos) {
            if (_candidato.getId() == id) {
                return _candidato;
            }
        }
        return null;
    }

    public static void mostraVotacao() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                for (Candidato _candidato : candidatos) {
                    System.out.println(_candidato.getNome() + " esta com " + _candidato.getQuantidade() + " votos!");
                } 
                System.out.println("\n");
            }
        }, 0, 5 * 1000);
    }
}
