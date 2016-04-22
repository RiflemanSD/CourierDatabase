/* ~~ ﻿The GUIDataTest is part of BusinessProfit. ~~
 * 
 * The BusinessProfit's classes and any part of the code 
 * cannot be copied/distributed without 
 * the permission of Sotiris Doudis
 * 
 * Github - RiflemanSD - https://github.com/RiflemanSD
 * 
 * Copyright © 2016 Sotiris Doudis | All rights reserved
 * 
 * License for BusinessProfit project - in GREEK language
 * 
 * Οποιοσδήποτε μπορεί να χρησιμοποιήσει το πρόγραμμα για προσωπική του χρήση. 
 * Αλλά απαγoρεύεται η πώληση ή διακίνηση του προγράμματος σε τρίτους.
 * 
 * Aπαγορεύεται η αντιγραφή ή διακίνηση οποιοδήποτε μέρος του κώδικα χωρίς 
 * την άδεια του δημιουργού. 
 * Σε περίπτωση που θέλετε να χρεισημοποιήσετε κάποια κλάση ή μέρος του κώδικα.
 * Πρέπει να συμπεριλάβεται στο header της κλάσης τον δημιουργό και link στην
 * αυθεντική κλάση (στο github).
 * 
 * ~~ ﻿Information about BusinessProfit project - in GREEK language ~~
 *  
 * Το BusinessProfit είναι ένα project για την αποθήκευση και επεξεργασία
 * των εσόδων/εξόδων μίας επιχείρησης με σκοπό να μπορεί ο επιχειρηματίας να καθορήσει 
 * το καθαρό κέρδος της επιχείρησης. Καθώς και να κρατάει κάποια σημαντικά
 * στατιστικά στοιχεία για τον όγκο της εργασίας κτλ..
 *  
 * Το project δημιουργήθηκε από τον Σωτήρη Δούδη. Φοιτητή πληροφορικής του Α.Π.Θ
 * για προσωπική χρήση. Αλλά και για όποιον άλλον πιθανόν το χρειαστεί.
 * 
 * Το project προγραμματίστηκε σε Java (https://www.java.com/en/download/).
 * Με χρήση του NetBeans IDE (https://netbeans.org/)
 * Για να το τρέξετε πρέπει να έχετε εγκαταστήσει την java.
 *  
 * Ο καθένας μπορεί δωρεάν να χρησιμοποιήσει το project αυτό. Αλλά δεν επιτρέπεται
 * η αντιγραφή/διακήνηση του κώδικα, χωρίς την άδεια του Δημιουργού (Δείτε την License).
 * 
 * Github - https://github.com/RiflemanSD/BusinessProfit
 * 
 * 
 * Copyright © 2016 Sotiris Doudis | All rights reserved
 */
