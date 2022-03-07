/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nonacept.ui;

import com.nonacept.ui.themes.CTGTheme;
import com.nonacept.ui.themes.Theme;
import com.nonacept.ui.themes.Themes;
import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 *
 * @author Douglas Rocha de Oliveira - NonaCept
 */
abstract class VisualElement {

    Dimension Size;
    Theme tema;

    RenderingHints rh;
    Font fonte;

    boolean finalizado;

    BufferedImage back_buffer;
    Graphics2D g2d;

    public void setGraphics(RenderingHints graphs, Font font) {
        this.rh = graphs;
        this.fonte = font;
    }

    public abstract void setReady();

    abstract void unready();

    public boolean isReady() {
        return this.finalizado;
    }

    /**
     * Set one of the provided themes
     *
     * @param theme use the provided enumerator to load one of the themes;
     */
    public void setTheme(Themes theme) {
        if (theme == Themes.CTG) {
            this.tema = new CTGTheme();
        } else {
            throw new UnsupportedOperationException("For now the only theme supported is the CTG Theme");
        }
    }

    /**
     * Set a custom theme
     *
     * @param theme use a custom theme created with the class. Tip: maybe you
     * can use serialization to load a theme, but, for now, is on yours. We
     * don't support loading custom/external themes yet
     */
    public void setTheme(Theme theme) {
        this.tema = theme;
    }

    public abstract BufferedImage draw(int selected, int selectedOption, RenderingHints hints);

    public BufferedImage draw(int selected, int selectedOption) {
        if (this.rh == null) {
            throw new IllegalStateException("You must set a Rendering hints and a Font in order to do this. Use the constructor or the methos called setGraphics().");
        } else {
            return this.draw(selected, selectedOption, this.rh);
        }
    }
    
    public abstract BufferedImage draw();

    void clearBuffer() {
        g2d.setComposite(AlphaComposite.Clear);
        g2d.fillRect(0, 0, this.Size.width, this.Size.height);
        g2d.setComposite(AlphaComposite.SrcOver);
    }

    void createBuffer() {
        back_buffer = new BufferedImage(this.Size.width, this.Size.height, BufferedImage.TYPE_INT_ARGB);
        g2d = (Graphics2D) back_buffer.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, rh.get(RenderingHints.KEY_ANTIALIASING));
        g2d.setFont(this.fonte);
    }

    public void setDimension(Dimension size) {
        this.Size = size;
    }

    public void setDimension(int width, int height) {
        this.setDimension(new Dimension(width, height));
    }
    
    void verificarProntidao(){
        if (!this.isReady()){
            throw new IllegalStateException("You must set ready in order to use. Use the method setReady() when you're ready.");
        }
    }
}
