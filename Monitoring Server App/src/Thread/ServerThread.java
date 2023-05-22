/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Thread;

import Component.DashboardForm;
import Model.ConnectSocket;
import static Model.ConnectSocket.socket;
import Model.DataSend;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.DefaultListModel;

/**
 *
 * @author ASUS
 */
public class ServerThread extends Thread {

    private ConnectSocket connectSocket;

    public ServerThread(ConnectSocket connectSocket) {
        this.connectSocket = connectSocket;
    }

    @Override
    public void run() {
        try {
            connectSocket.startServer();

            // lang nghe cac clients
            while (true) {

                // accept, add thread to list
                Socket clientSocket = socket.accept();

                ConnectSocket.listClient.add(new ClientHandler(clientSocket));

                new Thread(ConnectSocket.listClient.get(ConnectSocket.listClient.size() - 1)).start();
                // Update UI
                DashboardForm.updateListClients();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
