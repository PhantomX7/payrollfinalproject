/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payrollfinalproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Phantom
 */
public class DlgEmployeeList extends javax.swing.JDialog {

    Connection myConn = null;
    PreparedStatement myStmt = null;
    ResultSet myRs = null;

    public DlgEmployeeList(java.awt.Frame parent, boolean modal, Connection conn) {
        super(parent, modal);
        initComponents();
        myConn = conn;
        setLocationRelativeTo(null);
        tableEmployeeInitData();
        tableSelectionListener();
    }

    private void tableEmployeeInitData() {
        DefaultTableModel tableModel = (DefaultTableModel) tblEmployee.getModel();
        tableModel.setRowCount(0);

        try {
            // Prepare statement
            myStmt = myConn.prepareStatement("select * from data_karyawan");
            
            // Execute SQL query
            myRs = myStmt.executeQuery();

            // Process result set
            if (myRs.isBeforeFirst()) {
                while (myRs.next()) {

                    Integer id = myRs.getInt("id_karyawan");
                    String name = myRs.getString("nama");
                    String nik = myRs.getString("nik");
                    String id_department = myRs.getString("id_department");
                    String jabatan = myRs.getString("jabatan");
                    String tipe_karyawan = myRs.getString("tipe_karyawan");

                    Object data[] = {id, name, nik, id_department, jabatan, tipe_karyawan};
                    tableModel.addRow(data);
                }
            }
            
            myRs.close();
            myStmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FrmMainPayroll.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void tableSelectionListener() {
        ListSelectionListener listener = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int row = tblEmployee.getSelectedRow();
                if (row >= 0) {
                    FrmMainPayroll.txtEmployeeTransaction.setText(tblEmployee.getValueAt(row, 2).toString());
                    
                    dispose();
                }
            }
        };
        tblEmployee.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblEmployee.getSelectionModel().addListSelectionListener(listener);
    }   

    private void removeTableData() {
        DefaultTableModel tableModel = (DefaultTableModel) tblEmployee.getModel();
        tableModel.setRowCount(0);
    }

    private void searchByEmployeeName() {
        try {
            removeTableData();
            // Prepare statement
            myStmt = myConn.prepareStatement("select * from data_karyawan where nama like ?");
            myStmt.setString(1, "%" + txtEmployeeListSearch.getText().trim() + "%");
            // Execute SQL query
            myRs = myStmt.executeQuery();
            

            if (myRs.isBeforeFirst()) { // check is resultset not empty
                DefaultTableModel tableModel = (DefaultTableModel) tblEmployee.getModel();
                while (myRs.next()) {

                    Integer id = myRs.getInt("id_karyawan");
                    String name = myRs.getString("nama");
                    String nik = myRs.getString("nik");
                    String id_department = myRs.getString("id_department");
                    String jabatan = myRs.getString("jabatan");
                    String tipe_karyawan = myRs.getString("tipe_karyawan");

                    Object data[] = {id, name, nik, id_department, jabatan, tipe_karyawan};
                    tableModel.addRow(data);
                }
            }

            myRs.close();
            myStmt.close();
        } catch (SQLException ex) {
            System.out.println("Error:\n" + ex.getLocalizedMessage());
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
        jScrollPane3 = new javax.swing.JScrollPane();
        tblEmployee = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtEmployeeListSearch = new javax.swing.JTextField();
        btnEmployeeListSearch = new javax.swing.JButton();
        btnEmployeeListReset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblEmployee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Karyawan", "Nama", "NIK", "Departemen", "Jabatan", "tipe karyawan"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblEmployee);

        jLabel1.setText("Search Name");

        btnEmployeeListSearch.setText("Search");
        btnEmployeeListSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmployeeListSearchActionPerformed(evt);
            }
        });

        btnEmployeeListReset.setText("Reset");
        btnEmployeeListReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmployeeListResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmployeeListSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEmployeeListSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEmployeeListReset)
                        .addGap(29, 29, 29))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtEmployeeListSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEmployeeListSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEmployeeListReset, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(98, 98, 98)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEmployeeListSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmployeeListSearchActionPerformed
        searchByEmployeeName();
    }//GEN-LAST:event_btnEmployeeListSearchActionPerformed

    private void btnEmployeeListResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmployeeListResetActionPerformed
        tableEmployeeInitData();
    }//GEN-LAST:event_btnEmployeeListResetActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEmployeeListReset;
    private javax.swing.JButton btnEmployeeListSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblEmployee;
    private javax.swing.JTextField txtEmployeeListSearch;
    // End of variables declaration//GEN-END:variables
}
