/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.riflemansd.courierdb.gui.test;

import javax.swing.JFileChooser;

/**
 *
 * @author RiflemanSD
 */
public class TestiumMain {

    public static void main(String[] args) {
        String c1 = "1000.45";
        String c2 = "93,56";
        
        System.out.println(Double.parseDouble(c1) + " " + Double.parseDouble(c2.replace(",", ".")));
    }
}
