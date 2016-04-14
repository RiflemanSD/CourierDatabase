/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.riflemansd.courierdb.gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sotir
 */
public class City {
    private String name;
    private int postCode;
    
    private String streetNamesPath;
    private String openstreetmapPath;
    private String[] streetNames;

    public City(String name, int postCode, String streetNamesPath, String openstreetmapPath) {
        this.name = name;
        this.postCode = postCode;
        this.streetNamesPath = streetNamesPath;
        
        try {
            String streets = "";
            BufferedReader in = new BufferedReader(new FileReader(this.streetNamesPath));
            
            String line;
            while((line = in.readLine()) != null)
            {
                streets += line + "\n";
                System.out.println(line);
            }
            in.close();
            
            this.streetNames = streets.split("\n");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(City.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(City.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.openstreetmapPath = openstreetmapPath;
    }

    public String[] getStreetNames() {
        return streetNames;
    }

    public void setStreetNames(String[] streetNames) {
        this.streetNames = streetNames;
    }
    
    public String getName() {
        return name;
    }

    public int getPostCode() {
        return postCode;
    }

    public String getStreetNamesPath() {
        return streetNamesPath;
    }

    public String getOpenstreetmapPath() {
        return openstreetmapPath;
    }
}
