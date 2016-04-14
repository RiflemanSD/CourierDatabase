/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.riflemansd.courierdb.gui;

import org.riflemansd.autocomplete.AutoCompleteListener;

/**
 *
 * @author sotir
 */
public class MyAutoCompleteListener extends AutoCompleteListener {
    private QuickGUIForm f;

    public MyAutoCompleteListener(QuickGUIForm f) {
        this.f = f;
    }
    
    @Override
    public void onAutoComplete() {
        f.onCityAutoComplete(getWord());
    }
    
}
