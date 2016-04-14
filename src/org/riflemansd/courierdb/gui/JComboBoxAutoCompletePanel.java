/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.riflemansd.courierdb.gui;

import java.awt.BorderLayout;
import org.riflemansd.autocomplete.AutoCompleteListener;
import org.riflemansd.autocomplete.JBoxAutoCompletePromptFasterWM;
/**
 *
 * @author user01
 */
public class JComboBoxAutoCompletePanel extends javax.swing.JPanel {
    private JBoxAutoCompletePromptFasterWM box;

    /**
     * Creates new form JXTextFieldPanel
     */
    public JComboBoxAutoCompletePanel() {
        initComponents();
        
        box = new JBoxAutoCompletePromptFasterWM(" ");
        //box.addAutoCompleteListener(new AutoCompleteListener());
        this.add(box, BorderLayout.CENTER);
    }
    
    public JComboBoxAutoCompletePanel(String... words) {
        initComponents();
        
        box = new JBoxAutoCompletePromptFasterWM(words);
        //box.addAutoCompleteListener(new AutoCompleteListener());
        this.add(box, BorderLayout.CENTER);
    }
    
    public void setWords(String... words) {
        box.setWords(words);
    }

    public void setPrompt(String prompt) {
        box.setPromptText(prompt);
    }
    
    public String getValue() {
        return box.getValue();
    }
    public void setValue(String value) {
        box.setValue(value);
    }
    
    public void addAutoCompleteListener(AutoCompleteListener l) {
        box.addAutoCompleteListener(l);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    
}
