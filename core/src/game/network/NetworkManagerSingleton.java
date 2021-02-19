package game.network;

import java.util.ArrayList;
import java.util.List;

public class NetworkManagerSingleton {

    private static NetworkManagerSingleton instance = null;
    private NetworkRole networkRole;
    private HostRole host;
    private List<ClientRole> clients;

    private NetworkManagerSingleton() {
        host = new HostRole();
        clients = new ArrayList<>();
    }

    public static NetworkManagerSingleton getInstance() {
        if (instance == null) {
            instance = new NetworkManagerSingleton();
        }
        return instance;
    }

    public ClientRole registerClient() {
        ClientRole client = new ClientRole();
        clients.add(client);
        return client;
    }

    public void revokeClient(ClientRole client) {
        if (!clients.contains(client)) {
            throw new IllegalArgumentException("client does not exists");
        }
        clients.remove(client);
    }



}
