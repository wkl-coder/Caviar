package com.vrexlab.caviar.models;

public class ChooseStreamModel {

    private int fps;
    private int pxiel;
    private int bits;

    public ChooseStreamModel(int fps, int pxiel, int bits) {
        this.fps = fps;
        this.pxiel = pxiel;
        this.bits = bits;
    }

    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    public int getPxiel() {
        return pxiel;
    }

    public void setPxiel(int pxiel) {
        this.pxiel = pxiel;
    }

    public int getBits() {
        return bits;
    }

    public void setBits(int bits) {
        this.bits = bits;
    }
}
