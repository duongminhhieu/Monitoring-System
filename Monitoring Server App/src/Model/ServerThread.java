/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Component.DashboardForm;
import static Model.ConnectSocket.socket;
import java.net.Socket;


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
                Socket clientSocket = socket.accept();
                ConnectSocket.clients.add(clientSocket);
                DashboardForm.listClient.addElement("Client-" + clientSocket.getPort());
                DashboardForm.updateListClients();
                System.out.println("Client-" + clientSocket.getPort());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  
}
