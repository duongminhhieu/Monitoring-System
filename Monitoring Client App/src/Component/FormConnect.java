/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Component;

import Model.ConnectSocket;
import Model.DataSend;
import Model.FolderInfo;
import Thread.ClientThread;
import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class FormConnect extends javax.swing.JPanel {

    public static ConnectSocket connectSocket;
    public static DataSend dataSend;
    public static FolderInfo folderInfo;
    public ClientThread clientThread;

    /**
     * Creates new form FormConnect
     */
    public FormConnect() {
        initComponents();
        init();
    }

    public void init() {
        DisconnectBtn.setVisible(false);

        File[] roots = File.listRoots();
        dataSend = new DataSend(roots, null, 0, null); // khoi tao data

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ConnectBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        DisconnectBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        portClientText = new javax.swing.JTextField();
        portServerText = new javax.swing.JTextField();

        ConnectBtn.setText("Connect");
        ConnectBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ConnectBtnMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Giám sát thư mục ");

        DisconnectBtn.setText("Disconnect");
        DisconnectBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DisconnectBtnMouseClicked(evt);
            }
        });

        jLabel2.setText("Port Client: ");

        jLabel3.setText("Port Server: ");

        portClientText.setEditable(false);
        portClientText.setText("Port Client");

        portServerText.setEditable(false);
        portServerText.setText("Port Server");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DisconnectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ConnectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(portClientText)
                            .addComponent(portServerText, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(portClientText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(portServerText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DisconnectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ConnectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ConnectBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ConnectBtnMouseClicked
        // TODO add your handling code here:
        try {

            connectSocket = new ConnectSocket("localhost", 8080);
            clientThread = new ClientThread(connectSocket);
            clientThread.start();

            ConnectBtn.setVisible(false);
            DisconnectBtn.setVisible(true);
            JOptionPane.showMessageDialog(this, "Kết nối thành công");
            portClientText.setText(Integer.toString(ConnectSocket.socket.getLocalPort()));
            portServerText.setText("8080");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Kết nối không thành công !",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ConnectBtnMouseClicked

    private void DisconnectBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DisconnectBtnMouseClicked
        // TODO add your handling code here:
        try {

            clientThread.getConnectSocket().CloseSocket();
            ConnectBtn.setVisible(true);
            DisconnectBtn.setVisible(false);
            JOptionPane.showMessageDialog(this, "Ngắt kết nối thành công");
            portClientText.setText("Port Client");
            portServerText.setText("8080");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ngắt kết nối không thành công !",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_DisconnectBtnMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ConnectBtn;
    private javax.swing.JButton DisconnectBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField portClientText;
    private javax.swing.JTextField portServerText;
    // End of variables declaration//GEN-END:variables
}
