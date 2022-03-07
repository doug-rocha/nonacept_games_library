/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nonacept.audio.mp3;

import java.io.File;

/**
 *
 * @author Douglas Rocha de Oliveira - NonaCept
 */
public class Player {

    private File audioFile;
    private Thread decoderThread;
    private Decoder dec;
    private boolean paused;

    private float ganho;

    public Player() {
        this.audioFile = null;
        this.ganho = 0.0f;
        this.paused = false;
        this.dec = new Decoder();
    }

    public Player(File SFXOri) {
        this();
        this.setFile(SFXOri);
    }

    public Player(File SFXOri, float vol) {
        this();
        this.setVol(vol);
        this.setFile(SFXOri);
    }

    public void play() {
        if (this.audioFile != null) {
            dec.setFile(this.audioFile);
            dec.setGain(this.ganho);
            decoderThread = new Thread(dec);
            decoderThread.start();
        } else {
            throw new IllegalStateException("You need to set the file you want to play");
        }
    }

    public void play(String URL) {
        this.setFile(URL);
        this.play();
    }

    public void play(File file) {
        this.setFile(file);
        this.play();
    }

    public void setFile(String URL) {
        this.setFile(new File(URL));
    }

    public void setFile(File file) {
        this.audioFile = file;
    }

    public File getFile() {
        return this.audioFile;
    }

    public String getFileName() {
        return this.audioFile.getName();
    }

    public void stop() {
        if (decoderThread != null) {
            decoderThread.stop();
        }
    }

    public void setVol(int vol) {
        if (vol > 100) {
            vol = 100;
        } else if (vol < 0) {
            vol = 0;
        }
        float gain = (float) (Math.log(vol) / Math.log(10) * 20);
        setVol(gain);
    }

    public void setVol(int vol, boolean inst) {
        if (vol > 100) {
            vol = 100;
        } else if (vol < 0) {
            vol = 0;
        }
        float gain = (float) (Math.log(vol) / Math.log(10) * 20);
        setVol(gain, inst);
    }

    public void setVol(float gain) {
        this.ganho = gain;
        dec.setGain(gain);
    }

    public void setVol(float gain, boolean inst) {
        this.ganho = gain;
        dec.setGain(gain, inst);
    }

    public boolean isComplete() {
        return dec.isCompleto();
    }

    public void pause() throws InterruptedException {
        if (!this.paused) {
            decoderThread.wait();
            this.paused = true;
        } else {
            decoderThread.notify();
            this.paused = false;
        }
    }

}
