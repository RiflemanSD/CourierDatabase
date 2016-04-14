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
public class County extends Entry {
    private Country country;

    public County(String name) {
        super(name);
        this.country = null;
    }
    public County(int id, String name, Country country) {
        super(id, name);
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
    
    
}
