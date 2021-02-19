package com.github.grapixlegrand.kryonettest;


import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

class SomeRequest {
    String text;
}

class SomeResponse {
    String text;
}

public class Main {
    public static void main(String[] args) throws IOException {

        Server server = new Server();
        server.start();
        server.bind(54555);

        Client client = new Client();
        client.start();
        client.connect(5000, "127.0.0.1", 54555);

        Kryo kryo = server.getKryo();
        kryo.register(SomeRequest.class);
        kryo.register(SomeResponse.class);
        Kryo kryo2 = client.getKryo();
        kryo2.register(SomeRequest.class);
        kryo2.register(SomeResponse.class);

        server.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if (object instanceof SomeRequest) {
                    SomeRequest request = (SomeRequest)object;
                    System.out.println(request.text);

                    SomeResponse response = new SomeResponse();
                    response.text = "Thanks";
                    connection.sendTCP(response);
                }
            }
        });

        client.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if (object instanceof SomeResponse) {
                    SomeResponse response = (SomeResponse)object;
                    System.out.println(response.text);
                }
            }
        });

        SomeRequest request = new SomeRequest();
        request.text = "Here is the request";
        client.sendTCP(request);

    }
}