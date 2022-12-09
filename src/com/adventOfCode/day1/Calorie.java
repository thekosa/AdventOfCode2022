package com.adventOfCode.day1;

import com.adventOfCode.Modules.AoC;
import com.adventOfCode.Modules.Scan;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Calorie implements AoC {
    private final List<String> linie;
    private List<Integer> sums;
    private int maxValue;
    private int sumOfSums;

    public Calorie() throws FileNotFoundException {
        linie = new Scan(this.getClass()).createFileList();
    }

    private void setSums() {
        sums = new ArrayList<>();
        int tempSum = 0;
        for (String s : linie) {
            if (!s.matches("")) {
                tempSum += Integer.parseInt(s);
            } else {
                sums.add(tempSum);
                tempSum = 0;
            }
        }
    }

    private void setMaxValue() {
        maxValue = sums.get(0);
        for (Integer i : sums) {
            if (i > maxValue)
                maxValue = i;
        }
    }

    private void setSumOfSums() {
        int[] maxValues = new int[3];
        sumOfSums = 0;
        for (int i = 0; i < maxValues.length; i++) {
            maxValues[i] = maxValue;
            sumOfSums += maxValues[i];
            maxValue = 0;
            for (Integer integer : sums) {
                if (integer > maxValue && integer < maxValues[i])
                    maxValue = integer;
            }
        }
    }

    private int getMaxValue() {
        return maxValue;
    }

    private int getSumOfSums() {
        return sumOfSums;
    }

    public void operate() {
        setSums();
        setMaxValue();
        setSumOfSums();
    }

    @Override
    public Object getResultPart1() {
        return getMaxValue();
    }

    @Override
    public Object getResultPart2() {
        return getSumOfSums();
    }

    @Override
    public void execute() {
        operate();
    }
}
