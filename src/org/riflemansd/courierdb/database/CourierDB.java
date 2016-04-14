/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.riflemansd.courierdb.database;

import org.riflemansd.courierdb.entrys.Address;
import org.riflemansd.courierdb.entrys.Customer;
import org.riflemansd.courierdb.entrys.FullAddress;

/**
 *
 * Χώρα
 * Νομός
 * Πόλη
 * Περιοχή
 * 
 * Οδός
 * Αριθμός
 * 
 * Πελάτης
 * Όναμα
 * Διεύθυνση
 * Τηλέφωνα
 * 
 * +++++++++++++++++++++++
 * Voucher:
 * Αριθμός Αποστολής (VoucherID ?)
 * 
 * ΑΠΟΣΤΟΛΕΑΣ
 * Όνομα
 * Διεύθυνση
 * Πόλη
 * ΤΚ
 * ΑΦΜ
 * ΤΗΛ.
 * 
 * Ονοματεπώνυμο και υπογραφή
 * Ημερομηνία και ώρα Παραλαβής
 * 
 * ΠΑΡΑΛΗΠΤΗΣ
 * Όνομα
 * Διεύθυνση
 * Πόλη
 * ΤΚ
 * ΑΦΜ
 * ΤΗΛ.
 * 
 * Ονοματεπώνυμο και υπογραφή
 * Ημερομηνία και ώρα Παράδοσης
 * 
 * Τεμάχια
 * Βάρος
 * Ογκομετρικό Βάρος
 * 
 * ΥΠΗΡΕΣΙΕΣ/SERVICES - ΧΡΕΩΣΕΙΣ/CHARGES
 * Αντικαταβολή
 * Μετρητά, Επιταγή
 * 
 * Αγορά
 * 
 * Ασφάληση Αποστολής
 * 
 * Βασική υπηρεσία
 * Αυθημερός
 * Σάββατο
 * Απομακρυσμένη δυσπρόσιτη
 * Πρωινή παράδοση (ως 10)
 * Δέσμευση ώρας Παράδοσης 10-13, 13-16, 16-19
 * Σύνολο Χρέωσης
 * 
 * Χρέωση/Charge
 * Αποστολέα/Shipper
 * Παραλήπτη/Consignee
 * Τρίτου/Third
 * Κωδικός Χρέωσης (για πελάτες)
 * Μετρητοις/cash
 * Επί Πιστώσει/credt
 * 
 * ΠΑΡΑΤΗΡΗΣΕΙΣ/REMARKS
 * 
 * +++++++++++++++++++++++
 * 
 * 
 * @author RiflemanSD
 */
public class CourierDB {
    private SQLiteManager manager;
    
    //Ένα πίνακα για τους Πελάτες
    //Ένα πίνακα για τα voucher
    //Ένα πίνακα για τις διευθύνσεις
    
    //Standard tables
    //πχ. Οι χώρες, οι πόλοις, οι διευθήνσεις...
    
    public CourierDB() {
        manager = new SQLiteManager("courierDB");
        
        manager.createTable("customer", "id,name,address,phone,afm,iscompany,chargecode", 1, "name", "a", "null", "null", true, "null");
        
        manager.createTable("voucher", "id,voucherid,shipperid,consigeeid,pieces,weight,volumetricweight,codcash,codcheck,buy,charge,chargecash,specifiedtime,issaturday,isinaccessible,isremote,sendername,recivername,sendtime,recivetime,isemergency,isreceipt,informations",
                                        1, "vid", 1, 1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, "charge", 1.0, "null", true, true, true, "sname", "rname","stime", "rtime", true, true, "null");
        
        manager.createTable("country", "id,name", 1, "name");
        manager.createTable("county", "id,countryid,name", 1, -1, "name");
        manager.createTable("city", "id,countyid,countryid,name,postcode", 1, -1, -1, "name", 1);
        manager.createTable("address", "id,cityid,name", 1, 1, "name");
        
        
        
        //Settings
        manager.createTable("settings", "id,name,value", 1, "s", "s");
    }
    
    
    public boolean saveCuctomer(Customer customer) {
        String name = customer.getName(); if (name == null || name.isEmpty() || name.equals(" ")) return false;
        FullAddress address = customer.getFullAddress(); if (address == null) return false;
        String phones = customer.getPhones(); if (phones == null || phones.isEmpty() || phones.equals(" ")) phones = "NULL";
        String afm = customer.getAfm(); if (afm == null || afm.isEmpty() || afm.equals(" ")) afm = "NULL";
        boolean isCompany = customer.isIsCompany(); 
        String charge = customer.getChargeCode(); if (charge == null || charge.isEmpty() || charge.equals(" ")) charge = "NULL";
        
        manager.insert("customer","name,address,phone,afm,iscompany,chargecode",name,address.getAddress().getName() +"-"+ address.getNumber(),phones,afm,isCompany,charge);
        
        return true;
    }
    
    public Customer getCustomer(String name) {
        String result = manager.select("customer", "name,address,phone,afm,iscompany,chargecode", "name = '" + name + "'", 7);
        
        String[] results = result.split(",");
        
        int id = stringToInt(results[0]);
        //results[1];
        FullAddress address = new FullAddress(new Address(results[2].split("-")[0]), stringToInt(results[2].split("-")[1]));
        String phones = results[3]; if (phones.equals("NULL")) phones = null;
        String afm = results[4]; if (afm.equals("NULL")) afm = null;
        boolean isCompany = stringToBoolean(results[5]); 
        String charge = results[6]; if (charge.equals("NULL")) charge = null;;
        
        return new Customer(id, name, address, phones, afm, isCompany, charge);
    }
    
    public String getCustomersAsString() {
        String result = manager.select("customer", "*", "", 7);
        
        return result;
    }
    
    public void saveVoucher() {
        
    }
    
    public void saveCountry() {
        
    }
    public void saveCounty() {
        
    }
    public void saveCity() {
        
    }
    public void saveAddress() {
        
    }
    
    public boolean stringToBoolean(String str) {
        if (str.equals("true")) return true;
        else return false;
    }
    public int stringToInt(String str) {
        int number = -1;
        
        try {
            number = Integer.parseInt(str);
        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            number = -1;
        }
        
        return number;
    }
    public double stringToDouble(String str) {
        double number = -1;
        
        try {
            number = Double.parseDouble(str);
        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            number = -1;
        }
        
        return number;
    }
}
