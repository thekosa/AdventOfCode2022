package com.adventOfCode.Modules;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Scan {
    private Scanner scanner;

    public Scan(String fileName) throws FileNotFoundException {
        this.scanner = new Scanner(new File("src\\files\\" + fileName));
    }

    public Scan(Class<?> c) throws FileNotFoundException {
        this.scanner = new Scanner(new File("src\\files\\" + c.getPackageName().split("[.]")[2] + ".txt"));
    }

    public int[][] createFileIntMatrix() {
        List<String> tempList = createFileList();
        scanner.reset();
        int[][] matrix = new int[tempList.get(1).length()][tempList.size()];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = tempList.get(i).toCharArray()[j] - '0';
            }
        }
        return matrix;
    }

    public String createFileString() {
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
        }
        return stringBuilder.toString();
    }

    public List<String> createFileList() {
        List<String> list = new ArrayList<>();
        while (scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }
        return list;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public Scanner getScanner() {
        return this.scanner;
    }
}
