package com.example.einzelabgabe_auer.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;


public class NetworkClient extends Thread {

    private int port;
    private String host;
    private String payload;
    private String serverAnswer;

    public NetworkClient(String host, int port) {
        this.host = host;
        this.port = port;
        this.serverAnswer = "failed.";
    }


    @Override
    public void run() {
        super.run();
        this.serverAnswer = connectToServer();
    }

    private String connectToServer() {
        try {
            Socket socket = new Socket(this.host, this.port);
            DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
            outStream.writeBytes(payload + "\n");
            BufferedReader inBuffer = new BufferedReader(new
                    InputStreamReader(socket.getInputStream()));
            outStream.flush();
            return inBuffer.readLine();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "Unknown error occurred.";
    }

    /**
     * Getter / Setter
     */
    public String getServerAnswer() {
        return serverAnswer;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public int getPort() {
        return port;
    }


    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
