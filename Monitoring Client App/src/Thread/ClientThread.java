/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Thread;

import Model.ConnectSocket;
import Model.DataSend;
import Model.WatchFolder;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class ClientThread extends Thread {

    private ConnectSocket connectSocket;
    public static DataSend data;
    public static ObjectOutputStream out;
    public static ObjectInputStream in;

    public ClientThread(ConnectSocket connectSocket) throws IOException {
        this.connectSocket = connectSocket;
        this.out = new ObjectOutputStream(ConnectSocket.socket.getOutputStream());
        this.in = new ObjectInputStream(ConnectSocket.socket.getInputStream());
        initData();
    }

    public ConnectSocket getConnectSocket() {
        return connectSocket;
    }

    public void setConnectSocket(ConnectSocket connectSocket) {
        this.connectSocket = connectSocket;
    }

    public void initData() {
        File[] roots = File.listRoots();
        data = new DataSend(roots, null, 0, null);
    }

    public static void sendDataToServer() throws IOException {
        System.out.println(data.getRoots().length);
        out = new ObjectOutputStream(ConnectSocket.socket.getOutputStream());
        out.writeObject(ClientThread.data);
        out.flush();
    }

    @Override
    public void run() {
        //connectSocket.connect();

        try {

            while (true) {
                if (ConnectSocket.socket.isClosed()) {
                    break;
                }
                in = new ObjectInputStream(ConnectSocket.socket.getInputStream());
                DataSend dataSend = (DataSend) in.readObject();

                ClientThread.data = dataSend;

                if (dataSend.getPath() != null) {
                    WatchFolder watchFolder = new WatchFolder(ConnectSocket.socket);
                    new Thread(watchFolder).start();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();

            try {
                in.close();
                out.close();
                ConnectSocket.socket.close();
                System.out.println("Server" + " disconnected!");

            } catch (IOException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
