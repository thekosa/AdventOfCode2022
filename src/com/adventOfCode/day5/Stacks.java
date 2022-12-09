package com.adventOfCode.day5;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Stacks<T> {
    private final List<Stack<T>> stacks;
    private int quantity;

    public Stacks() {
        stacks = new ArrayList<>();
        quantity = 0;
    }

    public Stacks(int quantity) {
        this.quantity = quantity;
        stacks = new ArrayList<>();
        for (int i = 0; i < this.quantity; i++) {
            stacks.add(new Stack<>());
        }
    }

    public void amountMoveByCrateMover9001(int amount, int source, int target) {
        Stack<T> assistStack = new Stack<>();
        for (int i = 0; i < amount; i++) {
            assistStack.push(pop(source));
        }
        for (int i = 0; i < amount; i++) {
            push(target,assistStack.pop());
        }
    }

    public void amountMove(int amount, int source, int target) {
        for (int i = 0; i < amount; i++) {
            move(source, target);
        }
    }

    public void move(int source, int target) {
        push(target, pop(source));
    }

    public void push(int stackNumb, T t) {
        stacks.get(stackNumb).push(t);
    }

    public T pop(int stackNumb) {
        return stacks.get(stackNumb).pop();
    }

    public boolean isEmpty(int stackNumb) {
        return stacks.get(stackNumb).empty();
    }

    public int getQuantity() {
        return quantity;
    }

    public Stack<T> getStack(int stackNumb) {
        return stacks.get(stackNumb);
    }

    public List<Stack<T>> getStacks() {
        return stacks;
    }

    public void addNewStack() {
        stacks.add(new Stack<>());
        quantity++;
    }

    public void addStack(Stack<T> stack) {
        stacks.add(stack);
        quantity++;
    }

    public void deleteStack(int stackNumb) {
        stacks.remove(stackNumb);
        quantity--;
    }
}
