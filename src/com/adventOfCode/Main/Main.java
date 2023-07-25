package com.adventOfCode.Main;

import com.adventOfCode.Modules.View;
import com.adventOfCode.day1.Calorie;
import com.adventOfCode.day10.CPU;
import com.adventOfCode.day2.RockPaperScissors;
import com.adventOfCode.day3.CrazyRucksacks;
import com.adventOfCode.day4.Camp;
import com.adventOfCode.day5.SillyStacks;
import com.adventOfCode.day6.Tuning;
import com.adventOfCode.day7.ElvesFileSystem;
import com.adventOfCode.day8.TallTreesForest;
import com.adventOfCode.day9.RopeBridge;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("\nAdvent of Code 2022 - ANSWERS \n");

        new View(new Calorie()).write();
        new View(new RockPaperScissors()).write();
        new View(new CrazyRucksacks()).write();
        new View(new Camp()).write();
        new View(new SillyStacks()).write();
        new View(new Tuning()).write();
        new View(new ElvesFileSystem()).write();
        new View(new TallTreesForest()).write();
        new View(new RopeBridge()).write();
        new View(new CPU()).write();
    }
}
