/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.riflemansd.courierdb.database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.riflemansd.courierdb.entrys.dbs.CourierS;
import org.riflemansd.courierdb.entrys.dbs.PackageInS;
import org.riflemansd.courierdb.entrys.dbs.PackageOutS;
import org.riflemansd.courierdb.entrys.dbs.ReceiptInfoS;
import org.riflemansd.courierdb.entrys.dbs.VoucherInfoS;
import org.riflemansd.courierdb.entrys.dbs.VoucherS;
import org.riflemansd.courierdb.utils.MyUtils;

public class CourierDBS {
    private SQLiteManager manager;
    
    public CourierDBS() {
        manager = new SQLiteManager("courierDBS");
        
        //Διανομέας
        manager.createTable("courier", "id,did,name", 1, 1, "name");
        
        manager.createTable("voucher", "id,voucherid,codcash,chargecash,isreceipt", 1, "vid", 1, 1, true);
        manager.createTable("voucherinfo", "id,voucherid,name,address,city,postcode,county,country,time,emergency,phone", 1, 1, "null", "null", 1, "null", "null", "null", "null", true, "null");
        
        manager.createTable("receiptinfo", "id,voucherid,imagepath", 1, 1, "path");
        
        manager.createTable("packageout", "id,voucherid,courierid,time,rtime,info", 1, 1, 1, "null", "null", "null");
        manager.createTable("packagein", "id,voucherid,courierid,time", 1, 1, 1, "rtime");
        
        //Settings
        manager.createTable("settings", "id,name,value", 1, "s", "s");
    }
    
    public boolean saveReceiptInfo(ReceiptInfoS voucher) {
        int voucherid = voucher.getVoucherid(); if (voucherid < 0) return false;
        String imagePath = voucher.getImagePath();
        
        VoucherInfoS v = getVoucherInfo(voucherid);
        if (v != null) {
            manager.update("receiptinfo", "imagepath = '" + imagePath, "voucherid = " + voucherid);
        }
        else {
            manager.insert("receiptinfo","voucherid,imagepath", voucherid, imagePath);
        }
        
        return true;
    }
    public ReceiptInfoS getReceiptInfo(int voucherid) {
        String result = manager.select("receiptinfo","id,voucherid,imagepath", "voucherid = " + voucherid, 3);
        
        if (result.length() == 0) return null;
        
        String[] r = result.split(",");
        
        int id = -1; if (!r[0].equals("NULL")) id = MyUtils.stringToInt(r[0]);
        String imagePath = r[2];
        
        ReceiptInfoS v = new ReceiptInfoS(id, voucherid, imagePath);
        System.out.println(v);
        return v;
    }
    
    public boolean saveVoucherInfo(VoucherInfoS voucher) {
        int voucherid = voucher.getVoucherid(); if (voucherid < 0) return false;
        String name = voucher.getName();
        String address = voucher.getAddress();
        String city = voucher.getCity();
        int postcode = voucher.getPostcode();
        String county = voucher.getCounty();
        String country = voucher.getCountry();
        String time = voucher.getTime();
        boolean emergency = voucher.isEmergercy(); String isem = MyUtils.booleanToString(emergency);
        String phone = voucher.getPhone();
        
        VoucherInfoS v = getVoucherInfo(voucherid);
        if (v != null) {
            manager.update("voucherinfo", "name = '" + name + "', address = '" + address + "', city = '" + city + "', postcode = " + postcode + ", county = '" + county + "', country = '" + country + "', time = '" + time + "', emergency = '" + isem + "', phone = '" + phone + "'", "voucherid = " + voucherid);
        }
        else {
            manager.insert("voucherinfo","voucherid,name,address,city,postcode,county,country,time,emergency,phone", voucherid, name, address, city, postcode, county, country, time, isem, phone);
        }
        
        return true;
    }
    public VoucherInfoS getVoucherInfo(int voucherid) {
        String result = manager.select("voucherinfo","id,voucherid,name,address,city,postcode,county,country,time,emergency,phone", "voucherid = " + voucherid, 11);
        
        if (result.length() == 0) return null;
        
        String[] r = result.split(",");
        
        int id = -1; if (!r[0].equals("NULL")) id = MyUtils.stringToInt(r[0]);
        String name = r[2];
        String address = r[3];
        String city = r[4];
        int postcode = -1; if (!r[5].equals("NULL")) postcode = MyUtils.stringToInt(r[2]);
        String county = r[6];
        String country = r[7];
        String time = r[8];
        boolean emergency = MyUtils.stringToBoolean(r[9]);
        String phone = r[10];
        
        VoucherInfoS v = new VoucherInfoS(id, voucherid, name, address, city, postcode, county, country, time, emergency, phone);
        System.out.println(v);
        return v;
    }
    
