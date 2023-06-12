package com.radio.radioagakiza;

public class Umwidondoro {

    private String name;
    private int thumbnail;

    public String getSloga() {
        return sloga;
    }

    public void setSloga(String sloga) {
        this.sloga = sloga;
    }

    private String sloga;

    public Umwidondoro(String name, int thumbnail, String sloga) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.sloga = sloga;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
