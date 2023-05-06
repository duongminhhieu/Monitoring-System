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
    

    @Override
    public void run() {

        // Tạo một luồng để nhan các phản hồi cho client một cách không đồng bộ
        Thread reciveThread = new Thread(() -> {
            try {

                while (true) {
                    DataSend data = (DataSend) in.readObject();

                    // khoi tao
                    if (data.getStatus() == 0) {
                        dataSend = data;
                        System.out.println(data);
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        reciveThread.start();

        // Tạo một luồng để gửi các phản hồi cho client một cách không đồng bộ
        Thread sendThread = new Thread(() -> {

        });
        sendThread.start();

    }

}
