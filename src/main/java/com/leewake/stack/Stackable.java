package com.leewake.stack;

import java.util.Stack;

public abstract class Stackable {

    Stack<Stackable> stack = null;

    public void pushOnto(Stack<Stackable> stack) {
        stack = stack;
        stack.push(this);
    }

    public abstract void execute();

    public abstract boolean isNumeric();

}
