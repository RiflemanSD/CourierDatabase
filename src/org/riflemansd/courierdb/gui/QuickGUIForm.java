/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.riflemansd.courierdb.gui;

import java.util.ArrayList;
import java.util.Date;
import org.riflemansd.courierdb.CourierDBM;
import org.riflemansd.courierdb.entrys.dbs.PackageInS;
import org.riflemansd.courierdb.entrys.dbs.VoucherInfoS;
import org.riflemansd.courierdb.entrys.dbs.VoucherS;
import org.riflemansd.courierdb.utils.MyUtils;

/**
 *
 * @author sotir
 */
public final class QuickGUIForm extends javax.swing.JFrame{
    private MyAutoCompleteListener l;
    private ArrayList<City> citys;
    private String nameCitys;
    /**
     * Creates new form QuickGUIForm
     */
    public QuickGUIForm() {
        this.citys = new ArrayList<>();
        l = new MyAutoCompleteListener(this);
        
        initComponents();
        
        this.cityTF.addAutoCompleteListener(l);
        
        initAutoCompletes();
        
        //this.addressTF.setWords(pefka + filiro + asvestohori + xortiatis);
        this.cityTF.setWords(nameCitys.split("\n"));
        
        Date d1 = new Date();d1.setHours(9);d1.setMinutes(0);
        Date d2 = new Date();d2.setHours(14);d2.setMinutes(0);
        Date d3 = new Date();d3.setHours(17);d3.setMinutes(0);
        Date d4 = new Date();d4.setHours(21);d4.setMinutes(30);
        
        this.timeFrom.setValue(d1);
        this.timeTo.setValue(d2);
        this.orTimeFrom.setValue(d3);
        this.orTimeTo.setValue(d4);
        
        this.jLabel10.setVisible(false);
        this.jLabel12.setVisible(false);
        this.demaCheck.setVisible(false);
        this.fakelosCheck.setVisible(false);
        
        this.rootPane.setDefaultButton(this.insertButton);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
     
    public void initAutoCompletes() {
        nameCitys = "Πεύκα\n" +
                "Ασβεστοχώρι\n" +
                "Φίλυρο\n" +
                "Χορτιάτης\n" +
                "Εξοχή";
        
        this.citys.add(new City("Πεύκα", 57010, "pefka.txt", "pefka.osm"));
        this.citys.add(new City("Ασβεστοχώρι", 57010, "asvestohori.txt", "asvestohori.osm"));
        this.citys.add(new City("Φίλυρο", 57010, "filiro.txt", "filiro.osm"));
        this.citys.add(new City("Χορτιάτης", 57010, "xortiatis.txt", "xortiatis.osm"));
        
        
        //this.citys.add(new City("Εξοχή", 57010, "exohi.txt", "exohi.osm"));
        
        /*this.citys.add(new City("Συκιές", 56626, "exohi.txt", "exohi.osm"));
        this.citys.add(new City("Συκιές", 56625, "exohi.txt", "exohi.osm"));
        
        this.citys.add(new City("Πολίχνη", 56429, "exohi.txt", "exohi.osm"));
        this.citys.add(new City("Πολίχνη", 56533, "exohi.txt", "exohi.osm"));
        this.citys.add(new City("Πολίχνη-Μετέωρα", 56532, "exohi.txt", "exohi.osm"));
        
        this.citys.add(new City("Άγιος Παύλος", 55438, "exohi.txt", "exohi.osm"));
        
        this.citys.add(new City("Νεάπολη", 56728, "exohi.txt", "exohi.osm"));
        this.citys.add(new City("Νεάπολη", 56727, "exohi.txt", "exohi.osm"));*/
    }
    
    public void onCityAutoComplete(String word) {
        //System.out.println("lol " + word);
        if (word.equals("Πεύκα")) {
            //System.out.println(pefka);
            this.addressTF.setWords(this.citys.get(0).getStreetNames());
        }
        else if (word.equals("Ασβεστοχώρι")) {
            this.addressTF.setWords(this.citys.get(1).getStreetNames());
        }
        else if (word.equals("Φίλυρο")) {
            this.addressTF.setWords(this.citys.get(2).getStreetNames());
        }
        else if (word.equals("Χορτιάτης")) {
            this.addressTF.setWords(this.citys.get(3).getStreetNames());
        }
        else if (word.equals("Εξοχή")) {
            //this.addressTF.setWords(this.citys.get(4).getStreetNames());
            
        }
        //else {
            //this.addressTF.setWords((pefka + filiro + asvestohori + xortiatis).split("\n"));
        //}
    }
    
    private boolean insertToDB() {
        System.out.println("Insert..");
        String voucher = voucherTF.getValue();
        
        VoucherS voucherS = new VoucherS(voucher);
        
        if (!CourierDBM.database.saveVoucher(voucherS)) return false;
        voucherS = CourierDBM.database.getVoucher(voucher);
        
        String address = addressTF.getValue();
        String city = cityTF.getValue();
        //String pcode = pcodeTF.getValue();
        String name = nameTF.getValue();
        String phone = phoneTF.getValue();
        String time = "";
        if (timeCheck.isSelected()) time += timeFrom.getValueDate().getHours()+":"+timeFrom.getValueDate().getMinutes() + "-" + timeTo.getValueDate().getHours()+":"+timeTo.getValueDate().getMinutes();
        if (orTimeCheck.isSelected() && timeCheck.isSelected()) time += "," + orTimeFrom.getValueDate().getHours()+":"+orTimeFrom.getValueDate().getMinutes() + "-" + orTimeTo.getValueDate().getHours()+":"+orTimeTo.getValueDate().getMinutes();
        else time += time += orTimeFrom.getValueDate().getHours()+":"+orTimeFrom.getValueDate().getMinutes() + "-" + orTimeTo.getValueDate().getHours()+":"+orTimeTo.getValueDate().getMinutes();
        boolean emergency = epeigonCheck.isSelected();
        
        VoucherInfoS voucherinfo = new VoucherInfoS(voucherS.getId(), address, city, name, time, emergency, phone);
        System.out.println(voucherinfo);
        CourierDBM.database.saveVoucherInfo(voucherinfo);
        
        CourierDBM.database.savePackageIn(new PackageInS(voucherS.getId(), -1, MyUtils.currentTime()));
        
        return true;
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
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cityTF = new org.riflemansd.courierdb.gui.JComboBoxAutoCompletePanel();
        phoneTF = new org.riflemansd.courierdb.gui.JComboBoxAutoCompletePanel();
        pcodeTF = new org.riflemansd.courierdb.gui.JComboBoxAutoCompletePanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nameTF = new org.riflemansd.courierdb.gui.JComboBoxAutoCompletePanel();
        addressTF = new org.riflemansd.courierdb.gui.JComboBoxAutoCompletePanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        voucherTF = new org.riflemansd.courierdb.gui.JComboBoxAutoCompletePanel();
        jPanel3 = new javax.swing.JPanel();
        timeTo = new org.riflemansd.courierdb.gui.TimePickerPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        fakelosCheck = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        orTimeTo = new org.riflemansd.courierdb.gui.TimePickerPanel();
        orTimeFrom = new org.riflemansd.courierdb.gui.TimePickerPanel();
        jLabel4 = new javax.swing.JLabel();
        epeigonCheck = new javax.swing.JCheckBox();
        orTimeCheck = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        timeFrom = new org.riflemansd.courierdb.gui.TimePickerPanel();
        timeCheck = new javax.swing.JCheckBox();
        demaCheck = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        insertButton = new javax.swing.JButton();
        printButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("ΤΚ");

        cityTF.setPreferredSize(new java.awt.Dimension(127, 28));

        phoneTF.setPreferredSize(new java.awt.Dimension(127, 28));

        pcodeTF.setPreferredSize(new java.awt.Dimension(127, 28));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("ΔΙΕΥΘ.");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("ΠΟΛΗ");

        nameTF.setPreferredSize(new java.awt.Dimension(127, 28));

        addressTF.setPreferredSize(new java.awt.Dimension(127, 28));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("ΤΗΛ");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("ΟΝΟΜΑ");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel13.setText("Voucher");

        voucherTF.setPreferredSize(new java.awt.Dimension(127, 28));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pcodeTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(phoneTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addressTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cityTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(voucherTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(voucherTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(3, 3, 3))
                                    .addComponent(addressTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cityTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(3, 3, 3)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(pcodeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(nameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("-");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("ΦΑΚΕΛΟΣ");

        fakelosCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fakelosCheckActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("ΔΕΜΑ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("-");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("ΕΠΕΙΓΟΝ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("ΩΡΑ");

        timeCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeCheckActionPerformed(evt);
            }
        });

        demaCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                demaCheckActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("ή");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(6, 6, 6)
                        .addComponent(epeigonCheck))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel9)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(orTimeCheck)
                            .addComponent(timeCheck))
                        .addGap(1, 1, 1)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(orTimeFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(orTimeTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(timeFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(timeTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jLabel10)
                                .addGap(6, 6, 6)
                                .addComponent(demaCheck)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel12)
                                .addGap(6, 6, 6)
                                .addComponent(fakelosCheck)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timeTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timeFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timeCheck))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(orTimeTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(orTimeFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(orTimeCheck))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(epeigonCheck)
                    .addComponent(jLabel2)
                    .addComponent(demaCheck)
                    .addComponent(jLabel10)
                    .addComponent(fakelosCheck)
                    .addComponent(jLabel12))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        insertButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        insertButton.setText("INSERT");
        insertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertButtonActionPerformed(evt);
            }
        });

        printButton.setText("Print");
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(insertButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(printButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(insertButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(printButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void insertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertButtonActionPerformed
        this.insertToDB();
    }//GEN-LAST:event_insertButtonActionPerformed

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        
    }//GEN-LAST:event_printButtonActionPerformed

    private void cityTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cityTFKeyTyped
        
    }//GEN-LAST:event_cityTFKeyTyped

    private void addressTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addressTFKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_addressTFKeyTyped

    private void addressTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addressTFKeyReleased
        
    }//GEN-LAST:event_addressTFKeyReleased

    private void fakelosCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fakelosCheckActionPerformed
        //this.demaCheck.setSelected(false);
    }//GEN-LAST:event_fakelosCheckActionPerformed

    private void demaCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_demaCheckActionPerformed
        //this.fakelosCheck.setSelected(false);
    }//GEN-LAST:event_demaCheckActionPerformed

    private void timeCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeCheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_timeCheckActionPerformed

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
            java.util.logging.Logger.getLogger(QuickGUIForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuickGUIForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuickGUIForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuickGUIForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuickGUIForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.riflemansd.courierdb.gui.JComboBoxAutoCompletePanel addressTF;
    private org.riflemansd.courierdb.gui.JComboBoxAutoCompletePanel cityTF;
    private javax.swing.JCheckBox demaCheck;
    private javax.swing.JCheckBox epeigonCheck;
    private javax.swing.JCheckBox fakelosCheck;
    private javax.swing.JButton insertButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private org.riflemansd.courierdb.gui.JComboBoxAutoCompletePanel nameTF;
    private javax.swing.JCheckBox orTimeCheck;
    private org.riflemansd.courierdb.gui.TimePickerPanel orTimeFrom;
    private org.riflemansd.courierdb.gui.TimePickerPanel orTimeTo;
    private org.riflemansd.courierdb.gui.JComboBoxAutoCompletePanel pcodeTF;
    private org.riflemansd.courierdb.gui.JComboBoxAutoCompletePanel phoneTF;
    private javax.swing.JButton printButton;
    private javax.swing.JCheckBox timeCheck;
    private org.riflemansd.courierdb.gui.TimePickerPanel timeFrom;
    private org.riflemansd.courierdb.gui.TimePickerPanel timeTo;
    private org.riflemansd.courierdb.gui.JComboBoxAutoCompletePanel voucherTF;
    // End of variables declaration//GEN-END:variables
}
