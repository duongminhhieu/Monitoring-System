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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author ASUS
 */
public class FormConnect extends javax.swing.JPanel {

    public static ConnectSocket connectSocket;
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
        ipServerText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        ConnectBtn.setText("Connect");
        ConnectBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ConnectBtnMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Monitoring Folder");

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

        ipServerText.setText("localhost");
        ipServerText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ipServerTextActionPerformed(evt);
            }
        });

        jLabel4.setText("IP Server");

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
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ipServerText, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .addComponent(portServerText)
                            .addComponent(portClientText))))
                .addGap(60, 60, 60))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(ipServerText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DisconnectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ConnectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ConnectBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ConnectBtnMouseClicked
        // TODO add your handling code here:
        try {
            connectSocket = new ConnectSocket(ipServerText.getText(), 8080);
            connectSocket.connect();
            clientThread = new ClientThread(connectSocket);
            clientThread.start();
            ClientThread.sendDataToServer();

            if (ConnectSocket.socket != null) {
                ConnectBtn.setVisible(false);
                DisconnectBtn.setVisible(true);
                portClientText.setText(Integer.toString(ConnectSocket.socket.getLocalPort()));
                portServerText.setText("8080");
                JOptionPane.showMessageDialog(this, "Kết nối thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Kiểm tra lại IPServer và Server đã được bật !!",
                        "Kết nối không thành công !", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Kiểm tra lại IPServer và \nCó thể Server chưa được bật !!",
                    "Kết nối không thành công !", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ConnectBtnMouseClicked

    public static void updateUIServerDisconnect() {
        ConnectBtn.setVisible(true);
        DisconnectBtn.setVisible(false);
        portClientText.setText("Port Client");
    }

    private void DisconnectBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DisconnectBtnMouseClicked
        // TODO add your handling code here:
        try {
            ConnectSocket.socket.close();
            
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

    private void ipServerTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ipServerTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ipServerTextActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JButton ConnectBtn;
    private static javax.swing.JButton DisconnectBtn;
    private javax.swing.JTextField ipServerText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private static javax.swing.JTextField portClientText;
    private javax.swing.JTextField portServerText;
    // End of variables declaration//GEN-END:variables
}
