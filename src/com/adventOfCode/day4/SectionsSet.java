/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adventOfCode.day4;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MKosieradzki
 */
public class SectionsSet {

    private final int firstElement;
    private final int lastElement;
    private List<Integer> set;

    public SectionsSet(int firstElement, int lastElement) {
        this.firstElement = firstElement;
        this.lastElement = lastElement;
        setSet();
    }

    public SectionsSet(String encrypted) {
        firstElement = Integer.parseInt(encrypted.split("-")[0]);
        lastElement = Integer.parseInt(encrypted.split("-")[1]);
        setSet();
    }

    private void setSet() {
        set = new ArrayList<>();
        for (int i = firstElement; i <= lastElement; i++) {
            set.add(i);
        }
    }

    public int getFirstElement() {
        return firstElement;
    }

    public int getLastElement() {
        return lastElement;
    }

    public List<Integer> getSet() {
        return set;
    }
}