    public CourierS saveCourier(String name) {
        if (name == null) return null;
        if (this.getCourier(name) != null) return null;
        
        String[] dists = this.getCouriers();
        int id = MyUtils.stringToInt(dists[dists.length-1].split(",")[0]) + 1;
        
        manager.insert("courier","did,name", id, name);
        
        return new CourierS(id, name);
    }
    
    public CourierS getCourier(String name) {
        String result = manager.select("courier", "id,did,name", "name = '" + name + "'", 3);
        if (result.length() == 0) return null;
        
        String[] r = result.split(",");
        int id = MyUtils.stringToInt(r[0]);
        int did = MyUtils.stringToInt(r[1]);
        
        return new CourierS(id, did, name);
    }
    public CourierS getCourier(int dID) {
        String result = manager.select("courier", "id,did,name", "did = '" + dID + "'", 3);
        if (result.length() == 0) return null;
        
        String[] r = result.split(",");
        int id = MyUtils.stringToInt(r[0]);
        String name = r[2];
        
        return new CourierS(id, dID, name);
    }
    public CourierS getCourierByID(int id) {
        String result = manager.select("courier", "id,did,name", "id = '" + id + "'", 3);
        if (result.length() == 0) return null;
        
        String[] r = result.split(",");
        int did = MyUtils.stringToInt(r[1]);
        String name = r[2];
        
        return new CourierS(id, did, name);
    }
    public String[] getCouriers() {
        String result = manager.select("courier", "did,name", "", 2);
        System.out.println(result);
        if (result.length() == 0) return null;
        
        String[] r = result.split("\n");
        
        return r;
    }
    public void editCourier(String name, int did) {
        manager.update("courier", "did = " + did, "name = '" + name + "'");
    }
    public boolean deleteCourier(int did) {
        if (did < 1) return false;
        
        manager.delete("courier", "did = " + did);
        
        String[] dists = this.getCouriers();
        for (String d : dists) {
            if (did < MyUtils.stringToInt(d.split(",")[0])) {
                this.editCourier(d.split(",")[1], MyUtils.stringToInt(d.split(",")[0])-1);
            }
        }
        
        return true;
    }
    
    public boolean saveVoucher(VoucherS voucher) {
        String name = voucher.getName(); if (name == null) return false;
        double cash = voucher.getCodcash(); 
        double charge = voucher.getChargecash();
        boolean isr = voucher.isReceipt(); String isrs = MyUtils.booleanToString(isr);
        
        VoucherS v = getVoucher(name);
        if (v != null) {
            manager.update("voucher", "codcash = " + cash + ", chargecash = " + charge + ", isreceipt = '" + isrs + "'", "voucherid = '" + name + "'");
        }
        else {
            manager.insert("voucher","voucherid,codcash,chargecash,isreceipt", name, cash, charge, isrs);
        }
        
        return true;
    }
    public VoucherS getVoucher(int id) {
        String result = manager.select("voucher","id,voucherid,codcash,chargecash,isreceipt", "id = " + id, 5);
        
        if (result.length() == 0) return null;
        
        String[] r = result.split(",");
        
        String voucherid = r[1];
        double cod = -1; if (!r[2].equals("NULL")) cod = MyUtils.stringToDouble(r[2]);
        double charge = -1; if (!r[3].equals("NULL")) charge = MyUtils.stringToDouble(r[3]);
        boolean isr = MyUtils.stringToBoolean(r[4]);
        
        VoucherS v = new VoucherS(id, voucherid, cod, charge, isr);
        System.out.println(v);
        return v;
    }
    public VoucherS getVoucher(String voucherid) {
        String result = manager.select("voucher","id,voucherid,codcash,chargecash,isreceipt", "voucherid = '" + voucherid + "'", 5);
        
        if (result.length() == 0) return null;
        
        String[] r = result.split(",");
        
        int id = MyUtils.stringToInt(r[0]);
        double cod = -1; if (!r[2].equals("NULL")) cod = MyUtils.stringToDouble(r[2]);
        double charge = -1; if (!r[3].equals("NULL")) charge = MyUtils.stringToDouble(r[3]);
        boolean isr = MyUtils.stringToBoolean(r[4]);
        
        VoucherS v = new VoucherS(id, voucherid, cod, charge, isr);
        //System.out.println(v);
        return v;
    }
    
