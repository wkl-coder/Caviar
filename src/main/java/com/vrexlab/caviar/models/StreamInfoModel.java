package com.vrexlab.caviar.models;

public class StreamInfoModel {
    private String info;
    private boolean isChoose;


    public StreamInfoModel(String info, boolean isChoose) {
        this.info = info;
        this.isChoose = isChoose;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }

}
