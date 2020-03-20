package com.leewake.operator;

import com.leewake.rpn.RPNException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class OperatorUtil {

    /**
     * <B>Description:</B> 判断是不是数字 <br>
     *
     * @author leewake
     */
    public static boolean isNumber(String number) {
        try {
            new BigDecimal(number);
        } catch (Exception e) {
            // 会抛出NumberFormatException
            return false;
        }
        return true;
    }

    /**
     * <B>Description:</B> 加减乘除的基本计算 <br>
     *
     * @author leewake
     */
    public static void baseCalculate(Stack<BigDecimal> numbers, OperatorEnum operator) throws RPNException {
        // 按照从左向右计算，则先弹出栈顶的数据是:被除/减数。
        BigDecimal num2 = numbers.pop();
        BigDecimal num1 = numbers.pop();

        switch (operator) {
            case ADD:
                numbers.push(num1.add(num2));
                break;
            case SUB:
                numbers.push(num1.subtract(num2));
                break;
            case MUL:
                numbers.push(num1.multiply(num2));
                break;
            case DIV:
                if (BigDecimal.ZERO.equals(num2)) {
                    throw new RPNException("Dividend cannot be zero!");
                }
                numbers.push(num1.divide(num2));
                break;
            default:
                throw new RPNException("RPN expression has error!");
        }
    }

    /**
     * <B>Description:</B> 开方计算 <br>
     *
     * @author leewake
     */
    public static void sqrt(Stack<BigDecimal> numbers) throws RPNException {

        BigDecimal num = numbers.pop();
        if (num.compareTo(BigDecimal.ZERO) < 0) {
            throw new RPNException("Negative number cannot square!");
        }
        BigDecimal sqrtNum = sqrt(num);
        numbers.push(sqrtNum);
    }

    /**
     * <B>Description:</B> 牛顿下山法去开方 <br>
     * <B>Create on:</B> 2020/3/19 下午8:13 <br>
     *
     * @author leewake
     */
    public static BigDecimal sqrt(BigDecimal value) {
        BigDecimal num2 = BigDecimal.valueOf(2);
        int precision = 100;
        MathContext mc = new MathContext(precision, RoundingMode.HALF_EVEN);
        BigDecimal deviation = value;
        int cnt = 0;
        while (cnt < precision) {
            deviation = (deviation.add(value.divide(deviation, mc))).divide(num2, mc);
            cnt++;
        }
        return deviation;
    }

    /**
     * <B>Description:</B> 回退操作 <br>
     *
     * @author leewake
     */
    public static void undo(Stack<BigDecimal> numbers, Stack<List<BigDecimal>> logList) {
        // 清空栈中数据
        numbers.clear();
        // 将上一步的操作数据存入操作数栈中
        if (!logList.isEmpty()) {
            // 弹出计算结果的日志
            logList.pop();
            // 获取计算前的栈数据
            List<BigDecimal> numbersLog = logList.peek();
            for (BigDecimal num : numbersLog) {
                if (num != null) {
                    numbers.push(num);
                }
            }
        }
    }

    /**
     * <B>Description:</B> 清屏操作 <br>
     *
     * @author leewake
     */
    public static void clear(Stack<BigDecimal> numbers, Stack<List<BigDecimal>> logList) {
        // 清空栈中数据
        numbers.clear();
        // 当清屏操作时,日志栈里存入null,便于回退时区分
        List<BigDecimal> list = new ArrayList<>();
        list.add(null);
        logList.push(list);
    }

}


