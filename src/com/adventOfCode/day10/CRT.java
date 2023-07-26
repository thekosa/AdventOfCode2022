package com.adventOfCode.day10;

public class CRT {
    public static final int MAX_CYCLES = 240;
    public static final String HIGH_SIGN = "█";
    public static final String LOW_SIGN = " ";
    private int spritePosition;
    private final StringBuilder screen;

    public CRT() {
        spritePosition = 1;
        screen = new StringBuilder();
    }

    public boolean isSprite(int index) {
        return index == spritePosition - 1 || index == spritePosition || index == spritePosition + 1;
    }

    public void drawPixel(int index) {
        if (isSprite(index)) {
            screen.append(HIGH_SIGN);
        } else {
            screen.append(LOW_SIGN);
        }
        //logOfDrawing();
    }

    public void drawSprite() {
        StringBuilder row = new StringBuilder();
        for (int i = 0; i < 40; i++) {
            if (isSprite(i)) {
                row.append("#");
            } else {
                row.append(".");
            }
        }
        System.out.println(row);
    }

    public String getScreen(){
        return screen.toString();
    }

    public void draw() {
        System.out.println(screen);
    }

    public void nextRow() {
        screen.append("\n");
    }

    public void setNewSpritePosition(int newSpritePosition) {
        this.spritePosition += newSpritePosition;
    }

    public void setSpritePosition(int spritePosition) {
        this.spritePosition = spritePosition;
    }

    public int getSpritePosition() {
        return spritePosition;
    }

    private void logOfDrawing() {
        draw();
        System.out.println("\n");
        drawSprite();
        System.out.println("\n");
    }
}
