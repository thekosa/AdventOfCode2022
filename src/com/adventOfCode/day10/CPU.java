package com.adventOfCode.day10;

import com.adventOfCode.Modules.AoC;
import com.adventOfCode.Modules.Scan;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CPU implements AoC {
    private List<String> commands;
    private static final int MAX_CYCLES = 220;
    private int registerX;
    private final List<Integer> signalStrengths = new ArrayList<>();

    public CPU() throws FileNotFoundException {
        commands = new Scan(this.getClass()).createFileList();
        registerX = 1;
    }

    private void compute() {
        for (int i = 1, j = 0; i <= MAX_CYCLES && j < commands.size(); i++, j++) {
            String command = commands.get(j);
            if (i == 20 || (i - 20) % 40 == 0) {
                signalStrengths.add(registerX * i);
            }
            if (command.contains("addx")) {
                i++;
                if (i == 20 || (i - 20) % 40 == 0) {
                    signalStrengths.add(registerX * i);
                }
                registerX += Integer.parseInt(command.split(" ")[1]);
            }
        }
    }

    private int calculateSumSignalStrength() {
        int sum = 0;
        for (Integer i : signalStrengths) {
            sum += i;
        }
        return sum;
    }

    @Override
    public Object getResultPart1() {
        return calculateSumSignalStrength();
    }

    @Override
    public Object getResultPart2() {
        return null;
    }

    @Override
    public void execute() {
        compute();
    }
}
