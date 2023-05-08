/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Component.DashboardForm;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class ClientHandler implements Runnable {

    private Socket client;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private DataSend dataSend;

    public ClientHandler(Socket client) throws IOException {
        this.client = client;
        this.out = new ObjectOutputStream(client.getOutputStream());
        this.in = new ObjectInputStream(client.getInputStream());
    }

    public Socket getClient() {
        return client;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public void setIn(ObjectInputStream in) {
        this.in = in;
    }

    public DataSend getDataSend() {
        return dataSend;
    }

    public void setDataSend(DataSend dataSend) {
        this.dataSend = dataSend;
    }

    public void sendDataToClient() throws IOException {
        out = new ObjectOutputStream(client.getOutputStream());
        out.writeObject(this.dataSend);
        out.flush();
        System.out.println(this.dataSend.getPath());
        System.out.println("send successfully");
    }

    @Override
    public void run() {

        // Tạo một luồng để nhan các phản hồi cho client một cách không đồng bộ
        Thread reciveThread = new Thread(() -> {
            try {

                while (true) {

                    in = new ObjectInputStream(client.getInputStream());

                    DataSend data = (DataSend) in.readObject();

                    dataSend = data;
                    if (data.getFolderInfo() != null) {
                        System.out.println(data.getFolderInfo().size());
                        DashboardForm.updateTableLog(data.getFolderInfo());
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();

                //client.close();
                System.out.println("Client-" + client.getPort() + " disconnected!");
                ConnectSocket.removeClient(this);
                DashboardForm.updateListClients();
                DashboardForm.updateTableLog(null);
                JOptionPane.showMessageDialog(null, "Client-" + client.getPort() + " disconnected!", "Client ngắt kết nối", JOptionPane.WARNING_MESSAGE);

            }

        });
        reciveThread.start();

    }

}
