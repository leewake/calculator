package com.leewake.operator;

import com.leewake.rpn.RPNException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class OperatorUtil {

    public static boolean isNumber(String number) {
        try {
            new BigDecimal(number);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * <B>Description:</B> 加减乘除的基本计算 <br>
     * <B>Create on:</B> 2020/3/20 上午11:37 <br>
     *
     * @author leewake
     */
    public static void baseCalculate(Stack<BigDecimal> numbers, String operator) throws RPNException {
        // 按照从左向右计算，则先弹出栈顶的数据是：被除数。
        BigDecimal num2 = numbers.pop();
        BigDecimal num1 = numbers.pop();

        switch (operator) {
            case "+":
                numbers.push(num1.add(num2));
                break;
            case "-":
                numbers.push(num1.subtract(num2));
                break;
            case "*":
                numbers.push(num1.multiply(num2));
                break;
            case "/":
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
     * 进行开平方计算
     *
     * @param numbers
     * @param operator
     * @throws Exception
     */
    public static void sqrt(Stack<BigDecimal> numbers, String operator) throws RPNException {
        BigDecimal num = numbers.pop();
        if (num.compareTo(BigDecimal.ZERO) < 0) {
            throw new RPNException("负数不能开平方!");
        }
        BigDecimal sqrtNum = sqrt(num, 2);
        numbers.push(sqrtNum);
    }

    /**
     * <B>Description:</B> 回退操作 <br>
     * <B>Create on:</B> 2020/3/20 上午11:38 <br>
     *
     * @author leewake
     */
    public static void undo(Stack<BigDecimal> numbers, Stack<List<BigDecimal>> logList, String operator) throws RPNException {

        // 将栈内数据清空
        //TODO 是否可以使用clear
        while (!numbers.isEmpty()) {
            numbers.pop();
        }

        // 将上一步的操作数据存入操作数栈中
        if (!logList.isEmpty()) {
            logList.pop();// 弹出计算结果的日志
            List<BigDecimal> numbersLog = logList.peek();// 获取计算前的栈数据
            for (BigDecimal num : numbersLog) {
                if (num != null) {
                    numbers.push(num);
                }
            }
        }
    }

    /**
     * <B>Description:</B> 清屏操作 <br>
     * <B>Create on:</B> 2020/3/20 上午11:38 <br>
     *
     * @author leewake
     */
    public static void clear(Stack<BigDecimal> numbers, Stack<List<BigDecimal>> logList, String operator) throws RPNException {
        // 清理栈里的数据
        while (!numbers.isEmpty()) {
            numbers.pop();
        }
        // 清理动作在日志栈里存入null，用于回退时区分
        List<BigDecimal> list = new ArrayList<>();
        list.add(null);
        logList.push(list);
    }

    /**
     * <B>Description:</B> 牛顿下山法去开方 <br>
     * <B>Create on:</B> 2020/3/19 下午8:13 <br>
     *
     * @author leewake
     */
    public static BigDecimal sqrt(BigDecimal value, int scale) {
        BigDecimal num2 = BigDecimal.valueOf(2);
        int precision = 100;
        MathContext mc = new MathContext(precision, RoundingMode.HALF_UP);
        BigDecimal deviation = value;
        int cnt = 0;
        while (cnt < precision) {
            deviation = (deviation.add(value.divide(deviation, mc))).divide(num2, mc);
            cnt++;
        }
        deviation = deviation.setScale(scale, BigDecimal.ROUND_HALF_UP);
        return deviation;
    }

}


