package com.adventOfCode.day1;

import com.adventOfCode.Moduls.AoC;
import com.adventOfCode.Moduls.Scan;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Calorie implements AoC {
    private final List<String> linie;
    private List<Integer> sums;
    private int maxValue;
    private int sumOfSums;

    public Calorie() throws FileNotFoundException {
        this.linie = new Scan(this.getClass()).createFileList();
    }

    private void setSums() {
        this.sums = new ArrayList<>();
        int tempSum = 0;
        for (String s : this.linie) {
            if (!s.matches("")) {
                tempSum += Integer.parseInt(s);
            } else {
                this.sums.add(tempSum);
                tempSum = 0;
            }
        }
    }

    private void setMaxValue() {
        this.maxValue = sums.get(0);
        for (Integer i : sums) {
            if (i > this.maxValue)
                this.maxValue = i;
        }
    }

    private void setSumOfSums() {
        int[] maxValues = new int[3];
        this.sumOfSums = 0;
        for (int i = 0; i < maxValues.length; i++) {
            maxValues[i] = maxValue;
            this.sumOfSums += maxValues[i];
            this.maxValue = 0;
            for (Integer integer : this.sums) {
                if (integer > this.maxValue && integer < maxValues[i])
                    this.maxValue = integer;
            }
        }
    }

    private int getMaxValue() {
        return this.maxValue;
    }

    private int getSumOfSums() {
        return this.sumOfSums;
    }

    @Override
    public int getResultPart1() {
        return getMaxValue();
    }

    @Override
    public int getResultPart2() {
        return getSumOfSums();
    }

    public void operate() {
        setSums();
        setMaxValue();
        setSumOfSums();
    }

}
