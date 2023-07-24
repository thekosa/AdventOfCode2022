package com.adventOfCode.day9;

public class Vector extends Position {
    public enum Direction {
        U, D, R, L;
    }

    private final Direction direction;
    private final int value;

    public Vector(int positionX, int positionY, Direction direction) {
        super(positionX, positionY);
        this.value = (int) Math.sqrt(Math.pow(positionX, 2) + Math.pow(positionY, 2));
        this.direction = direction;
    }

    public Vector(Direction direction) {
        value = 1;
        this.direction = direction;
        if (direction.equals(Direction.R)) {
            setPositionX(1);
            setPositionY(0);
        } else if (direction.equals(Direction.L)) {
            setPositionX(-1);
            setPositionY(0);
        } else if (direction.equals(Direction.U)) {
            setPositionX(0);
            setPositionY(1);
        } else if (direction.equals(Direction.D)) {
            setPositionX(0);
            setPositionY(-1);
        }
    }

    public int getValue() {
        return value;
    }

    public Direction getDirection() {
        return direction;
    }
}
