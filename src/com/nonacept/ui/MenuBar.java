/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nonacept.ui;

import com.nonacept.Properties;
import com.nonacept.ui.components.MenuItem;
import com.nonacept.ui.themes.CTGTheme;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Douglas Rocha de Oliveira - NonaCept
 */
public class MenuBar extends VisualElement {

    private ArrayList<MenuItem> itensList = new ArrayList<>();
    private MenuItem[] itens;

    private int largura_item;
    private int round_corner;

    private int selectedTab, selectedOption;

    public int getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(int selected) {
        this.selectedOption = selected;
    }

    public MenuBar() {
        unready();
        this.fonte = new Font("dialog", Font.PLAIN, (int) (18 * Properties.MODRESOL));
        this.rh = null;
        this.tema = new CTGTheme();
    }

    public MenuBar(RenderingHints graphs, Font font) {
        this();
        this.setGraphics(graphs, font);
    }

    public MenuBar(RenderingHints graphs, Font font, Dimension size) {
        this(graphs, font);
        this.setDimension(size);
    }

    public void addItem(MenuItem item) {
        itensList.add(item);
    }

    public void addItem(String text, boolean disabled) {
        MenuItem item = new MenuItem();
        item.setText(text);
        item.disable(disabled);
        this.addItem(item);
        unready();
    }

    public void addItem(String text) {
        MenuItem item = new MenuItem();
        item.setText(text);
        item.disable(false);
        this.addItem(item);
        unready();
    }

    public MenuItem getSelectedMenu() {
        verificarProntidao();
        return itens[selectedTab];
    }

    public MenuItem[] getMenus() {
        verificarProntidao();
        return itens;
    }

    @Override
    public void setReady() {
        this.itens = this.itensList.toArray(new MenuItem[itensList.size()]);
        this.largura_item = this.Size.width / this.itens.length;
        this.round_corner = (5 * this.Size.height) / 33;
        createBuffer();
        this.finalizado = true;
    }

    @Override
    void unready() {
        this.finalizado = false;
        this.largura_item = 0;
        this.round_corner = 0;
    }

    public void setSelectedTab(int selected) {
        this.selectedTab = selected;
    }

    public void setSelectedTab(String tabname) {
        for (int i = 0; i < itens.length; i++) {
            if (itens[i].getText().equals(tabname)) {
                this.setSelectedTab(i);
            }
        }
    }

    public int getSelectedTab() {
        return this.selectedTab;
    }

    @Override
    public BufferedImage draw(int selected, int selectedoption, RenderingHints hints) {
        clearBuffer();
        verificarProntidao();
        this.setSelectedTab(selected);
        this.setSelectedOption(selectedoption);
        return this.draw();
    }

    @Override
    public BufferedImage draw() {
        g2d.setColor(tema.options_header_background);
        g2d.fillRect(0, 0, this.Size.width, this.Size.height);
        for (int i = 0; i < itens.length; i++) {
            itens[i].centerTextPosition(g2d.getFontMetrics(), i * largura_item, largura_item);
            if (this.selectedTab == i && this.selectedOption == 0) {
                if (!itens[i].isDisabled()) {
                    g2d.setColor(tema.options_background_hover);
                    g2d.fillRoundRect(i * largura_item, 0, largura_item, this.Size.height, this.round_corner, this.round_corner);
                    g2d.setColor(tema.options_text_hover);
                    g2d.drawString(itens[i].getText(), itens[i].getTextPos(), (int) (25 * Properties.MODRESOL));
                } else {
                    g2d.setColor(tema.options_background_disabled_hover);
                    g2d.fillRoundRect(i * largura_item, 0, largura_item, this.Size.height, this.round_corner, this.round_corner);
                    g2d.setColor(tema.options_text_disabled_hover);
                    g2d.drawString(itens[i].getText(), itens[i].getTextPos(), (int) (25 * Properties.MODRESOL));
                }

            } else if (this.selectedTab == i && this.selectedOption != 0) {
                g2d.setColor(tema.options_backdround_selected);
                g2d.fillRoundRect(i * largura_item, 0, largura_item, this.Size.height, this.round_corner, this.round_corner);
                g2d.setColor(tema.options_text_selected);
                g2d.drawString(itens[i].getText(), itens[i].getTextPos(), (int) (25 * Properties.MODRESOL));
            } else {
                if (!itens[i].isDisabled()) {
                    g2d.setColor(tema.options_text);
                    g2d.drawString(itens[i].getText(), itens[i].getTextPos(), (int) (25 * Properties.MODRESOL));
                } else {
                    g2d.setColor(tema.options_text_disabled);
                    g2d.drawString(itens[i].getText(), itens[i].getTextPos(), (int) (25 * Properties.MODRESOL));
                }
            }
        }
        g2d.setColor(tema.options_divider);
        for (int i = 0; i < 1 * Properties.MODRESOL; i++) {
            g2d.drawLine((int) (33 * Properties.MODRESOL), i, this.Size.width - (int) (66 * Properties.MODRESOL), i);
            g2d.drawLine((int) (33 * Properties.MODRESOL), this.Size.height - (i + 1), this.Size.width - (int) (66 * Properties.MODRESOL), this.Size.height - (i + 1));
        }
        return this.back_buffer;
    }

}
