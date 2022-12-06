package com.adventOfCode.day2;

import com.adventOfCode.Moduls.AoC;
import com.adventOfCode.Moduls.Scan;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class RockPaperScissors implements AoC {
    private final Map<String, Integer> solutionMap;
    private final Map<String, Integer> properSolutionMap;
    private final List<String> strategy;
    private int sum;
    private int properSum;

    public RockPaperScissors() throws FileNotFoundException {
        this.solutionMap = encryptSolution();
        this.properSolutionMap = encryptProperSolution();
        this.strategy = new Scan(this.getClass()).createFileList();
        this.sum = 0;
        this.properSum = 0;
    }

    private Map<String, Integer> encryptProperSolution() {
        HashMap<String, Integer> mapka = new HashMap<>();
        mapka.put("A X", 3);
        mapka.put("A Y", 4);
        mapka.put("A Z", 8);
        mapka.put("B X", 1);
        mapka.put("B Y", 5);
        mapka.put("B Z", 9);
        mapka.put("C X", 2);
        mapka.put("C Y", 6);
        mapka.put("C Z", 7);
        return mapka;
    }

    private Map<String, Integer> encryptSolution() {
        HashMap<String, Integer> mapka = new HashMap<>();
        mapka.put("A X", 4);
        mapka.put("A Y", 8);
        mapka.put("A Z", 3);
        mapka.put("B X", 1);
        mapka.put("B Y", 5);
        mapka.put("B Z", 9);
        mapka.put("C X", 7);
        mapka.put("C Y", 2);
        mapka.put("C Z", 6);
        return mapka;
    }

    private int getSum() {
        return this.sum;
    }

    private int getProperSum() {
        return this.properSum;
    }

    @Override
    public int getResultPart1() {
        return getSum();
    }

    @Override
    public int getResultPart2() {
        return getProperSum();
    }

    public void operate() {
        for (String s : strategy) {
            this.sum += solutionMap.get(s);
            this.properSum += properSolutionMap.get(s);
        }
    }
}
