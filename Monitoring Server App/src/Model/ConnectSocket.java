/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Thread.ClientHandler;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ConnectSocket {

    private String host;
    public static ServerSocket socket;
    public static List<ClientHandler> listClient = new ArrayList<ClientHandler>();

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void startServer() throws IOException {
        socket = new ServerSocket(8080);
        System.out.println("Server is running.");
    }

    public void CloseSocket() throws IOException {
        socket.close();

    }

    public static void removeClient(ClientHandler clientSocket) {
        listClient.remove(clientSocket);
    }

}
