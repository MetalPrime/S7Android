package com.example.s7android;

public class Coordinate {

    private String posX;
    private String posY;

    public Coordinate(String posX, String posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public Coordinate(){

    }

    public String getPosX() {
        return posX;
    }

    public void setPosX(String posX) {
        this.posX = posX;
    }

    public String getPosY() {
        return posY;
    }

    public void setPosY(String posY) {
        this.posY = posY;
    }
}
