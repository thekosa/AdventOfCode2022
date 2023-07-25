package com.adventOfCode.day9;

public class Vector extends Position {
    public enum Direction {
        U, D, R, L, NONE, UR, UL, DR, DL
    }

    private Direction direction;
    private final int value;

    public Vector(int positionX, int positionY, Direction direction) {
        super(positionX, positionY);
        this.value = (int) Math.sqrt(Math.pow(positionX, 2) + Math.pow(positionY, 2));
        this.direction = direction;
    }

    public Vector(Position position) {
        value = 1;
        switch (position.getPositionX() + position.getPositionY()) {
            case 1 -> {
                if (position.getPositionX() != 0) {
                    direction = Direction.R;
                } else {
                    direction = Direction.U;
                }
            }
            case -1 -> {
                if (position.getPositionX() != 0) {
                    direction = Direction.L;
                } else {
                    direction = Direction.D;
                }
            }
            case 2 -> direction = Direction.UR;
            case -2 -> direction = Direction.DL;
            case 0 -> {
                if (position.getPositionX() < 0) {
                    direction = Direction.UL;
                } else if (position.getPositionX() > 0) {
                    direction = Direction.DR;
                } else {
                    direction = Direction.NONE;
                }
            }
        }
    }

    public Vector(Direction direction) {
        value = 1;
        this.direction = direction;
        switch (direction) {
            case R -> {
                setPositionX(1);
                setPositionY(0);
            }
            case L -> {
                setPositionX(-1);
                setPositionY(0);
            }
            case U -> {
                setPositionX(0);
                setPositionY(1);
            }
            case D -> {
                setPositionX(0);
                setPositionY(-1);
            }
            case UR -> {
                setPositionX(1);
                setPositionY(1);
            }
            case UL -> {
                setPositionX(-1);
                setPositionY(1);
            }
            case DR -> {
                setPositionX(1);
                setPositionY(-1);
            }
            case DL -> {
                setPositionX(-1);
                setPositionY(-1);
            }
            case NONE -> {
                setPositionX(0);
                setPositionY(0);
            }
        }
    }

    public static Vector calculateVector(Position positionAfterMove, Position positionBeforeMove) {
        return new Vector(new Position(
                positionAfterMove.getPositionX() - positionBeforeMove.getPositionX(),
                positionAfterMove.getPositionY() - positionBeforeMove.getPositionY()
        ));
    }

    public int getValue() {
        return value;
    }

    public Direction getDirection() {
        return direction;
    }
}
