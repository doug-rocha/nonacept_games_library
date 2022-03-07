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
abstract class Item {

    String texto;
    boolean desativado;

    int textPos = 0;

    public void centerTextPosition(FontMetrics fm, int startPos, int width) {
        int media = (startPos + (startPos + width)) / 2;
        int tamanho_texto = fm.stringWidth(this.texto) / 2;
        this.textPos = media - tamanho_texto;
    }

    public int getTextWidth(FontMetrics fm) {
        return fm.stringWidth(this.texto);
    }

    public String getText() {
        return this.texto;
    }

    public void setText(String text) {
        this.texto = text;
    }

    public void disable() {
        this.disable(true);
    }

    public void disable(boolean disabled) {
        this.desativado = disabled;
    }

    public void enable() {
        this.disable(false);
    }

    public boolean isDisabled() {
        return this.desativado;
    }

    public int getTextPos() {
        return this.textPos;
    }

    public void setTextPos(int position) {
        this.textPos = position;
    }
}
