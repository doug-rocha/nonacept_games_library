/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nonacept.ui.themes;

import java.awt.Color;

/**
 *
 * @author Douglas Rocha de Oliveira - NonaCept
 */
public class CTGTheme extends Theme {

    public CTGTheme() {
        this.options_text = new Color(150, 150, 150);
        this.options_text_hover = new Color(255, 255, 255);
        this.options_text_selected = new Color(200, 200, 200);
        this.options_text_disabled = new Color(150, 100, 100, 200);
        this.options_text_disabled_hover = new Color(200, 150, 150);

        this.options_header_background = new Color(200, 200, 200, 50);
        this.options_backdround_selected = new Color(100, 100, 100, 50);
        this.options_background_hover = new Color(200, 200, 200, 50);
        this.options_background_disabled_hover = new Color(125, 100, 100, 75);
        this.options_panel_background = new Color(0, 0, 0, 45);

        this.options_divider = new Color(200, 200, 200, 20);
    }
}
