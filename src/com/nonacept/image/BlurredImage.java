/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nonacept.image;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author Douglas Rocha de Oliveira
 */
public class BlurredImage {

    private BufferedImage imageSrc, imageResult;
    
    public BlurredImage(){
    }

    public BlurredImage(BufferedImage src) {
        imageSrc = src;
    }

    public BlurredImage(Image src) {
        imageSrc = (BufferedImage) src;
    }

    public BlurredImage(String src_path) throws MalformedURLException, IOException {
        imageSrc = ImageIO.read(new URL(src_path));
    }

    public BufferedImage blur() {
        /*imageResult = new BufferedImage(imageSrc.getWidth(), imageSrc.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

        Graphics g1 = imageResult.getGraphics();
        g1.drawImage(imageSrc, 0, 0, null);*/

        float[] blurKernel = {1 / 9f, 1 / 9f, 1 / 9f, 1 / 9f, 1 / 9f, 1 / 9f, 1 / 9f, 1 / 9f, 1 / 9f};

        BufferedImageOp blur = new ConvolveOp(new Kernel(3, 3, blurKernel));
        imageSrc = blur.filter(imageSrc, null);
        

        return imageSrc;
    }
    
    public static BufferedImage blur(BufferedImage src){
        float[] blurKernel = {1 / 5f, 1 / 5f, 1 / 5f, 1 / 5f, 1 / 5f, 1 / 5f, 1 / 5f, 1 / 5f, 1 / 5f};
        
        BufferedImageOp blur = new ConvolveOp(new Kernel(3, 3, blurKernel));
        
        src = blur.filter(src, null);
        
        return src;
        
    }

}
