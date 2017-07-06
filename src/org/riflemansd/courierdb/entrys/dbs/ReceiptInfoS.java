/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.riflemansd.courierdb.entrys.dbs;

import org.riflemansd.courierdb.entrys.Entry;

/**
 *
 * @author user
 */
public class ReceiptInfoS extends Entry {
    private int voucherid;

    public ReceiptInfoS(int id, int voucherid, String imagePath) {
        super(id, imagePath);
        
        this.voucherid = voucherid;
    }
    
    public int getVoucherid() {
        return voucherid;
    }

    public void setVoucherid(int voucherid) {
        this.voucherid = voucherid;
    }
    
    public String getImagePath() {
        return this.getName();
    }
    
    @Override
    public String toString() {
        String str = this.voucherid  + "," + this.getName();
        
        return str;
    }
}
