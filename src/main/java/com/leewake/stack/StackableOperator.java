package com.leewake.stack;

import com.leewake.operator.OperatorEnum;

public class StackableOperator extends Stackable {

    private OperatorEnum operatorEnum;

    public StackableOperator(OperatorEnum operatorEnum) {
        this.operatorEnum = operatorEnum;
    }

    @Override
    public void execute() {
    }

    @Override
    public boolean isNumeric() {
        return false;
    }

}
