package com.adventOfCode.day9;

public class Position {
    private int positionX;
    private int positionY;

    public Position(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Position() {

    }

    public void addPosition(Vector vector) {
        this.positionX += vector.getPositionX();
        this.positionY += vector.getPositionY();
    }


    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
}
