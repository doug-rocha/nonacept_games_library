/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nonacept.audio.mp3;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Douglas Rocha de Oliveira - NonaCept
 */
class Decoder implements Runnable {

    private SourceDataLine line;
    private float ganho;
    private File arquivo;
    private boolean completo;

    public Decoder() {
        ganho = 0.0f;
    }

    @Override
    public void run() {
        try {
            this.completo = false;
            play(this.arquivo);
            this.completo = true;
        } catch (IOException ex) {
            Logger.getLogger(Decoder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Decoder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Decoder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void play(File file)
            throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        AudioInputStream in = AudioSystem.getAudioInputStream(file);
        AudioInputStream din = null;
        AudioFormat baseFormat = in.getFormat();
        AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                baseFormat.getSampleRate(),
                16,
                baseFormat.getChannels(),
                baseFormat.getChannels() * 2,
                baseFormat.getSampleRate(),
                false);
        din = AudioSystem.getAudioInputStream(decodedFormat, in);
        rawPlay(decodedFormat, din);
        in.close();
    }

    public void setFile(String file) {
        setFile(new File(file));
    }

    public void setFile(File file) {
        this.arquivo = file;
    }

    private void rawPlay(AudioFormat targetFormat, AudioInputStream din)
            throws IOException, LineUnavailableException {
        byte[] data = new byte[4096];
        line = getLine(targetFormat);
        if (line != null) {
            applyGain();
            line.start();

            int nBytesRead = 0, nBytesWritten = 0;
            while (nBytesRead != -1) {
                nBytesRead = din.read(data, 0, data.length);
                if (nBytesRead != -1) {
                    nBytesWritten = line.write(data, 0, nBytesRead);
                }
            }
            line.drain();
            line.stop();
            line.close();
            din.close();
        }
    }

    private SourceDataLine getLine(AudioFormat audioFormat)
            throws LineUnavailableException {
        SourceDataLine res = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        res = (SourceDataLine) AudioSystem.getLine(info);
        res.open();
        return res;
    }

    private void applyGain() {
        FloatControl gainControl = (FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(this.ganho);
    }

    public void setGain(float gain, boolean instantApply) {
        setGain(gain);
        if (instantApply) {
            applyGain();
        }
    }

    public void setGain(float gain) {
        this.ganho = gain;
    }
    
    public boolean isCompleto(){
        return this.completo;
    }

}
