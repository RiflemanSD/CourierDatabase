/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.riflemansd.courierdb.entrys.dbs;

import org.riflemansd.courierdb.entrys.Entry;

/**
 *
 * @author RiflemanSD
 */
public class VoucherS extends Entry {
    private double codcash;
    private double chargecash;
    private boolean receipt;
    
    public VoucherS(String name, double codcash, double chargecash, boolean receipt) {
        super(name);
        this.codcash = codcash;
        this.chargecash = chargecash;
        this.receipt = receipt;
    }
    
    public double getCodcash() {
        return codcash;
    }

    public void setCodcash(double codcash) {
        this.codcash = codcash;
    }

    public double getChargecash() {
        return chargecash;
    }

    public void setChargecash(double chargecash) {
        this.chargecash = chargecash;
    }

    public boolean isReceipt() {
        return receipt;
    }

    public void setReceipt(boolean receipt) {
        this.receipt = receipt;
    }
    
    @Override
    public String toString() {
        String str = this.getName() + "," + this.codcash + "," + this.chargecash + "," + this.receipt;
        
        return str;
    }
}
