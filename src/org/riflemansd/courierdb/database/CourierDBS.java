/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.riflemansd.courierdb.database;

import org.riflemansd.courierdb.entrys.Address;
import org.riflemansd.courierdb.entrys.Customer;
import org.riflemansd.courierdb.entrys.FullAddress;
import org.riflemansd.courierdb.entrys.dbs.DistributorS;
import org.riflemansd.courierdb.entrys.dbs.PackageInS;
import org.riflemansd.courierdb.entrys.dbs.PackageOutS;
import org.riflemansd.courierdb.entrys.dbs.VoucherS;
import org.riflemansd.courierdb.utils.MyUtils;

public class CourierDBS {
    private SQLiteManager manager;
    
    public CourierDBS() {
        manager = new SQLiteManager("courierDBS");
        
        //Διανομέας
        manager.createTable("distributor", "id,did,name", 1, 1, "name");
        
        manager.createTable("voucher", "id,voucherid,codcash,chargecash,isreceipt", 1, "vid", 1, 1, true);
        
        manager.createTable("packagein", "id,voucherid,time", 1, 1, "rtime");
        manager.createTable("packageout", "id,voucherid,distributorid,time,rtime,info", 1, 1, 1, "null", "null", "null");
        
        
        //Settings
        manager.createTable("settings", "id,name,value", 1, "s", "s");
    }
    
    public boolean saveDistributor(DistributorS d) {
        int id = d.getdID(); if (id < 0) return false;
        String name = d.getName(); if (name == null) return false;
        
        manager.insert("distributor","did,name", id, name);
        
        return true;
    }
    
    public DistributorS getDistributor(String name) {
        String result = manager.select("distributor", "did,name", "name = '" + name + "'", 3);
        if (result.length() == 0) return null;
        
        String[] r = result.split(",");
        int id = MyUtils.stringToInt(r[0]);
        
        return new DistributorS(id, name);
    }
    public DistributorS getDistributor(int dID) {
        String result = manager.select("distributor", "did,name", "did = '" + dID + "'", 3);
        if (result.length() == 0) return null;
        
        String[] r = result.split(",");
        String name = r[1];
        
        return new DistributorS(dID, name);
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
        
        System.out.println("true");
        return true;
    }
    public VoucherS getVoucher(int id) {
        String result = manager.select("voucher","id,voucherid,codcash,chargecash,isreceipt", "id = " + id, 5);
        
        if (result.length() == 0) return null;
        
        String[] r = result.split(",");
        
        String voucherid = r[1];
        double cod = -1; if (!r[1].equals("NULL")) cod = MyUtils.stringToDouble(r[2]);
        double charge = -1; if (!r[2].equals("NULL")) cod = MyUtils.stringToDouble(r[3]);
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
        double cod = -1; if (!r[1].equals("NULL")) cod = MyUtils.stringToDouble(r[2]);
        double charge = -1; if (!r[2].equals("NULL")) cod = MyUtils.stringToDouble(r[3]);
        boolean isr = MyUtils.stringToBoolean(r[4]);
        
        VoucherS v = new VoucherS(id, voucherid, cod, charge, isr);
        System.out.println(v);
        return v;
    }
    
    public boolean savePackageIn(PackageInS pack) {
        System.out.println(pack.getVoucherId() + " " + pack.getTime());
        int id = pack.getVoucherId(); if (id < 0) return false;
        String time = pack.getTime(); if (time == null) return false;
        
        manager.insert("packagein","voucherid,time", id, time);
        System.out.println("bige");
        return true;
    }
    public boolean savePackageOut(PackageOutS pack) {
        int id = pack.getVoucherId(); if (id < 0) return false;
        String time = pack.getTime(); if (time == null) return false;
        int dID = pack.getDistributorId();
        String info = pack.getInfo();
        String rtime = pack.getRtime();
        
        manager.insert("packageout","voucherid,distributorid,time,rtime,info", id, dID, time, rtime, info);
        
        return true;
    }
    
    public String[] getPackagesIn(String voucherid) {
        String result = manager.select("packagein","voucherid,time", "voucherid = '" + voucherid + "'", 3);
        
        if (result.length() == 0) return null;
        
        String[] r = result.split(",");
        
        return r;
    }
    public String[] getPackagesOut(String voucherid) {
        String result = manager.select("packageout","voucherid,distributorid,time,rtime,info", "voucherid = '" + voucherid + "'", 6);
        
        if (result.length() == 0) return null;
        
        String[] r = result.split(",");
        
        return r;
    }
    
    public String[] getPackagesIn() {
        String result = manager.select("packagein","voucherid,time", "", 2);
        System.out.println(result);
        if (result.length() == 0) return null;
        
        String[] r = result.split("\n");
        
        return r;
    }
    /**
     * voucherid,distributorid,time,rtime,info
     * voucherid,codcash,chargecash,isreceipt
     * @param time - format: 14-04-2016
     * @return 
     */
    public String[] getVouchersByRTime(String time) {
        String[] vouchers = new String[4];
        
        return vouchers;
    }
//    public String[] getVouchersByDistributor(String name) {
//        
//    }
//    public String[] getVouchersByDistributor(int dID) {
//        
//    }
}
