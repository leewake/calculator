package com.leewake;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * @description: 输入参数的封装
 * @author: leewake
 * @create: 2020-03-19 16:51
 **/

public class VariableHolder {

    String expression;

    Stack<BigDecimal> stack;

    public VariableHolder(String expression) {
        this(expression, null);
    }

    public VariableHolder(String expression, Stack<BigDecimal> stack) {
        this.expression = expression;
        if (stack == null) {
            this.stack = new Stack<>();
        } else {
            this.stack = stack;
        }
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Stack<BigDecimal> getStack() {
        return stack;
    }

    public void setStack(Stack<BigDecimal> stack) {
        this.stack = stack;
    }
}
