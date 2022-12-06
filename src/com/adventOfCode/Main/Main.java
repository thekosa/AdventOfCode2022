package com.adventOfCode.Main;

import com.adventOfCode.day1.Calorie;
import com.adventOfCode.day2.RockPaperScissors;
import com.adventOfCode.day3.CrazyRucksacks;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("\nAdvent of Code 2022 - ANSWERS \n");

        Calorie calorie= new Calorie();
        calorie.operate();
        System.out.println("Day 1: Calorie Counting:" +
                "\n\tpart 1: " + calorie.getResultPart1() +
                "\n\tpart 2: " + calorie.getResultPart2());

        RockPaperScissors rockPaperScissors = new RockPaperScissors();
        rockPaperScissors.operate();
        System.out.println("Day 2: Rock Paper Scissors:" +
                "\n\tpart 1: " + rockPaperScissors.getResultPart1() +
                "\n\tpart 2: " + rockPaperScissors.getResultPart2());

        CrazyRucksacks crazyRucksacks = new CrazyRucksacks();
        crazyRucksacks.operate();
        System.out.println("Day 3: Rucksack Reorganization:" +
                "\n\tpart 1: " + crazyRucksacks.getResultPart1() +
                "\n\tpart 2: " + crazyRucksacks.getResultPart2());

    }
}
