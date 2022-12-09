package com.adventOfCode.Modules;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class View {
    private final AoC aoc;
    private String title;
    private final String dayNumb;

    public View(AoC aoc) throws IOException {
        this.aoc = aoc;
        this.dayNumb = aoc.getClass().getPackageName().split("[.]")[2].substring(3);
        setTitle();
    }

    public void write() {
        aoc.execute();
        System.out.println(title +
                "\n\tpart 1: " + aoc.getResultPart1() +
                "\n\tpart 2: " + aoc.getResultPart2());
    }

    private void setTitle() throws IOException {
        Document document = Jsoup.connect("https://adventofcode.com/2022/day/" + dayNumb).get();
        String unpolishedTitle = document.select("article.day-desc > h2").toString();
        title = unpolishedTitle.substring(8, unpolishedTitle.length() - 9);
    }

    public String getDayNumb() {
        return dayNumb;
    }

    public AoC getAoc() {
        return aoc;
    }
}
