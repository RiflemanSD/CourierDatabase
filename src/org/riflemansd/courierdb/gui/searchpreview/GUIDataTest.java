package org.riflemansd.courierdb.gui.searchpreview;

import java.awt.BorderLayout;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import org.riflemansd.jxsortabletable.JXSortableTable;


/** <h1>﻿GUIDataTest</h1>
 * 
 * <p></p>
 * 
 * <p>Last Update: 01/02/2016</p>
 * <p>Author: <a href=https://github.com/RiflemanSD>RiflemanSD</a></p>
 * 
 * <p>Copyright © 2016 Sotiris Doudis | All rights reserved</p>
 * 
 * @version 1.0.7
 * @author RiflemanSD
 */
public class GUIDataTest extends javax.swing.JFrame {

    private String[] columnNames;
    private Object[] columnClasses;
    
    private JXSortableTable table;
    private SearchPanel sPanel;
    private int currSelectedRow;

    /**
     * Creates new form GUIDataTest
     * 
     * @param columns in format : VoucherID,Ημερομηνεία,Κατάσταση,Παραλαβή
     * @param columnClasses the value of each column to determinate the class. ex. "s", new Date(), "s", true
     */
    public GUIDataTest(String columns, Object... columnClasses) {
        this.columnNames = columns.split(",");
        if (this.columnNames.length != columnClasses.length) {
            throw new UnsupportedOperationException("The column is "  + this.columnNames.length + " but the data values is " + columnClasses.length);
        }
        this.columnClasses = columnClasses;
        
        this.currSelectedRow = -1;

        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        table = new JXSortableTable(columns, columnClasses);

//        for (int i = 0; i < this.columnNames.length; i++) {
//            table.getColumn(i).sizeWidthToFit();
//        }
//        table.getColumn(0).sizeWidthToFit();
//        table.getColumn(1).sizeWidthToFit();
//        table.getColumn(2).sizeWidthToFit();
//        table.getColumn(3).sizeWidthToFit();

        tablePanel.setLayout(new BorderLayout());
        JScrollPane sc = new JScrollPane(table);
        tablePanel.add(sc, BorderLayout.CENTER);

        searchPanel.setLayout(new BorderLayout());
        sPanel = new SearchPanel(columns, columnClasses);
        searchPanel.add(sPanel, BorderLayout.CENTER);

//        defineData();

        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public void clear() {
        this.table.removeAllRows();
    }
    
    public boolean addRow(Object... data) {
        try {
            if (data != null) {
                this.table.addRow(data);
                return true;
            }
            else {
                return false;
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }
    
    public void sort() {
        this.table.sort();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tablePanel = new javax.swing.JPanel();
        searchPanel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        FileMenu = new javax.swing.JMenu();
        MenuItemExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 102, 0));

        tablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Πίνακας Αποτελεσμάτων", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, java.awt.Color.black));

        javax.swing.GroupLayout tablePanelLayout = new javax.swing.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 388, Short.MAX_VALUE)
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 159, Short.MAX_VALUE)
        );

        getContentPane().add(tablePanel, java.awt.BorderLayout.CENTER);

        searchPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Φιλτάρισμα και Αναζήτηση"));

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        getContentPane().add(searchPanel, java.awt.BorderLayout.PAGE_START);

        FileMenu.setText("File");

        MenuItemExit.setText("Close Window");
        MenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemExitActionPerformed(evt);
            }
        });
        FileMenu.add(MenuItemExit);

        jMenuBar1.add(FileMenu);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_MenuItemExitActionPerformed

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
            java.util.logging.Logger.getLogger(GUIDataTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIDataTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIDataTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIDataTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUIDataTest test = new GUIDataTest("VoucherID,Ημερομηνεία,Κατάσταση,Παραλαβή,number", "s", new Date(), "s", true, 1.5);
                test.setVisible(true);

                test.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu FileMenu;
    private javax.swing.JMenuItem MenuItemExit;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JPanel tablePanel;
    // End of variables declaration//GEN-END:variables
}
