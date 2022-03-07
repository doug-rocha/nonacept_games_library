/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nonacept.ui.components;

/**
 *
 * @author Douglas Rocha de Oliveira - NonaCept
 */
public class PanelItem extends Item {

    private String[] valor;
    private int valorSelecionado;

    
    /**
     * 
     * @return all values
     */
    public String[] getValues() {
        return this.valor;
    }

    public void setValues(String[] value) {
        this.valor = value;
    }

    public void setProperties(String text, String[] value) {
        this.setText(text);
        this.setValues(value);
    }

    public void setSelectedValue(int id) {
        this.valorSelecionado = id;
    }

    public void setSelectedValue(String value) {
        boolean achado = false;
        for (int i = 0; i < valor.length && !achado; i++) {
            if (valor[i].equals(value)) {
                this.setSelectedValue(i);
                achado = true;
            }
        }
    }
    /**
     * 
     * @return the selected value
     */
    public String getValue(){
        return this.valor[valorSelecionado];
    }
    
    public String getValue(int selected){
        return this.valor[selected];
    }
    
    public int getSelected(){
        return this.valorSelecionado;
    }
}
