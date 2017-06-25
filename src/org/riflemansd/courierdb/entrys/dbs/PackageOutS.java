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
public class PackageOutS {
    private int id;
    private int voucherId;
    private int distributorId;
    private String time;
    private String rtime;
    private String info;

    public PackageOutS(int id, int voucherId, int distributorId, String time, String rtime) {
        this.id = id;
        this.voucherId = voucherId;
        this.distributorId = distributorId;
        this.time = time;
        this.rtime = rtime;
        this.info = null;
    }

    public PackageOutS(int id, int voucherId, int distributorId, String time, String rtime, String info) {
        this.id = id;
        this.voucherId = voucherId;
        this.distributorId = distributorId;
        this.time = time;
        this.rtime = rtime;
        this.info = info;
    }

    public String getRtime() {
        return rtime;
    }

    public void setRtime(String rtime) {
        this.rtime = rtime;
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

    public int getCourierId() {
        return distributorId;
    }

    public void setCourierId(int distributorId) {
        this.distributorId = distributorId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    
    
}
