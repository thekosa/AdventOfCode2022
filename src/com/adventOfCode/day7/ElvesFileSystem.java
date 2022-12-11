package com.adventOfCode.day7;

import com.adventOfCode.Modules.AoC;
import com.adventOfCode.Modules.Scan;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ElvesFileSystem implements AoC {
    private final Directory home;
    private final List<String> commands;
    private static final int MAX_TOTAL_SIZE = 100000;
    private static final int TOTAL_DISK_SPACE = 70000000;
    private static final int SPACE_NEEDED = 30000000;
    private int totalSizesSum;
    private final List<Integer> totalSizes;
    private int spaceToFree;
    private int sizeDirToDelete;

    public ElvesFileSystem() throws FileNotFoundException {
        commands = new Scan(this.getClass()).createFileList();
        home = new Directory("/", null);
        totalSizesSum = 0;
        totalSizes = new ArrayList<>();
    }

    private void operate() {
        Directory currDir = home;
        for (String command : commands) {
            if (command.startsWith("$")) {
                if (command.contains("cd")) {
                    if (command.contains("..")) {
                        currDir = currDir.getSuperDirectory();
                    } else if (command.contains("/")) {
                        currDir = home;
                    } else {
                        String dirName = command.split(" ")[2];
                        currDir = currDir.getSubDirectory(dirName);
                    }
                }
                if (command.contains("ls")) {
                    //System.out.println(currDir.ls());
                }
            } else {
                if (command.startsWith("dir")) {
                    currDir.newSubDirectory(command.split(" ")[1]);
                } else {
                    String[] tempFile = command.split(" ");
                    currDir.newFile(Integer.parseInt(tempFile[0]), tempFile[1]);
                }
            }
        }
    }

    private String printTree(Directory currDir, StringBuilder stringBuilder) {
        stringBuilder
                .append("\t".repeat(currDir.getLevel()))
                .append("level:")
                .append(currDir.getLevel())
                .append(" dir.\t")
                .append(currDir.getName())
                .append("\n");
        if (currDir.isRoot()) {
            return stringBuilder
                    .append(currDir.lsFiles())
                    .toString();
        } else {
            for (Directory dir : currDir.getSubDirectories()) {
                printTree(dir, stringBuilder);
            }
            return stringBuilder.append(currDir.lsFiles()).toString();
        }
    }

    private void calculateSizes(Directory currDir) {
        for (Directory dir : currDir.getSubDirectories()) {
            calculateSizes(dir);
        }
        int totalSize = currDir.getTotalSize();
        totalSizes.add(totalSize);
        if (totalSize <= MAX_TOTAL_SIZE) {
            totalSizesSum += totalSize;
        }
    }

    private int getFreeSpace() {
        int biggestTotalSize = totalSizes.get(0);
        for (int size : totalSizes) {
            if (biggestTotalSize < size) {
                biggestTotalSize = size;
            }
        }
        return TOTAL_DISK_SPACE - biggestTotalSize;
    }

    private void setSizeDirToDelete() {
        sizeDirToDelete = SPACE_NEEDED;
        for (int size : totalSizes) {
            if (size >= spaceToFree) {
                if (sizeDirToDelete > size) {
                    sizeDirToDelete = size;
                }
            }
        }
    }

    private int getSizeDirToDelete() {
        return sizeDirToDelete;
    }

    private int getTotalSizesSum() {
        return totalSizesSum;
    }

    @Override
    public Object getResultPart1() {
        return getTotalSizesSum();
    }

    @Override
    public Object getResultPart2() {
        return getSizeDirToDelete();
    }

    @Override
    public void execute() {
        operate();
        //System.out.println(printTree(home, new StringBuilder()));
        calculateSizes(home);
        spaceToFree = SPACE_NEEDED - getFreeSpace();
        setSizeDirToDelete();
    }
}
