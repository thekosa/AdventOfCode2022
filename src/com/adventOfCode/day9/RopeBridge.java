package com.adventOfCode.day9;

import com.adventOfCode.Modules.AoC;
import com.adventOfCode.Modules.Scan;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RopeBridge implements AoC {
    private final List<String> commands;
    private final double sqrtOf2 = Math.sqrt(2);
    private final List<Position> tailPositionsList = new ArrayList<>();
    private final Node head;
    private final Node tail;
    private static final int boundries= 100;

    public RopeBridge() throws FileNotFoundException {
        commands = new Scan(this.getClass()).createFileList();
        head = new Node(new Position(0, 0));
        tail = new Node(new Position(0, 0));
        tailPositionsList.add(new Position(0, 0));
    }

    private void operate() {
        for (String command : commands) {
            Vector vector = decryptCommand(command);
            for (int i = 0; i < vector.getValue(); i++) {
                head.moveBy1(vector.getDirection());
                if (calcDistance() > sqrtOf2) {
                    tail.setPosition(calculateTailPosition(vector));
                    addTailPositionToList();
                }
               // view();
            }
        }
    }

    private void view() {
        char[][] field = new char[boundries][boundries];
        for (int i = 0; i < boundries; i++) {
            for (int j = 0; j < boundries; j++) {
                field[i][j] = '.';
            }
        }
        field[head.getPosition().getPositionX()][head.getPosition().getPositionY()] = 'h';
        field[tail.getPosition().getPositionX()][tail.getPosition().getPositionY()] = 't';
        for (int i = 0; i < boundries; i++) {
            System.out.println(Arrays.toString(field[i]));
        }
        System.out.println("next");
    }

    private void addTailPositionToList() {
        Position tailPosition = tail.getPosition();
        for (Position position : tailPositionsList) {
            if (position.getPositionX() == tailPosition.getPositionX() && position.getPositionY() == tailPosition.getPositionY()) {
                return;
            }
        }
        tailPositionsList.add(tailPosition);
    }

    private Position calculateTailPosition(Position vector) {
        if (vector.getPositionX() < 0) {
            return new Position(head.getPosition().getPositionX() + 1, head.getPosition().getPositionY());
        } else if (vector.getPositionX() > 0) {
            return new Position(head.getPosition().getPositionX() - 1, head.getPosition().getPositionY());
        } else if (vector.getPositionY() < 0) {
            return new Position(head.getPosition().getPositionX(), head.getPosition().getPositionY() + 1);
        } else if (vector.getPositionY() > 0) {
            return new Position(head.getPosition().getPositionX(), head.getPosition().getPositionY() - 1);
        } else {
            return tail.getPosition();
        }
    }

    private double calcDistance() {
        return Math.sqrt(Math.pow(head.getPosition().getPositionX() - tail.getPosition().getPositionX(), 2)
                + Math.pow(head.getPosition().getPositionY() - tail.getPosition().getPositionY(), 2));
    }

    private Vector decryptCommand(String command) {
        Vector.Direction direction = Vector.Direction.valueOf(command.split(" ")[0]);
        int steps = Integer.parseInt(command.split(" ")[1]);
        return switch (direction) {
            case R -> new Vector(steps, 0, direction);
            case L -> new Vector(-steps, 0, direction);
            case U -> new Vector(0, steps, direction);
            case D -> new Vector(0, -steps, direction);
        };
    }

    @Override
    public Object getResultPart1() {
        return tailPositionsList.size();
    }

    @Override
    public Object getResultPart2() {
        return null;
    }

    @Override
    public void execute() {
        operate();
    }
}
