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
