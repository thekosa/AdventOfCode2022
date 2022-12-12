package com.adventOfCode.day8;

import com.adventOfCode.Modules.AoC;
import com.adventOfCode.Modules.Scan;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TallTreesForest implements AoC {
    private final int[][] trees;
    private final int max_hor_index;
    private final int max_vert_index;
    private final int hor_length;
    private final int vert_length;
    private final int min_index;
    private int visibleTreesSum;
    private final Map<String, Integer> visibleTreesRegistry;
    private int greatestScenicScore;

    public TallTreesForest() throws FileNotFoundException {
        trees = new Scan(this.getClass()).createFileIntMatrix();
        max_hor_index = trees.length - 2;
        max_vert_index = trees[0].length - 2;
        hor_length = trees.length;
        vert_length = trees[0].length;
        visibleTreesSum = 0;
        visibleTreesRegistry = new HashMap<>();
        min_index = 1;
    }

    public boolean isTallestFromLeft(int indexHor, int indexVert) {
        for (int i = 0; i < indexVert; i++) {
            if (trees[indexHor][indexVert] <= trees[indexHor][i]) {
                return false;
            }
        }
        return true;
    }

    public boolean isTallestFromRight(int indexHor, int indexVert) {
        for (int i = vert_length - 1; i > indexVert; i--) {
            if (trees[indexHor][indexVert] <= trees[indexHor][i]) {
                return false;
            }
        }
        return true;
    }

    public boolean isTallestFromUp(int indexHor, int indexVert) {
        for (int i = 0; i < indexHor; i++) {
            if (trees[indexHor][indexVert] <= trees[i][indexVert]) {
                return false;
            }
        }
        return true;
    }

    public boolean isTallestFromDown(int indexHor, int indexVert) {
        for (int i = hor_length - 1; i > indexHor; i--) {
            if (trees[indexHor][indexVert] <= trees[i][indexVert]) {
                return false;
            }
        }
        return true;
    }

    private String key(int i, int j) {
        return i + ":" + j;
    }

    private void searchTheTrees() {
        for (int i = min_index; i <= max_hor_index; i++) {
            for (int j = min_index; j <= max_vert_index; j++) {
                if (isTallestFromLeft(i, j) && visibleTreesRegistry.get(key(i, j)) == null) {
                    visibleTreesSum++;
                    visibleTreesRegistry.put(key(i, j), trees[i][j]);
                }
            }
            for (int j = max_vert_index; j >= min_index; j--) {
                if (isTallestFromRight(i, j) && visibleTreesRegistry.get(key(i, j)) == null) {
                    visibleTreesSum++;
                    visibleTreesRegistry.put(key(i, j), trees[i][j]);
                }
            }
        }
        for (int i = min_index; i <= max_vert_index; i++) {
            for (int j = min_index; j <= max_hor_index; j++) {
                if (isTallestFromUp(i, j) && visibleTreesRegistry.get(key(i, j)) == null) {
                    visibleTreesSum++;
                    visibleTreesRegistry.put(key(i, j), trees[i][j]);
                }
            }
            for (int j = max_hor_index; j >= min_index; j--) {
                if (isTallestFromDown(i, j) && visibleTreesRegistry.get(key(i, j)) == null) {
                    visibleTreesSum++;
                    visibleTreesRegistry.put(key(i, j), trees[i][j]);
                }
            }
        }
    }

    //direction: from 1 to 4; 1-right, 2-down, 3-left, 4-up
    private int calculateViewingDist(int indexHor, int indexVert, int direction) {
        int checkingTree = trees[indexHor][indexVert];
        switch (direction) {
            case 1 -> {
                for (int i = indexVert + 1; i < hor_length; i++) {
                    if (checkingTree <= trees[indexHor][i]) {
                        return i - indexVert;
                    }
                }
                return hor_length - indexVert - 1;
            }
            case 2 -> {
                for (int i = indexHor + 1; i < vert_length; i++) {
                    if (checkingTree <= trees[i][indexVert]) {
                        return i - indexHor;
                    }
                }
                return vert_length - indexHor - 1;
            }
            case 3 -> {
                for (int i = indexVert - 1; i >= 0; i--) {
                    if (checkingTree <= trees[indexHor][i]) {
                        return indexVert - i;
                    }
                }
                return indexVert;
            }
            case 4 -> {
                for (int i = indexHor - 1; i >= 0; i--) {
                    if (checkingTree <= trees[i][indexVert]) {
                        return indexHor - i;
                    }
                }
                return indexHor;
            }
            default -> throw new IllegalArgumentException();
        }
    }

    private void calulateScenicScore() {
        List<Integer> scenicScores = new ArrayList<>();
        visibleTreesRegistry.forEach((key, value) -> {
            int indexHor = Integer.parseInt(key.split(":")[0]);
            int indexVert = Integer.parseInt(key.split(":")[1]);
            scenicScores.add(calculateViewingDist(indexHor, indexVert, 1)
                    * calculateViewingDist(indexHor, indexVert, 2)
                    * calculateViewingDist(indexHor, indexVert, 3)
                    * calculateViewingDist(indexHor, indexVert, 4));
        });
        greatestScenicScore = 0;
        for (int currScenicScore : scenicScores) {
            if (greatestScenicScore < currScenicScore) {
                greatestScenicScore = currScenicScore;
            }
        }
    }

    private void addEdgedTrees() {
        visibleTreesSum += hor_length * 2 + vert_length * 2 - 4;
    }

    private int getVisibleTreesSum() {
        return visibleTreesSum;
    }

    private int getGreatestScenicScore() {
        return greatestScenicScore;
    }

    @Override
    public Object getResultPart1() {
        return getVisibleTreesSum();
    }

    @Override
    public Object getResultPart2() {
        return getGreatestScenicScore();
    }

    @Override
    public void execute() {
        searchTheTrees();
        addEdgedTrees();
        calulateScenicScore();
    }
}
