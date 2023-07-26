package com.adventOfCode.day10.OCR;

import java.util.ArrayList;
import java.util.List;

public class OCR {
    public static String convert(String wordBeforeOCR) {
        List<String> lettersBeforeOCR = fromWord2Letters(wordBeforeOCR);
        StringBuilder convertedWord = new StringBuilder();
        for (String letter : lettersBeforeOCR) {
            Letters letters = new Letters();
            convertedWord.append(letters.findLetter(letter));
        }
        return convertedWord.toString();
    }

    public static String convert(String wordBeforeOCR, String lowSign, String highSign) {
        return convert(reSign(wordBeforeOCR, lowSign, highSign));
    }

    private static String reSign(String string, String lowSign, String highSign) {
        return string.replaceAll(lowSign, ".").replaceAll(highSign, "#");
    }

    private static List<String> fromWord2Letters(String word) {
        List<String> letters = new ArrayList<>();
        String[] rows = word.split("\n");
        int lettersQuantity = rows[0].length() / 5;

        for (int i = 0; i < lettersQuantity; i++) {
            StringBuilder letter = new StringBuilder();
            for (String row : rows) {
                letter.append(row, i * 5, i * 5 + 4).append("\n");
            }
            letter.delete(letter.length()-1,letter.length());
            letters.add(letter.toString());
        }
        return letters;
    }
}
