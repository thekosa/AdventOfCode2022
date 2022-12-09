package com.adventOfCode.Main;

import com.adventOfCode.Modules.View;
import com.adventOfCode.day1.Calorie;
import com.adventOfCode.day2.RockPaperScissors;
import com.adventOfCode.day3.CrazyRucksacks;
import com.adventOfCode.day4.Camp;
import com.adventOfCode.day5.SillyStacks;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("\nAdvent of Code 2022 - ANSWERS \n");

        new View(new Calorie()).write();
        new View(new RockPaperScissors()).write();
        new View(new CrazyRucksacks()).write();
        new View(new Camp()).write();
        new View(new SillyStacks()).write();
    }
}
