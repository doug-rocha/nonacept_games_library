/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nonacept.ui.components;

import java.awt.FontMetrics;

/**
 *
 * @author Douglas Rocha de Oliveira - NonaCept
 */
public class MenuItem extends Item {
    
    public MenuItem() {
        texto = "placeholder";
        desativado = true;
    }

    @Override
    public void centerTextPosition(FontMetrics fm, int startPos, int width) {
        int media = (startPos + (startPos + width)) / 2;
        int tamanho_texto = fm.stringWidth(this.texto) / 2;
        this.textPos = media - tamanho_texto;
    }

    @Override
    public void setText(String txt) {
        this.texto = txt;
    }

    @Override
    public void disable() {
        this.disable(true);
    }

    @Override
    public void disable(boolean disabled) {
        this.desativado = disabled;
    }

    @Override
    public void enable() {
        this.disable(false);
    }

    @Override
    public String getText() {
        return this.texto;
    }

    @Override
    public boolean isDisabled() {
        return this.desativado;
    }

    @Override
    public int getTextPos() {
        return this.textPos;
    }

    @Override
    public void setTextPos(int position) {
        this.textPos = position;
    }
}
