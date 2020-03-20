package com.leewake.stack;

import java.math.BigDecimal;

public class StackableNumber extends Stackable {

    private BigDecimal value;

    public StackableNumber(String val) {
        this(new BigDecimal(val));
    }

    public StackableNumber(BigDecimal val) {
        value = val;
    }

    @Override
    public void execute() {
    }

    @Override
    public boolean isNumeric() {
        return true;
    }

}
