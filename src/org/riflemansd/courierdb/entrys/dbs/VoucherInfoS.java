
package org.riflemansd.courierdb.entrys.dbs;

import org.riflemansd.courierdb.entrys.Entry;

/**
 * <h1>CourierDB</h1>
 * <h3>Class VoucherInfoS</h3> 
 * <p>Created: 23 Ιουν 2017, 9:46:44 μμ</p>
 *
 * <p>Copyright © 2016 | RiflemanSD | All right reserved</p>
 *
 * @author RiflemanSD
 */
public class VoucherInfoS extends Entry {
    private int voucherid;
    private String address;
    private String city;
    private int postcode;
    private String county;
    private String country;
    private String time;
    private boolean emergercy;
    private String phone;
    
    public VoucherInfoS(int id, int voucherid, String name, String address, String city, int postcode, String county, String country, String time, boolean emergency, String phone) {
        super(id, name);
        
        this.voucherid = voucherid;
        this.address = address;
        this.city = city;
        this.postcode = postcode;
        this.county = county;
        this.country = country;
        this.time = time;
        this.emergercy = emergency;
        this.phone = phone;
    }
    public VoucherInfoS(int voucherid, String name, String address, String city) {
        super(name);
        
        this.voucherid = voucherid;
        this.address = address;
        this.city = city;
        this.postcode = 57010;
        this.county = "ΘΕΣΣΑΛΟΝΙΚΗ";
        this.country = "ΕΛΛΑΔΑ";
        this.time = "";
        this.emergercy = false;
        this.phone = "";
    }
    public VoucherInfoS(int voucherid, String address, String city) {
        super("null");
        
        this.voucherid = voucherid;
        this.address = address;
        this.city = city;
        this.postcode = 57010;
        this.county = "ΘΕΣΣΑΛΟΝΙΚΗ";
        this.country = "ΕΛΛΑΔΑ";
        this.time = "";
        this.emergercy = false;
        this.phone = "";
    }
    public VoucherInfoS(int voucherid, String address, String city, String name, String time, boolean emergency, String phone) {
        super(name);
        
        this.voucherid = voucherid;
        this.address = address;
        this.city = city;
        this.postcode = 57010;
        this.county = "ΘΕΣΣΑΛΟΝΙΚΗ";
        this.country = "ΕΛΛΑΔΑ";
        this.phone = phone;
        this.time = time;
        this.emergercy = emergency;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isEmergercy() {
        return emergercy;
    }

    public void setEmergercy(boolean emergercy) {
        this.emergercy = emergercy;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getVoucherid() {
        return voucherid;
    }

    public void setVoucherid(int voucherid) {
        this.voucherid = voucherid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    @Override
    public String toString() {
        String str = this.voucherid + "," + this.getName() + "," + this.address + "," + this.city + "," + this.postcode + "," + this.county + "," + this.country + "," + time + "," + emergercy + "," + phone;
        
        return str;
    }
}
