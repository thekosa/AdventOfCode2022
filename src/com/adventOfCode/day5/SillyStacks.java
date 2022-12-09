package com.adventOfCode.day5;

import com.adventOfCode.Modules.AoC;
import com.adventOfCode.Modules.Scan;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class SillyStacks implements AoC {

    private final List<String> moves;
    private Stacks<String> sillyStacks;
    private List<String> wholeDoc;
    private String message;
    private String messageByCrateMover9001;
    private int numbersRowIndex;

    public SillyStacks() throws FileNotFoundException {
        moves = new ArrayList<>();
        message = "";
        messageByCrateMover9001 = "";
        splitWholeDoc();
    }

    private void operate() {
        stacking();
        moving();
        stacking();
        movingByCrateMover9001();
    }

    private void splitWholeDoc() throws FileNotFoundException {
        wholeDoc = new Scan(this.getClass()).createFileList();
        numbersRowIndex = 0;
        for (int i = 0; i < wholeDoc.size(); i++) {
            String string = wholeDoc.get(i);
            if (string.startsWith("move")) {
                moves.add(string);
            } else if (string.startsWith(" 1 ")) {
                numbersRowIndex = i;
            }
        }
    }

    private void stacking() {
        String[] quantityRow = wholeDoc.get(numbersRowIndex).split(" ");
        sillyStacks = new Stacks<>(Integer.parseInt(quantityRow[quantityRow.length - 1]));

        for (int i = 1; i <= numbersRowIndex; i++) {
            String row = wholeDoc.get(numbersRowIndex - i);
            for (int j = 0, k = 3, l = 0; l < sillyStacks.getQuantity(); l++, j += 4, k += 4) {
                String supply = row.substring(j, k).substring(1, 2);
                if (!supply.contains(" ")) {
                    sillyStacks.push(l, supply);
                }
            }
        }
    }

    private void moving() {
        for (String moveRow : moves) {
            int[] decrypted = decryptMove(moveRow);
            sillyStacks.amountMove(decrypted[0], decrypted[1], decrypted[2]);
        }
        buildNormalMessage();
    }

    private void movingByCrateMover9001() {
        for (String moveRow : moves) {
            int[] decrypted = decryptMove(moveRow);
            sillyStacks.amountMoveByCrateMover9001(decrypted[0], decrypted[1], decrypted[2]);
        }
        buildCrateMover9001Message();
    }

    private int[] decryptMove(String string) {
        String[] tempString = string.split(" ");
        int[] decrypted = new int[3];
        decrypted[0] = Integer.parseInt(tempString[1]);
        decrypted[1] = Integer.parseInt(tempString[3]);
        decrypted[2] = Integer.parseInt(tempString[5]);
        decrypted[1]--;
        decrypted[2]--;
        return decrypted;
    }

    private void buildCrateMover9001Message() {
        messageByCrateMover9001 = buildMessage();
    }

    private void buildNormalMessage() {
        message = buildMessage();
    }

    private String buildMessage() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Stack<String> stack : sillyStacks.getStacks()) {
            stringBuilder.append(stack.pop());
        }
        return stringBuilder.toString();
    }

    private String getMessage() {
        return message;
    }

    private String getMessageByCrateMover9001(){
        return messageByCrateMover9001;
    }

    @Override
    public Object getResultPart1() {
        return getMessage();
    }

    @Override
    public Object getResultPart2() {
        return getMessageByCrateMover9001();
    }

    @Override
    public void execute() {
        operate();
    }
}
