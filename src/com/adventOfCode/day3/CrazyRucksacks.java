package com.adventOfCode.day3;

import com.adventOfCode.Moduls.AoC;
import com.adventOfCode.Moduls.Scan;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class CrazyRucksacks implements AoC {

    private HashMap<Character, Integer> priorityMap;
    private final List<String> rucksack;
    private int singleRucksackSum;
    private int tripleRucksackSum;

    public CrazyRucksacks() throws FileNotFoundException {
        this.rucksack = new Scan(this.getClass()).createFileList();
        if (isRucksackNotCool()) {
            System.out.println("To gówno nie zadziała");
        }
        encrypt();
        this.singleRucksackSum = 0;
        this.tripleRucksackSum = 0;
    }

    private void encrypt() {
        this.priorityMap = new HashMap<>();

        char lc = 'a', up = 'A';
        int magic_numb = 26;
        for (int i = 1; i <= magic_numb; i++) {
            this.priorityMap.put(lc++, i);
            this.priorityMap.put(up++, i + magic_numb);
        }
    }

    private int decrypt(char letter) {
        return priorityMap.get(letter);
    }

    private boolean isRucksackNotCool() {
        for (String string : rucksack) {
            if (string.length() % 2 != 0) {
                return true;
            }
        }
        return false;
    }

    private int searchForItem(char[] compart1, char[] compart2) {
        for (char letter1 : compart1) {
            for (char letter2 : compart2) {
                if (letter1 == letter2) {
                    return decrypt(letter1);
                }
            }
        }
        System.out.println(Arrays.toString(compart1) + " : " + Arrays.toString(compart2));
        return 0;
    }

    private int searchForItem(char[] rucks1, char[] rucks2, char[] rucks3) {
        for (char letter1 : rucks1) {
            for (char letter2 : rucks2) {
                if (letter1 == letter2) {
                    for (char letter3 : rucks3) {
                        if (letter1 == letter3) {
                            return decrypt(letter1);
                        }
                    }
                }
            }
        }
        System.out.println(Arrays.toString(rucks1) + " : " + Arrays.toString(rucks2) + " : " + Arrays.toString(rucks3));
        return 0;
    }

    private int getSingleRucksackSum() {
        return singleRucksackSum;
    }

    private int getTripleRucksackSum() {
        return tripleRucksackSum;
    }

    @Override
    public int getResultPart1() {
        return getSingleRucksackSum();
    }

    @Override
    public int getResultPart2() {
        return getTripleRucksackSum();
    }

    public void operate() {
        for (String s : rucksack) {
            int half = s.length() / 2;
            this.singleRucksackSum += searchForItem(
                    s.substring(0, half).toCharArray(),
                    s.substring(half).toCharArray());
        }
        for (int i = 0; i < rucksack.size(); i++) {
            tripleRucksackSum += searchForItem(
                    rucksack.get(i).toCharArray(),
                    rucksack.get(++i).toCharArray(),
                    rucksack.get(++i).toCharArray());
        }
    }
}
