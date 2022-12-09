package com.adventOfCode.day4;

import com.adventOfCode.Modules.AoC;
import com.adventOfCode.Modules.Scan;

import java.io.FileNotFoundException;
import java.util.List;

public class Camp implements AoC {

    private final List<String> idNumbs;
    private int numbFullyContainPairs;
    private int numbOverlapPairs;

    public Camp() throws FileNotFoundException {
        idNumbs = new Scan(this.getClass()).createFileList();
        numbFullyContainPairs = 0;
    }

    private boolean areSectionsFullyContain(SectionsSet set1, SectionsSet set2) {
        return isFullyContain(set1, set2) || isFullyContain(set2, set1);
    }

    private boolean isFullyContain(SectionsSet set1, SectionsSet set2) {
        return set1.getFirstElement() >= set2.getFirstElement() && set1.getLastElement() <= set2.getLastElement();
    }

    private boolean isOverlap(SectionsSet set1, SectionsSet set2) {
        for(int section1 : set1.getSet()){
            for (int section2 : set2.getSet()){
                if (section1==section2){
                    return true;
                }
            }
        }
        return false;
    }

    private void operate() {
        for (String id : idNumbs) {
            SectionsSet sectionsSet1 = new SectionsSet(id.split(",")[0]);
            SectionsSet sectionsSet2 = new SectionsSet(id.split(",")[1]);
            if (areSectionsFullyContain(sectionsSet1, sectionsSet2)) {
                numbFullyContainPairs++;
                numbOverlapPairs++;
            } else if (isOverlap(sectionsSet1, sectionsSet2)) {
                numbOverlapPairs++;
            }
        }
    }

    private int getNumbFullyContainPairs() {
        return numbFullyContainPairs;
    }

    private int getNumbOverlapPairs() {
        return numbOverlapPairs;
    }

    @Override
    public void execute() {
        operate();
    }

    @Override
    public Object getResultPart1() {
        return getNumbFullyContainPairs();
    }

    @Override
    public Object getResultPart2() {
        return getNumbOverlapPairs();
    }
}
