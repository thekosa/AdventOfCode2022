package com.adventOfCode.day10.OCR;

import java.util.*;

public class Letters extends Letter {
    private static final Map<Integer, String> lettersMap = new HashMap<>();
    private static final List<String> lettersList = new ArrayList<>();

    public Letters() {
        for (int i = 0; i < 26; i++) {
            lettersMap.put(i,  String.valueOf((char)(i + 65)));
        }
        lettersList.add(a);
        lettersList.add(b);
        lettersList.add(c);
        lettersList.add(d);
        lettersList.add(e);
        lettersList.add(f);
        lettersList.add(g);
        lettersList.add(h);
        lettersList.add(i);
        lettersList.add(j);
        lettersList.add(k);
        lettersList.add(l);
        lettersList.add(m);
        lettersList.add(n);
        lettersList.add(o);
        lettersList.add(p);
        lettersList.add(q);
        lettersList.add(r);
        lettersList.add(s);
        lettersList.add(t);
        lettersList.add(u);
        lettersList.add(v);
        lettersList.add(w);
        lettersList.add(x);
        lettersList.add(y);
        lettersList.add(z);
    }
    public String findLetter(String letterBeforeOCR){
        for (int i = 0; i<lettersList.size();i++){
            if(Objects.equals(lettersList.get(i),letterBeforeOCR)){
                return lettersMap.get(i);
            }
        }
        return "?";
    }
}

