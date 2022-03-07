/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nonacept.ui;

import com.nonacept.Properties;
import com.nonacept.ui.components.PanelItem;
import com.nonacept.ui.components.PanelMessageItem;
import com.nonacept.ui.components.PanelMusicItem;
import com.nonacept.ui.themes.CTGTheme;
import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Douglas Rocha de Oliveira - NonaCept
 */
public class OptionsPanel extends VisualElement {

    private ArrayList<PanelItem> itemList = new ArrayList<>();
    private PanelItem[] itens;

    private int alturaItem, larguraItem, espacoItens, fSize, artSize;
    private int xItem, yItem, yPrimeiroTexto, deslocTexto, deslocValue, proximo;
    private Composite defaultComposite;
    private AlphaComposite alphaComposite;
    private int tamanhoAdicional = 0;

    private int selectedItem;

    public OptionsPanel() {
        unready();
        this.fonte = new Font("dialog", Font.PLAIN, (int) (18 * Properties.MODRESOL));
        this.rh = null;
        this.tema = new CTGTheme();
    }

    public OptionsPanel(RenderingHints rHints, Font font) {
        this();
        this.setGraphics(rHints, font);
    }

    public OptionsPanel(RenderingHints rHints, Font font, Dimension size) {
        this(rHints, font);
        this.setDimension(size);
    }

    @Override
    public void setReady() {
        itens = itemList.toArray(new PanelItem[itemList.size()]);
        this.espacoItens = (int) (27 * Properties.MODRESOL);
        this.larguraItem = (int) (300 * Properties.MODRESOL);
        this.alturaItem = (int) (22 * Properties.MODRESOL);
        this.xItem = (int) (10 * Properties.MODRESOL);
        this.yItem = (int) (18 * Properties.MODRESOL);
        this.yPrimeiroTexto = (int) (42 * Properties.MODRESOL);
        this.deslocTexto = (int) (15 * Properties.MODRESOL);
        this.deslocValue = (int) (220 * Properties.MODRESOL);
        this.fSize = (int) ((fonte.getSize() - 4) * Properties.MODRESOL);
        this.artSize = (int) (150 * Properties.MODRESOL);
        createBuffer();
        this.defaultComposite = g2d.getComposite();
        this.alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f);
        this.finalizado = true;
    }

    @Override
    void unready() {
        this.finalizado = false;
    }

    @Override
    public BufferedImage draw(int selectedtab, int selectedoption, RenderingHints hints) {
        verificarProntidao();
        this.setSelectedOption(selectedoption);

        return this.draw();
    }

    public OptionsPanel add(PanelItem item) {
        this.addItem(item);
        return this;
    }

    public OptionsPanel add(String text, String[] values) {
        this.addItem(text, values);
        return this;
    }

    public void addItem(PanelItem item) {
        this.unready();
        this.itemList.add(item);
    }

    public PanelItem[] getItens() {
        verificarProntidao();
        return itens;
    }

    public void addItem(String text, String[] values) {
        PanelItem item = new PanelItem();
        item.setProperties(text, values);
        this.addItem(item);
    }

    public void addItem(String text, String[] values, boolean disabled) {
        PanelItem item = new PanelItem();
        item.setProperties(text, values);
        item.disable(disabled);
        this.addItem(item);
    }

    public void addMessage(String message) {
        PanelMessageItem item = new PanelMessageItem();
        item.setText(message);
        this.add(item);
    }

    public void setSelectedValue(String text, String value) {
        for (int i = 0; i < itens.length; i++) {
            if (itens[i].getText().equals(text)) {
                itens[i].setSelectedValue(value);
            }
        }
    }

    public void setSelectedValue(int prop, int valueid) {
        itens[prop].setSelectedValue(valueid);
    }

    public void setSelectedOption(String prop) {
        for (int i = 0; i < itens.length; i++) {
            if (itens[i].getText().equals(prop)) {
                this.selectedItem = i;
            }
        }
    }

    public void setSelectedOption(int prop) {
        this.selectedItem = prop;
    }

    @Override
    public BufferedImage draw() {
        clearBuffer();
        int real_y = this.selectedItem - 1;
        if (this.selectedItem > 0) {
            g2d.setColor(tema.options_panel_background);
            g2d.fillRect(0, 0, this.Size.width, this.Size.height);
        }
        proximo = yPrimeiroTexto;
        for (int i = 0; i < itens.length; i++) {
            if (this.selectedItem > 0) {
                if (i == real_y) {
                    if (itens[i].getClass().equals(PanelMusicItem.class)) {
                        drawHoverMusic(i, real_y);
                    } else if (itens[i].getClass().equals(PanelMessageItem.class)) {
                        drawMessage(i);
                    } else {
                        drawHoverItem(i, real_y);
                    }
                } else {
                    if (itens[i].getClass().equals(PanelMessageItem.class)) {
                        drawMessage(i);
                    } else {
                        drawItem(i);
                    }
                }
            } else {
                if (itens[i].getClass().equals(PanelMessageItem.class)) {
                    drawMessage(i);
                } else {
                    drawItem(i);
                }
            }
            proximo += espacoItens + tamanhoAdicional;
        }
        return back_buffer;
    }

    private void drawHoverItem(int i, int real_y) {
        tamanhoAdicional = 0;
        g2d.setColor(tema.options_background_hover);
        g2d.fillRect(xItem, proximo - yItem, larguraItem, alturaItem + tamanhoAdicional);
        g2d.setColor(tema.options_text_hover);
        g2d.drawString(itens[i].getText(), deslocTexto, proximo);
        g2d.setColor(tema.options_text_hover);
        g2d.drawString(itens[i].getValue(), deslocValue, proximo);
    }

    private void drawItem(int i) {
        g2d.setColor(tema.options_text);
        g2d.drawString(itens[i].getText(), deslocTexto, proximo);
        g2d.drawString(itens[i].getValue(), deslocValue, proximo);
        tamanhoAdicional = 0;
    }

    private void drawHoverMusic(int i, int real_y) {
        tamanhoAdicional = (int) (220 * Properties.MODRESOL);
        PanelMusicItem music = (PanelMusicItem) itens[i];
        g2d.setColor(tema.options_background_hover);
        g2d.fillRect(xItem, proximo - yItem, larguraItem, alturaItem + tamanhoAdicional);
        g2d.setComposite(alphaComposite);
        g2d.drawImage(music.getAlbumArt(), larguraItem - artSize - xItem, proximo + xItem, artSize, artSize, null);
        g2d.setComposite(defaultComposite);
        g2d.setColor(tema.options_text_hover);
        g2d.drawString(music.getText(), deslocTexto, proximo);
        g2d.drawString(music.getValue(), deslocValue, proximo);
        g2d.setFont(this.fonte.deriveFont(Font.PLAIN, fSize));
        g2d.drawString(music.getArtista(), deslocTexto, proximo + espacoItens);
        g2d.drawString(music.getAlbum(), deslocTexto, proximo + (espacoItens * 2));

        g2d.setFont(fonte);

    }

    private void drawMessage(int i) {
        g2d.setColor(tema.options_text);
        itens[i].centerTextPosition(g2d.getFontMetrics(), 0, this.back_buffer.getWidth());
        g2d.drawString(itens[i].getText(), itens[i].getTextPos(), proximo + xItem);
        tamanhoAdicional = xItem;
    }

}
