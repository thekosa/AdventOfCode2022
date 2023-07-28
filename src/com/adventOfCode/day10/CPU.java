package com.adventOfCode.day10;

import com.AdventOfCode.primitive.OCR.OCR;
import com.adventOfCode.Modules.AoC;
import com.adventOfCode.Modules.Scan;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CPU implements AoC {
    private final List<String> commands;
    private static final int MAX_CYCLES = 220;
    private int registerX;
    private final List<Integer> signalStrengths = new ArrayList<>();
    private final CRT crt = new CRT();

    public CPU() throws FileNotFoundException {
        commands = new Scan(this.getClass()).createFileList();
        registerX = 1;
        crt.setSpritePosition(1);
    }

    private void compute() {
        for (int i = 1, j = 0; i <= MAX_CYCLES && j < commands.size(); i++, j++) {
            String command = commands.get(j);
            calculateSignalStrenth(i);
            if (command.contains("addx")) {
                i++;
                calculateSignalStrenth(i);
                registerX += Integer.parseInt(command.split(" ")[1]);
            }
        }
    }

    private void computeForCRT() {
        for (int i = 1, j = 0; i <= CRT.MAX_CYCLES && j < commands.size(); i++, j++) {
            String command = commands.get(j);
            crt.drawPixel((i - 1) % 40);
            ifNextRow(i);
            if (command.contains("addx")) {
                i++;
                crt.drawPixel((i - 1) % 40);
                ifNextRow(i);
                crt.setNewSpritePosition(Integer.parseInt(command.split(" ")[1]));
            }
        }
    }

    private void ifNextRow(int i) {
        if (i % 40 == 0) {
            crt.nextRow();
        }
    }

    private void calculateSignalStrenth(int i) {
        if (i == 20 || (i - 20) % 40 == 0) {
            signalStrengths.add(registerX * i);
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
        return OCR.convert(crt.getScreen(), CRT.LOW_SIGN, CRT.HIGH_SIGN);
    }

    @Override
    public void execute() {
        compute();
        computeForCRT();
        //crt.draw();
    }
}
