package com.adventOfCode.day9;

import com.adventOfCode.Modules.AoC;
import com.adventOfCode.Modules.Scan;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RopeBridge implements AoC {
    private static final int NODES_MAX = 10;
    private static final double SQRT_OF_2 = Math.sqrt(2);
    private final List<String> commands;
    private final List<Position> tailPositions = new ArrayList<>();
    private final List<Position> ropeTailPositions = new ArrayList<>();
    private final Node head;
    private final Node tail;
    private final List<Node> nodes = new ArrayList<>();

    public RopeBridge() throws FileNotFoundException {
        commands = new Scan(this.getClass()).createFileList();
        head = new Node(new Position(0, 0));
        tail = new Node(new Position(0, 0));
        tailPositions.add(new Position(0, 0));
        ropeTailPositions.add(new Position(0, 0));
        nodes.add(head);
        for (int i = 1; i < NODES_MAX; i++) {
            nodes.add(new Node(new Position(0, 0)));
        }
    }

    private void operate() {
        for (String command : commands) {
            Vector mainVector = decryptCommand(command);
            for (int i = 0; i < mainVector.getValue(); i++) {
                head.moveBy1(mainVector.getDirection());
                if (calcDistance(head, tail) > SQRT_OF_2) {
                    tail.setPosition(calculateTailPosition(mainVector, head, tail));
                    addTailPositionToList(tail, tailPositions);
                }
                Vector vector = mainVector;
                for (int j = 1; j < NODES_MAX; j++) {
                    if (calcDistance(nodes.get(j - 1), nodes.get(j)) > SQRT_OF_2) {
                        Position positionBeforeMove = nodes.get(j).getPosition();
                        nodes.get(j).setPosition(calculateTailPosition(vector, nodes.get(j - 1), nodes.get(j)));
                        Position positionAfterMove = nodes.get(j).getPosition();
                        vector = Vector.calculateVector(positionAfterMove, positionBeforeMove);
                        if (j == NODES_MAX - 1) {
                            addTailPositionToList(nodes.get(j), ropeTailPositions);
                        }
                    }
                }
               // view();
            }
        }
    }

    private void view() {
        int border = 50;
        char[][] field = new char[border][border];
        for (int i = 0; i < border; i++) {
            for (int j = 0; j < border; j++) {
                field[i][j] = '.';
            }
        }
        for (int i = 0; i < NODES_MAX; i++) {
            field[nodes.get(i).getPosition().getPositionX() + border / 2][nodes.get(i).getPosition().getPositionY() + border / 2] = i == 0 ? 'h' : (char) (i + 48);
        }
        for (int i = 0; i < border; i++) {
            System.out.println(Arrays.toString(field[i]));
        }
        System.out.println("next");
    }

    private void addTailPositionToList(Node tail, List<Position> tailPositions) {
        Position tailPosition = tail.getPosition();
        for (Position position : tailPositions) {
            if (position.getPositionX() == tailPosition.getPositionX() && position.getPositionY() == tailPosition.getPositionY()) {
                return;
            }
        }
        tailPositions.add(tailPosition);
    }

    private Position calculateTailPosition(Vector vector, Node head, Node tail) {
        return switch (vector.getDirection()) {
            case R -> new Position(head.getPosition().getPositionX() - 1, head.getPosition().getPositionY());
            case L -> new Position(head.getPosition().getPositionX() + 1, head.getPosition().getPositionY());
            case U -> new Position(head.getPosition().getPositionX(), head.getPosition().getPositionY() - 1);
            case D -> new Position(head.getPosition().getPositionX(), head.getPosition().getPositionY() + 1);
            case UR -> new Position(head.getPosition().getPositionX() - 1, head.getPosition().getPositionY() - 1);
            case UL -> new Position(head.getPosition().getPositionX() + 1, head.getPosition().getPositionY() - 1);
            case DR -> new Position(head.getPosition().getPositionX() - 1, head.getPosition().getPositionY() + 1);
            case DL -> new Position(head.getPosition().getPositionX() + 1, head.getPosition().getPositionY() + 1);
            case NONE -> tail.getPosition();
        };
    }

    private double calcDistance(Node head, Node tail) {
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
            case UR, DL, UL, DR, NONE -> null;
        };
    }

    @Override
    public Object getResultPart1() {
        return tailPositions.size();
    }

    @Override
    public Object getResultPart2() {
        return ropeTailPositions.size();
    }

    @Override
    public void execute() {
        operate();
    }
}
