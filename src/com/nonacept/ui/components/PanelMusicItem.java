/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nonacept.ui.components;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Douglas Rocha de Oliveira - NonaCept
 */
public class PanelMusicItem extends PanelItem {

    private String Artista, Album;
    private Image AlbumArt;
    
    public PanelMusicItem(String[] dados) {
        this.texto = dados[0];
        this.Artista = dados[1];
        this.Album = dados[2];
        this.AlbumArt = new ImageIcon(dados[3]).getImage();
    }
    
    public PanelMusicItem(String[] dados, Image album_art) {
        this.texto = dados[0];
        this.Artista = dados[1];
        this.Album = dados[2];
        this.AlbumArt = album_art;
    }

    public String getArtista() {
        return Artista;
    }

    public void setArtista(String Artista) {
        this.Artista = Artista;
    }

    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String Album) {
        this.Album = Album;
    }

    public Image getAlbumArt() {
        return AlbumArt;
    }

    public void setAlbumArt(Image AlbumArt) {
        this.AlbumArt = AlbumArt;
    }

    public void setAlbumArt(String Path) {
        this.AlbumArt = new ImageIcon(Path).getImage();
    }

    public void setTitle(String title) {
        this.texto = title;
    }

    public String[] getDados() {
        String[] dados = new String[3];
        dados[0] = this.texto;
        dados[1] = this.Artista;
        dados[2] = this.Album;
        return dados;
    }
}