    public String[] getVouchers() {
        String result = manager.select("voucher","id,voucherid,codcash,chargecash,isreceipt", "", 5);
        System.out.println(result);
        if (result.length() == 0) return null;
        
        String[] r = result.split("\n");
        
        return r;
    }
    
    public boolean savePackageIn(PackageInS pack) {
        System.out.println(pack.getVoucherId() + " " + pack.getTime());
        int id = pack.getVoucherId(); if (id < 0) return false;
        int distid = pack.getCourierId(); if (distid < 0) return false;
        String time = pack.getTime(); if (time == null) return false;
        
        manager.insert("packagein","voucherid,courierid,time", id, distid, time);
        System.out.println("bige");
        return true;
    }
    public boolean savePackageOut(PackageOutS pack) {
        int id = pack.getVoucherId(); if (id < 0) return false;
        String time = pack.getTime(); if (time == null) return false;
        int dID = pack.getCourierId();
        String info = pack.getInfo();
        String rtime = pack.getRtime();
        
        manager.insert("packageout","voucherid,courierid,time,rtime,info", id, dID, time, rtime, info);
        
        return true;
    }
    
    public String[] getPackagesIn(String voucherid) {
        String result = manager.select("packagein","voucherid,courierid,time", "voucherid = '" + voucherid + "'", 3);
        
        if (result.length() == 0) return null;
        
        String[] r = result.split(",");
        
        return r;
    }
    public String[] getPackagesOut(String voucherid) {
        String result = manager.select("packageout","voucherid,courierid,time,rtime,info", "voucherid = '" + voucherid + "'", 6);
        
        if (result.length() == 0) return null;
        
        String[] r = result.split(",");
        
        return r;
    }
    
    public String[] getPackagesIn() {
        String result = manager.select("packagein","voucherid,courierid,time", "", 3);
        System.out.println(result);
        if (result.length() == 0) return null;
        
        String[] r = result.split("\n");
        
        return r;
    }
    /**
     * voucherid,courierid,time,rtime,info
     * voucherid,codcash,chargecash,isreceipt
     * @param time - format: 14-04-2016
     * @return 
     */
    public String[] getVouchersByTime(String time) {
        System.out.println("I AM RUNNING" + " t: " + time);
        String result = manager.select("packageout","voucherid,courierid,rtime", "time LIKE '" + time + "%'", 3);
        
        System.out.println(result);
        
        return result.split("\n");
    }
//    public String[] getVouchersByCourier(String name) {
//        
//    }
//    public String[] getVouchersByCourier(int dID) {
//        
//    }
    
    public void delete(int tableId, int rowId) {
        String table = null;
        if (tableId == 0) {
            table = "courier";
        }
        else if (tableId == 1) {
            table = "voucher";
        }
        else if (tableId == 2) {
            table = "packagein";
        }
        else if (tableId == 3) {
            table = "packageout";
        }
        
        if (table != null) manager.delete(table, "id = " + rowId);
    }
    
    public ArrayList<Object[]> getAll3(String voucher) {
        String sql = "SELECT voucher.voucherid, voucherinfo.address, voucherinfo.city, voucher.chargecash, voucher.codcash, packagein.time, packageout.time "
                + "FROM voucher LEFT JOIN voucherinfo ON voucher.voucherid = voucherinfo.voucherid "
                + "LEFT JOIN packageout ON voucher.voucherid = packageout.voucherid "
                + "LEFT JOIN packagein ON voucher.voucherid = packagein.voucherid "
                + "WHERE voucher.voucherid LIKE '%" + voucher + "%' OR voucherinfo.address LIKE '%" + voucher + "%'";
        
        return this.manager.executeQueryToObjects(sql, 7);
    }
    
    public void close() {
        this.manager.close();
    }
}
