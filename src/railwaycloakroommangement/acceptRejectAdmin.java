/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package railwaycloakroommangement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author prate
 */
public class acceptRejectAdmin extends javax.swing.JFrame {

    String stid;
    Connection connection = null;
    PreparedStatement prp = null;
    ResultSet result = null;
    DefaultTableModel Table = new DefaultTableModel();
    int Lval = 0;

    /**
     * Creates new form acceptRejectAdmin
     */
    public acceptRejectAdmin() {
        initComponents();
    }

    public acceptRejectAdmin(String stid) {
        initComponents();
        this.stid = stid;
        Object columns[] = {"PNR", "NO OF LOCKERS", "STATUS",};
        System.out.println(stid);
        Table.setColumnIdentifiers(columns);
        jTable2.setModel(Table);
        connection = connector.ConnectDB();
        String sql = "select pnr,no_of_locker,status from transaction where station_id ='" + stid + "' and status='pending'";
        String sql2 = "select no_locker from locker where st_id='" + stid + "'";
        try {
            prp = connection.prepareStatement(sql);
            result = prp.executeQuery();

            Object columnData[] = new Object[4];
            while (result.next()) {

                columnData[0] = result.getInt("pnr");
                columnData[1] = result.getInt("no_of_Locker");
                columnData[2] = result.getString("status");

                Table.addRow(columnData);
            }

            prp = connection.prepareStatement(sql2);
            result = prp.executeQuery();

            result.next();
            Lval = result.getInt("no_locker");
            String val = "" + Lval;
            jLabel4.setText(val);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void process(String action) {
        connection = connector.ConnectDB();
        String stime = jTextField1.getText();
        String etime = jTextField2.getText();
        int pnr = Integer.parseInt(jTextField3.getText());

        String sql = "select pnr,no_of_locker,status from transaction where pnr ='" + pnr + "' and station_id='"+stid+"'";
        String sql1 = "select source_station,destination_station from pnr_details where pnr ='" + pnr + "'";
        try {
            
            prp = connection.prepareStatement(sql1);
            result = prp.executeQuery();
            result.next();
            String sst = result.getString("source_station");
            String dst = result.getString("destination_station");
            prp = connection.prepareStatement(sql);
            result = prp.executeQuery();
            result.next();

            int val = result.getInt("no_of_locker");
            
            if (val > Lval && action.equals("Accepted")) {
                JOptionPane.showMessageDialog(null, "No Available required Lockers");
             

            } else {
                if(action.equals("Accepted")){
                Lval -= val;
                }
                String sql2 = "update locker set no_locker =" + Lval + " where st_id='" + stid + "'";
                String sql3 = "update transaction set status ='" + action + "' where pnr='" + pnr + "' and station_id='"+stid+"'";
                String sql4 = "insert into assigned_locker(station_id,start_time,end_time,no_locker,pnr) values(?,?,?,?,?) ";
                prp = connection.prepareStatement(sql2);
                prp.execute();
                prp = connection.prepareStatement(sql3);
                prp.execute();
                
                prp = connection.prepareStatement(sql4);
                prp.setString(1, stid);
                prp.setString(2, stime);
                prp.setString(3, etime);
                prp.setInt(4, val);
                prp.setInt(5, pnr);

                prp.execute();
                            JOptionPane.showMessageDialog(null, action+"d successfully");

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 102, 51,80));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel2.setText("REQUESTS");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 194, -1));

        jButton1.setBackground(new java.awt.Color(51, 153, 255));
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 16, -1, -1));

        jTable2.setBackground(new java.awt.Color(255, 153, 0));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}

            },
            new String [] {
                "STATION", "NO LOCKER"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 172, -1, 131));

        jLabel3.setText("Available Lockers :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, 46, 104, -1));

        jLabel4.setText("0");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(506, 46, 37, -1));

        jLabel5.setText("Start Time");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 86, -1, -1));

        jLabel6.setText("End Time");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 120, 54, -1));

        jLabel7.setText("PNR");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 86, 37, -1));

        jTextField1.setBackground(new java.awt.Color(255, 153, 51));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(107, 83, 71, -1));

        jTextField2.setBackground(new java.awt.Color(255, 153, 0));
        jTextField2.setForeground(new java.awt.Color(255, 153, 51));
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(107, 117, 71, -1));

        jTextField3.setBackground(new java.awt.Color(255, 153, 0));
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(395, 83, 71, -1));

        jButton2.setBackground(new java.awt.Color(0, 153, 0));
        jButton2.setText("Accept");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(305, 117, -1, -1));

        jButton3.setBackground(new java.awt.Color(255, 0, 0));
        jButton3.setText("Reject");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(395, 117, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 180, 550, 370));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/420210.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1909, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        adminHome u = new adminHome(stid);
        u.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        process("Accepted");
        acceptRejectAdmin ar = new acceptRejectAdmin(stid);
        ar.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        process("Rejected");
         acceptRejectAdmin ar = new acceptRejectAdmin(stid);
        ar.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(acceptRejectAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(acceptRejectAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(acceptRejectAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(acceptRejectAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new acceptRejectAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}