package org.riflemansd.courierdb.gui.test;

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

    private JXSortableTable table;
    private SearchPanel sPanel;
    private int currSelectedRow;

    /**
     * Creates new form GUIDataTest
     */
    public GUIDataTest() {
        currSelectedRow = -1;

        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        table = new JXSortableTable("VoucherID,Ημερομηνεία,Κατάσταση,Παραλαβή", "s", new Date(), "s", true);

        table.getColumn(0).sizeWidthToFit();
        table.getColumn(1).sizeWidthToFit();
        table.getColumn(2).sizeWidthToFit();
        table.getColumn(3).sizeWidthToFit();
        table.getColumn(4).sizeWidthToFit();

        tablePanel.setLayout(new BorderLayout());
        JScrollPane sc = new JScrollPane(table);
        tablePanel.add(sc, BorderLayout.CENTER);

        searchPanel.setLayout(new BorderLayout());
        sPanel = new SearchPanel(this);
        searchPanel.add(sPanel, BorderLayout.CENTER);

//        defineData();

        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public String[] rowToString(Object[] objects) {
        String[] datas = new String[8];

        datas[0] = (String) objects[0];
//        datas[1] = BusinessProfit.database.dateToString((Date) objects[1]);
        datas[2] = (String) objects[2];
        datas[3] = (String) objects[3];
        datas[4] = "" + objects[4];
        datas[5] = (String) objects[5];
        datas[6] = "" + objects[6];
        datas[7] = "" + objects[7];

        return datas;
    }

    public Object[] stringToRow(String[] objects) {
        Object[] datas = new Object[8];

        datas[0] = Integer.parseInt(objects[0]);
//        datas[1] = BusinessProfit.database.getDate(objects[1]);
        datas[2] = objects[2];
        datas[3] = objects[3];
        datas[4] = Double.parseDouble(objects[4]);
        datas[5] = objects[5];
        datas[6] = Integer.parseInt(objects[6]);
        datas[7] = Integer.parseInt(objects[7]);

        return datas;
    }

//    public void defineData() {
//        double inValue = 0;
//        double outValue = 0;
//
//        //table.deleteAllRows();
//        table.removeAllRows();
//        String[] in = BusinessProfit.database.getIn().split("\n");
//        String[] out = BusinessProfit.database.getOut().split("\n");
//        String[] packIn = BusinessProfit.database.getPackIn().split("\n");
//        
//        try {
//            for (String i : in) {
//                //System.out.println(i);
//                Object[] data = this.inToTableRow(i);
//                if (data != null) {
//                    table.addRow(data);
//                    inValue += (double) data[4];
//                }
//            }
//        }
//        catch (Exception e) {
//            System.out.println("Error: " + e);
//        }
//        try {
//            for (String p : packIn) {
//                Object[] data = this.packInToTableRow(p);
//                if (data != null) {
//                    table.addRow(data);
//                    inValue += (double) data[4];
//                }
//            }
//        }
//        catch (Exception e) {
//            System.out.println("Error: " + e);
//        }
//        try {
//            for (String o : out) {
//                Object[] data = this.outToTableRow(o);
//                if (data != null) {
//                    table.addRow(data);
//                    outValue += (double) data[4];
//                }
//            }
//        }
//        catch (Exception e) {
//            System.out.println("Error: " + e);
//        }
//        
//        table.sort();
//    }

    private Object[] inToTableRow(String in) {
        //"ID,Ημερομηνία,Κατηγορία,Έσοδο/Έξοδο,Ποσό(€),Αιτιολογία,Παραδόσεις,Παραλαβές"

        String cat = sPanel.getCategory();
        int index = sPanel.getInOut();

        String[] ind = in.split(",");
        Object[] objects = new Object[6];

//        objects[2] = BusinessProfit.database.getCategory(Integer.parseInt(ind[1])).getName();
        if (!cat.equals("Όλες")) {
            if (!cat.equals((String) objects[2])) {
                return null;
            }
        }
        if (index != 0) {
            if (index == 2) {
                return null;
            }
        }

//        objects[1] = BusinessProfit.database.getDate(ind[4]);
        if (!checkDate((Date) objects[1])) {
            return null;
        }

        objects[0] = ind[0];
        objects[3] = "Έσοδο";
        objects[4] = Double.parseDouble(ind[2]);
        objects[5] = ind[3];

        return objects;
    }

    private Object[] outToTableRow(String out) {
        //"ID,Ημερομηνία,Κατηγορία,Έσοδο/Έξοδο,Ποσό(€),Αιτιολογία,Παραδόσεις,Παραλαβές"
        String cat = sPanel.getCategory();
        int index = sPanel.getInOut();

        String[] ind = out.split(",");
        Object[] objects = new Object[6];

//        objects[2] = BusinessProfit.database.getCategory(Integer.parseInt(ind[1])).getName();
        if (!cat.equals("Όλες")) {
            if (!cat.equals((String) objects[2])) {
                return null;
            }
        }
        if (index != 0) {
            if (index == 1) {
                return null;
            }
        }

//        objects[1] = BusinessProfit.database.getDate(ind[4]);
        if (!checkDate((Date) objects[1])) {
            return null;
        }

        objects[0] = ind[0];
        objects[3] = "Έξοδο";
        objects[4] = Double.parseDouble(ind[2]);
        objects[5] = ind[3];

        return objects;

    }

    private Object[] packInToTableRow(String packIn) {
        //"ID,Ημερομηνία,Κατηγορία,Έσοδο/Έξοδο,Ποσό(€),Αιτιολογία,Παραδόσεις,Παραλαβές"
        String cat = sPanel.getCategory();
        int index = sPanel.getInOut();

        String[] ind = packIn.split(",");
        Object[] objects = new Object[8];

//        objects[2] = BusinessProfit.database.getCategory(Integer.parseInt(ind[1])).getName();
        if (!cat.equals("Όλες")) {
            if (!cat.equals((String) objects[2])) {
                return null;
            }
        }
        if (index != 0) {
            if (index == 2) {
                return null;
            }
        }

//        objects[1] = BusinessProfit.database.getDate(ind[6]);

        if (!checkDate((Date) objects[1])) {
            return null;
        }

        objects[0] = ind[0];
        objects[3] = "Έσοδο";
        objects[4] = Double.parseDouble(ind[2]);
        objects[5] = ind[3];
        objects[6] = Integer.parseInt(ind[4]);
        objects[7] = Integer.parseInt(ind[5]);

        return objects;

    }

    private boolean checkDate(Date date) {
        Date[] dates = sPanel.getDates();

        if (this.sPanel.isDateSearch()) {
            if (dates[0].compareTo(dates[1]) == 0) {
                if (dates[0].compareTo(date) != 0) {
                    return false;
                }
            } else {
                if (dates[0].compareTo(date) != 0 && dates[1].compareTo(date) != 0) {
                    if (!((date).after(dates[0]) && (date).before(dates[1]))) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     *
     * @param o1 - 27-1-2016
     * @param o2 - 28-1-2016
     * @return o2 &gt; o1 = -1, o2 &lt; o1 = 1, o2 == o1 = 0
     */
    private int compare(Date o1, Date o2) {
        long n1 = o1.getTime();
        long n2 = o2.getTime();
        if (n1 < n2) {
            return -1;
        } else if (n1 > n2) {
            return 1;
        } else {
            return 0;
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

        tablePanel = new javax.swing.JPanel();
        searchPanel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        FileMenu = new javax.swing.JMenu();
        MenuItemExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 102, 0));

        tablePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout tablePanelLayout = new javax.swing.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 201, Short.MAX_VALUE)
        );

        getContentPane().add(tablePanel, java.awt.BorderLayout.CENTER);

        searchPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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
                GUIDataTest test = new GUIDataTest();
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
