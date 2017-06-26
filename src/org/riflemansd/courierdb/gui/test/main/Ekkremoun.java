
package org.riflemansd.courierdb.gui.test.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Date;
import org.riflemansd.courierdb.CourierDBM;
import org.riflemansd.courierdb.entrys.dbs.VoucherS;
import org.riflemansd.courierdb.gui.searchpreview.GUIDataTestPanelNosearch;
import org.riflemansd.courierdb.utils.MyUtils;

/**
 *
 * @author rifleman
 */
public class Ekkremoun extends javax.swing.JPanel {
    
    private final GUIDataTestPanelNosearch gui;
    private String date;
    
    /** Creates new form Ekkremoun */
    public Ekkremoun() {
        initComponents();
        
        date = MyUtils.dateToString(new Date()).split(" ")[0];
        System.out.println(date);
        
        gui = new GUIDataTestPanelNosearch("VoucherID,Παραλαβή", "s", "s");
        gui.setTitleName("Voucher που εκκρεμούν", Color.red);
        
        this.add(gui, BorderLayout.CENTER);
        
        defineData();
        
        gui.autoResizeColumn(0);
        gui.autoResizeColumn(1);
    }

    public void defineData() {
        gui.clear();
        
        this.date = "24-05-2016";
        String[] data = CourierDBM.database.getVouchersByTime(this.date);
        
        for (String d : data) {
            String[] line = d.split(",");
            
            int voucherid = MyUtils.stringToInt(line[0]); //voucherid
            System.out.println(line[0]);
            VoucherS voucher = CourierDBM.database.getVoucher(voucherid);
            
            if (voucher != null) {
                String is = "Όχι";
                if (voucher.isReceipt()){
                    is = "Ναι";
                } 
                gui.addRow(voucher.getName(), is);
            }
        }
        gui.sort();
    }

    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
