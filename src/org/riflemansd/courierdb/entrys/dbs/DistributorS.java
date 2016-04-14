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
public class DistributorS extends Entry{
    private int dID;
    
    public DistributorS(int dID, String name) {
        super(name);
        this.dID = dID;
    }

    public int getdID() {
        return dID;
    }

    public void setdID(int dID) {
        this.dID = dID;
    }
}
