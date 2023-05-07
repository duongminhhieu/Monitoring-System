/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author ASUS
 */
public class ConnectSocket {

    private String host;
    private int port;
    public static Socket socket;

    public ConnectSocket(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void connect() throws IOException {
        socket = new Socket(host, port);
        System.out.println(socket.getPort());

    }

    public void CloseSocket() throws IOException {
        socket.close();
    }

}
