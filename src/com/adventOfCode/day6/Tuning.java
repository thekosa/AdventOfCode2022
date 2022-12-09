package com.adventOfCode.day6;

import com.adventOfCode.Modules.AoC;
import com.adventOfCode.Modules.Scan;

import java.io.FileNotFoundException;

public class Tuning implements AoC {
    private final String dataStream;
    private final int dataStreamLength;
    private int startOfPacketMarker;
    private int startOfMessageMarker;

    public Tuning() throws FileNotFoundException {
        dataStream = new Scan(this.getClass()).createFileString();
        dataStreamLength = dataStream.length();
        startOfPacketMarker = 0;
        startOfMessageMarker = 0;
    }

    private int searchFirstMarker(int charNumbBefore) {
        //it doesn't check boundary condition, the last string which is checking is before the last letter
        //e.g.: string 10 letters: "abcdefghij", for charNumbBefore = 4 the last string is "ghij"
        //in this method the last checked string is "fghi"
        //I'm aware it doesn't work for every string,
        //but this is a simplification that i could have made in that type of exercise
        for (int i = charNumbBefore; i < dataStreamLength; i++) {
            if (isDifferent(dataStream.substring(i - charNumbBefore, i))) {
                return i;
            }
        }
        return 0;
    }

    private boolean isDifferent(String string) {
        for (int i = 0; i < string.length(); i++) {
            String letter = string.substring(i, i + 1);
            String restLetters = string.substring(0, i) + string.substring(i + 1);
            if (restLetters.contains(letter)) {
                return false;
            }
        }
        return true;
    }

    private int getStartOfPacketMarker() {
        return startOfPacketMarker;
    }

    private int getStartOfMessageMarker() {
        return startOfMessageMarker;
    }

    @Override
    public Object getResultPart1() {
        return getStartOfPacketMarker();
    }

    @Override
    public Object getResultPart2() {
        return getStartOfMessageMarker();
    }

    @Override
    public void execute() {
        startOfPacketMarker = searchFirstMarker(4);
        startOfMessageMarker = searchFirstMarker(14);
    }
}
