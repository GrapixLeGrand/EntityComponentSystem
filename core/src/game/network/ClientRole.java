package game.network;

import com.esotericsoftware.kryonet.Client;

public class ClientRole extends NetworkRole {
    Client clientInstance;

    public ClientRole() {
        clientInstance = new Client();
    }

}
