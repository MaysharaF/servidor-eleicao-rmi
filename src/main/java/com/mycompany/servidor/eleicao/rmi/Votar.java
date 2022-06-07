package com.mycompany.servidor.eleicao.rmi;

import java.rmi.*;

public interface Votar extends Remote {
    public String votar(int id) throws RemoteException;
    public String listaCandidato() throws RemoteException;
}
