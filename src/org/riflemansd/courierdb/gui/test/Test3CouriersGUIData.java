
package org.riflemansd.courierdb.gui.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import org.riflemansd.courierdb.CourierDBM;
import org.riflemansd.courierdb.gui.searchpreview.GUIDataTest;
import org.riflemansd.courierdb.utils.MyUtils;

/**
 * <h1>CourierDB</h1>
 * <h3>Class Test</h3> 
 * <p>Created: 27 Απρ 2016, 10:39:36 μμ</p>
 *
 * <p>Copyright © 2016 | RiflemanSD | All right reserved</p>
 *
 * @author RiflemanSD
 */
public class Test3CouriersGUIData extends MouseAdapter {
    GUIDataTest gui;
    
    public Test3CouriersGUIData() {
        gui = new GUIDataTest("ID,Όνομα", 1, "s");
        
        gui.setVisible(true);
        gui.table.addMouseListener(this);
        
        defineData();
    }
    
    public void defineData() {
        gui.clear();
        
        String[] data = CourierDBM.database.getCouriers();
        
        for (String d : data) {
            gui.addRow(MyUtils.stringToInt(d.split(",")[0]), d.split(",")[1]);
        }
        
        gui.sort();
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        int r = gui.table.rowAtPoint(e.getPoint());
        if (r >= 0 && r < gui.table.getRowCount()) {
            gui.table.setRowSelectionInterval(r, r);
        } else {
            gui.table.clearSelection();
        }
        
        gui.currSelectedRow = gui.table.getSelectedRow();
        if (gui.currSelectedRow < 0) {
            return;
        }
        if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
            JPopupMenu popup = new JPopupMenu();
            
            JMenuItem deleteItem = new JMenuItem("Delete");
            deleteItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int a = JOptionPane.showConfirmDialog(gui, "Πρόκειται να διαγραφοόυν όλα τα δεδομένα της γραμμής\nΕίστε σίγουρος ότι θέλετε να διαγραφούν?", "Διαγραφή Δεδομένων", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (a == 0) {
                        Object[] row = gui.table.getRowAt(gui.currSelectedRow);
                        
                        int did = (Integer) row[0];
                        
                        CourierDBM.database.deleteCourier(did);
                        
                        defineData();
                        JOptionPane.showMessageDialog(gui, "Τα δεδομένα της γραμμής διαγράφτηκαν", "Επιτυχία", JOptionPane.INFORMATION_MESSAGE);
                        
                    }
                }
            });
            popup.add(deleteItem);
            
            popup.show(e.getComponent(), e.getX(), e.getY());
        }
    }
}
