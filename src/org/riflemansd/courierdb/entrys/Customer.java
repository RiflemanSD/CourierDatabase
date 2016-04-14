/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.riflemansd.courierdb.entrys;

/**
 *
 * @author RiflemanSD
 */
public class Customer extends Entry {
    private String phones; // 2310358214-2310357321 etc..
    private FullAddress address;
    private String afm;
    private boolean isCompany;
    private String chargeCode;

    public Customer(String name, FullAddress address, String phones) {
        super(name);
        this.phones = phones;
        this.address = address;
        this.afm = null;
        this.isCompany = false;
        this.chargeCode = null;
    }
    
    public Customer(String name, FullAddress address, String phones, boolean isCompany, String chargeCode) {
        super(name);
        this.phones = phones;
        this.address = address;
        this.afm = null;
        this.isCompany = isCompany;
        this.chargeCode = chargeCode;
    }
    
    public Customer(String name, FullAddress address, String phones, String afm, boolean isCompany, String chargeCode) {
        super(name);
        this.phones = phones;
        this.address = address;
        this.afm = afm;
        this.isCompany = isCompany;
        this.chargeCode = chargeCode;
    }
    
    public Customer(int id, String name, FullAddress address, String phones, String afm, boolean isCompany, String chargeCode) {
        super(id, name);
        this.phones = phones;
        this.address = address;
        this.afm = afm;
        this.isCompany = isCompany;
        this.chargeCode = chargeCode;
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public FullAddress getFullAddress() {
        return address;
    }

    public void setFullAddress(FullAddress address) {
        this.address = address;
    }

    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    public boolean isIsCompany() {
        return isCompany;
    }

    public void setIsCompany(boolean isCompany) {
        this.isCompany = isCompany;
    }

    public String getChargeCode() {
        return chargeCode;
    }

    public void setChargeCode(String chargeCode) {
        this.chargeCode = chargeCode;
    }
    
    
}
