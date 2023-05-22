/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Thread;

import Component.FormConnect;
import Model.ConnectSocket;
import Model.DataSend;
import Model.WatchFolder;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
        out = new ObjectOutputStream(ConnectSocket.socket.getOutputStream());
        out.writeObject(ClientThread.data);
        out.flush();
    }

    @Override
    public void run() {
        //connectSocket.connect();

        try {

            while (true) {

                in = new ObjectInputStream(ConnectSocket.socket.getInputStream());
                DataSend dataSend = (DataSend) in.readObject();

                if (dataSend.getDirectoryNode() != null) {
                    File directory = new File(dataSend.getDirectoryNode().getUserObject().toString());
                    File[] files = directory.listFiles();
                    if (files != null) {
                        List<File> newLstFile = new ArrayList<File>();
                        for (File child : files) {
                            if (child.isDirectory()) {
                                newLstFile.add(child);
                            }
                        }

                        dataSend.setLstFilesOfNode(newLstFile.toArray((File[]) new File[0]));

                    }
                    ClientThread.data = dataSend;
                    ClientThread.sendDataToServer();
                } else {
                    ClientThread.data = dataSend;
                }
                if (dataSend.getPath() != null) {
                    WatchFolder watchFolder = new WatchFolder(ConnectSocket.socket);
                    new Thread(watchFolder).start();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();

            try {

                if (!ConnectSocket.socket.isClosed()) {
                    in.close();
                    out.close();
                    ConnectSocket.socket.close();
                    FormConnect.updateUIServerDisconnect();
                    System.out.println("Server" + " disconnected!");
                    JOptionPane.showMessageDialog(null, "Server disconnected!", "Server ngắt kết nối", JOptionPane.WARNING_MESSAGE);

                }

            } catch (IOException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
