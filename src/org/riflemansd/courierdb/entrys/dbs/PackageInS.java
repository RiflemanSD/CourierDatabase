/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.riflemansd.courierdb.entrys.dbs;

/**
 *
 * @author RiflemanSD
 */
public class PackageInS {
    private int id;
    private int voucherId;
    private int distributorId;
    private String time;

    public PackageInS(int voucherId, int distributorId, String time) {
        this.id = -1;
        this.voucherId = voucherId;
        this.distributorId = distributorId;
        this.time = time;
    }
    
    public PackageInS(int id, int voucherId, int distributorId, String time) {
        this.id = id;
        this.voucherId = voucherId;
        this.distributorId = distributorId;
        this.time = time;
    }

    public int getCourierId() {
        return distributorId;
    }

    public void setCourierId(int distributorId) {
        this.distributorId = distributorId;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(int voucherId) {
        this.voucherId = voucherId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
    
}
