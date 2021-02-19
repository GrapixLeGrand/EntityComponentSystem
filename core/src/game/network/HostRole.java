package game.network;

import com.esotericsoftware.kryonet.Server;

public class HostRole extends NetworkRole {
    private Server serverInstance;
    public HostRole() {
        serverInstance = new Server();
    }
}
