/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author ASUS
 */
public class ConnectSocket {

    private String host;
    public static ServerSocket socket;
    public static Socket clientSocket;
    public static ObjectOutputStream out;
    public static ObjectInputStream in;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void startServer() {
        try {
            socket = new ServerSocket(8080);
            System.out.println("Server is running.");

            while (true) {
      
                clientSocket = socket.accept();
                out = new ObjectOutputStream(clientSocket.getOutputStream());
                in = new ObjectInputStream(clientSocket.getInputStream());
                receiveData();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendData(DataSend dataSend) {
        try {
            out.writeObject(dataSend);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public DataSend receiveData() {
        DataSend ds = null;
        try {
            while (true) {
                ds = (DataSend) in.readObject();
                System.out.println(ds.getPath());

                if (ds.getStatus() == -1) {
                    break;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }

    public void CloseSocket() {

        try {
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
