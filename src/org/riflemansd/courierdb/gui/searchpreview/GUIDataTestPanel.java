/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.riflemansd.courierdb.gui.searchpreview;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import org.riflemansd.jxsortabletable.JXSortableTable;

/**
 *
 * @author RiflemanSD
 */
public class GUIDataTestPanel extends javax.swing.JPanel {
    
    private String[] columnNames;
    private Object[] columnClasses;
    
    public JXSortableTable table;
    private SearchPanel sPanel;
    public int currSelectedRow;

    /**
     * Creates new form GUIDataTest
     * 
     * @param columns in format : VoucherID,Ημερομηνεία,Κατάσταση,Παραλαβή
     * @param columnClasses the value of each column to determinate the class. ex. "s", new Date(), "s", true
     */
    public GUIDataTestPanel(String columns, Object... columnClasses) {
        this.columnNames = columns.split(",");
        if (this.columnNames.length != columnClasses.length) {
            throw new UnsupportedOperationException("The column is "  + this.columnNames.length + " but the data values is " + columnClasses.length);
        }
        this.columnClasses = columnClasses;
        
        this.currSelectedRow = -1;

        initComponents();
        
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
    
    public void addMyMouseListener(MouseAdapter a) {
        this.addMouseListener(a);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchPanel = new javax.swing.JPanel();
        tablePanel = new javax.swing.JPanel();

        searchPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Φιλτάρισμα και Αναζήτηση"));

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 388, Short.MAX_VALUE)
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        tablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Πίνακας Αποτελεσμάτων"));

        javax.swing.GroupLayout tablePanelLayout = new javax.swing.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 159, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel searchPanel;
    private javax.swing.JPanel tablePanel;
    // End of variables declaration//GEN-END:variables
}