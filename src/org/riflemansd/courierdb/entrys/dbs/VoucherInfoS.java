
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
    
    public VoucherInfoS(int id, int voucherid, String name, String address, String city, int postcode, String county, String country) {
        super(id, name);
        
        this.voucherid = voucherid;
        this.address = address;
        this.city = city;
        this.postcode = postcode;
        this.county = county;
        this.country = country;
    }
    public VoucherInfoS(int id, int voucherid, String name, String address, String city) {
        super(id, name);
        
        this.voucherid = voucherid;
        this.address = address;
        this.city = city;
        this.postcode = 57010;
        this.county = "ΘΕΣΣΑΛΟΝΙΚΗ";
        this.country = "ΕΛΛΑΔΑ";
    }
    public VoucherInfoS(int id, int voucherid, String address, String city) {
        super(id, "null");
        
        this.voucherid = voucherid;
        this.address = address;
        this.city = city;
        this.postcode = 57010;
        this.county = "ΘΕΣΣΑΛΟΝΙΚΗ";
        this.country = "ΕΛΛΑΔΑ";
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
    
    @Override
    public String toString() {
        String str = this.voucherid + "," + this.getName() + "," + this.address + "," + this.city + "," + this.postcode + "," + this.county + "," + this.country;
        
        return str;
    }
}
