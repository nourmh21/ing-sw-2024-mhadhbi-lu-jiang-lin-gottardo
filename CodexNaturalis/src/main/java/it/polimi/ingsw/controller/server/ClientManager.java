package it.polimi.ingsw.controller.server;

import it.polimi.ingsw.network.Client;

import java.util.HashSet;
import java.util.Set;

public class ClientManager {
    Set<Client> clients;
    public ClientManager(){
        clients = new HashSet<>();
    }

    public void addClient(Client client){
        clients.add(client);
    }

    public void removeClient(Client client){
        clients.remove(client);
    }
}
