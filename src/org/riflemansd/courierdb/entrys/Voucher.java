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
public class Voucher extends Entry {
    //"id,voucherid,shipperid,consigeeid,pieces,weight,volumetricweight,codcash,codcheck,buy,charge,
    //chargecash,specifiedtime,issaturday,isinaccessible,isremote,sendername,recivername,sendtime,
    //recivetime,isemergency,isreceipt,informations"
    private String voucherId;
    private Customer shipper;
    private Customer consigee;
    private int pieces;
    private double weight;
    private double volumetricWeight;
    private double codCash; //Αντικαταβολή Μετρητά
    private double codCheck; //Αντικαταβολή Επιταγή
    private double buy; //αγορά
    private String charge; //χρέωση (πχ αΠΕ0423 - Χρέωση αποστολέα, με κωδικό | πΠΕ0423 χρέωση παραλήπτη | π χρέωση χωρής κωδικό | τ χρέωση τρίτου)
    private double chargeCash; //Μετρητά
    private String specifiedTime; //25-2-16 13:00-15:00
    private boolean isSaturday;
    private boolean isInaccessible; //Δυσπρόσητη
    private boolean isRemote; //Απομακρυσμένη
    private String senderName;
    private String reciverName;
    private String sendTime; //Αυτό θα μπορούσα να είναι και Date
    private String reciveTime;
    private boolean isEmergency; //είναι επείγον
    private boolean isReceipt; //είναι παραλαβή
    private String informations; //Άλλες παρατηρήσεις-πληροφορίες

    public Voucher(String voucherId) {
        super(voucherId);
        this.voucherId = voucherId;
    }

    public Voucher(String voucherId, Customer shipper, Customer consigee, double codCash, String specifiedTime, boolean isReceipt) {
        super(voucherId);
        this.voucherId = voucherId;
        this.shipper = shipper;
        this.consigee = consigee;
        this.codCash = codCash;
        this.specifiedTime = specifiedTime;
        this.isReceipt = isReceipt;
    }

    
    public Voucher(int id, String name, String voucherId, Customer shipper, Customer consigee, int pieces, double weight, double volumetricWeight, double codCash, double codCheck, double buy, String charge, double chargeCash, String specifiedTime, boolean isSaturday, boolean isInaccessible, boolean isRemote, String senderName, String reciverName, String sendTime, String reciveTime, boolean isEmergency, boolean isReceipt, String informations) {
        super(id, name);
        this.voucherId = voucherId;
        this.shipper = shipper;
        this.consigee = consigee;
        this.pieces = pieces;
        this.weight = weight;
        this.volumetricWeight = volumetricWeight;
        this.codCash = codCash;
        this.codCheck = codCheck;
        this.buy = buy;
        this.charge = charge;
        this.chargeCash = chargeCash;
        this.specifiedTime = specifiedTime;
        this.isSaturday = isSaturday;
        this.isInaccessible = isInaccessible;
        this.isRemote = isRemote;
        this.senderName = senderName;
        this.reciverName = reciverName;
        this.sendTime = sendTime;
        this.reciveTime = reciveTime;
        this.isEmergency = isEmergency;
        this.isReceipt = isReceipt;
        this.informations = informations;
    }

    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }

    public Customer getShipper() {
        return shipper;
    }

    public void setShipper(Customer shipper) {
        this.shipper = shipper;
    }

    public Customer getConsigee() {
        return consigee;
    }

    public void setConsigee(Customer consigee) {
        this.consigee = consigee;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getVolumetricWeight() {
        return volumetricWeight;
    }

    public void setVolumetricWeight(double volumetricWeight) {
        this.volumetricWeight = volumetricWeight;
    }

    public double getCodCash() {
        return codCash;
    }

    public void setCodCash(double codCash) {
        this.codCash = codCash;
    }

    public double getCodCheck() {
        return codCheck;
    }

    public void setCodCheck(double codCheck) {
        this.codCheck = codCheck;
    }

    public double getBuy() {
        return buy;
    }

    public void setBuy(double buy) {
        this.buy = buy;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public double getChargeCash() {
        return chargeCash;
    }

    public void setChargeCash(double chargeCash) {
        this.chargeCash = chargeCash;
    }

    public String getSpecifiedTime() {
        return specifiedTime;
    }

    public void setSpecifiedTime(String specifiedTime) {
        this.specifiedTime = specifiedTime;
    }

    public boolean isIsSaturday() {
        return isSaturday;
    }

    public void setIsSaturday(boolean isSaturday) {
        this.isSaturday = isSaturday;
    }

    public boolean isIsInaccessible() {
        return isInaccessible;
    }

    public void setIsInaccessible(boolean isInaccessible) {
        this.isInaccessible = isInaccessible;
    }

    public boolean isIsRemote() {
        return isRemote;
    }

    public void setIsRemote(boolean isRemote) {
        this.isRemote = isRemote;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReciverName() {
        return reciverName;
    }

    public void setReciverName(String reciverName) {
        this.reciverName = reciverName;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getReciveTime() {
        return reciveTime;
    }

    public void setReciveTime(String reciveTime) {
        this.reciveTime = reciveTime;
    }

    public boolean isIsEmergency() {
        return isEmergency;
    }

    public void setIsEmergency(boolean isEmergency) {
        this.isEmergency = isEmergency;
    }

    public boolean isIsReceipt() {
        return isReceipt;
    }

    public void setIsReceipt(boolean isReceipt) {
        this.isReceipt = isReceipt;
    }

    public String getInformations() {
        return informations;
    }

    public void setInformations(String informations) {
        this.informations = informations;
    }
    
    
}
