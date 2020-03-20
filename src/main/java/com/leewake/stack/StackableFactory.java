package com.leewake.stack;

import com.leewake.operator.OperatorEnum;
import com.leewake.VariableHolder;

import java.math.BigDecimal;
import java.util.Stack;

public class StackableFactory {

    public static void create(VariableHolder variable) {
        String[] tokens = variable.getExpression().split(" ");
        Stack<BigDecimal> stack = variable.getStack();
//        create(tokens, stack);
    }

    public static void create(String[] tokens, Stack<Stackable> stack) {
        for (String token : tokens) {
            if (!OperatorEnum.isInclude(token)) { //push to stack if it is a number
                stack.push(new StackableNumber(token));
            } else {//pop numbers from stack if it is an Operator
                StackableOperator stackableOperator = new StackableOperator(OperatorEnum.getOperatorEnum(token));
                stack.push(stackableOperator);
            }
        }
    }

}
