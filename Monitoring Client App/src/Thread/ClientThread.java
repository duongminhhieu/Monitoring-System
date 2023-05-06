/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Thread;

import Model.ConnectSocket;
import Model.DataSend;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class ClientThread extends Thread {

    private ConnectSocket connectSocket;
    private DataSend data;

    public ClientThread(ConnectSocket connectSocket) {
        this.connectSocket = connectSocket;
        initData();
    }

    public DataSend getData() {
        return data;
    }

    public void setData(DataSend data) {
        this.data = data;
    }
   
    public ConnectSocket getConnectSocket() {
        return connectSocket;
    }

    public void setConnectSocket(ConnectSocket connectSocket) {
        this.connectSocket = connectSocket;
    }
    
    public void initData(){
        File[] roots = File.listRoots();
        data = new DataSend(roots, null, 0, null);
    }

    @Override
    public void run() {
        try {
            System.out.println(123);
            connectSocket.connect();
            Thread reciveThread = new Thread(() -> {
                try {
                    DataSend dataSend = (DataSend) ConnectSocket.in.readObject();
                    System.out.println(dataSend.getPath());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
            reciveThread.start();

            // Tạo một luồng để gửi các phản hồi cho client một cách không đồng bộ
            Thread sendThread = new Thread(() -> {
                try {
                    while (true) {
                      ConnectSocket.out.writeObject(data);
                      ConnectSocket.out.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            sendThread.start();

        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
