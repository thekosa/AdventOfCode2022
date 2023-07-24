package com.adventOfCode.day9;

public class Node {

    private Position position;

    public Node(Position position) {
        this.position = position;
    }

    public void move(Vector vector) {
        position.addPosition(vector);
    }

    public void moveBy1(Vector.Direction direction) {
        move(new Vector(direction));
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
