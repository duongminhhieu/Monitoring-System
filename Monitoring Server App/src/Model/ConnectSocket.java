/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ConnectSocket {

    private String host;
    public static ServerSocket socket;
    public static Socket clientSocket;
    public static List<Socket> clients = new ArrayList<Socket>();
    public static ObjectOutputStream out;
    public static ObjectInputStream in;

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

    public void sendData(DataSend dataSend) {
        try {
            out.writeObject(dataSend);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public DataSend receiveData() throws IOException, ClassNotFoundException {
        DataSend ds = null;
        while (true) {
            ds = (DataSend) in.readObject();
            System.out.println(ds.getPath());

            if (ds.getStatus() == -1) {
                break;
            }

        }

        return ds;
    }

    public void CloseSocket() throws IOException {

        in.close();
        out.close();
        socket.close();

    }

    public static void removeClient(Socket clientSocket) {
        ConnectSocket.clients.remove(clientSocket);
    }

    public static List<Socket> getClients() {
        return ConnectSocket.clients;
    }

}
