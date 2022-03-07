/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nonacept.ui;

import com.nonacept.ui.components.MenuItem;

/**
 *
 * @author Douglas Rocha de Oliveira - NonaCept
 */
//@todo menu de opções
public class OptionsMenu {

    private MenuBar barra;
    private int selectedMenu, selectOption;

    private boolean pronto;

    public OptionsMenu() {
        this.selectedMenu = 0;
        this.selectOption = 0;
        this.pronto = false;
    }

    public void setReady() {
        
        barra.setReady();
        this.pronto = true;
    }

    private void setUnready() {
        this.pronto = false;
    }

}
