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
public class Address extends Entry {
    private Country country;
    private County county;

    public Address(String name) {
        super(name);
        this.country = null;
        this.county = null;
    }
    public Address(int id, String name, Country country, County county) {
        super(id, name);
        this.country = country;
        this.county = county;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
    }
    
    
}
