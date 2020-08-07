package aggregation.bank;

import java.util.ArrayList;

/**
 * Class for bank
 */
public class Bank {
    private String name;
    private ArrayList<Client> clients = new ArrayList<>();

    public Bank(String name) {
        this.name = name;
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public Client findClient(String name) {
        for (Client client : clients) {
            if (client.getName().equals(name)) {
                return client;
            }
        }
        return null;
    }
}
