package com.leewake.operator;

import com.leewake.RPNException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class OperatorUtil {

    public static boolean isNumber(String number) {
        try {
            Double.valueOf(number);
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
    public static void baseCalculate(Stack<Double> numbers, String operator) throws RPNException {
        // 按照从左向右计算，则先弹出栈顶的数据是：被除数。
        double num2 = numbers.pop();
        double num1 = numbers.pop();

        switch (operator) {
            case "+":
                numbers.push(num1 + num2);
                break;
            case "-":
                numbers.push(num1 - num2);
                break;
            case "*":
                numbers.push(num1 * num2);
                break;
            case "/":
                if (num2 == 0) {
                    throw new RPNException("Dividend cannot be zero!");
                }
                numbers.push(num1 / num2);
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
    public static void sqrt(Stack<Double> numbers, String operator) throws RPNException {
        double num = numbers.pop();
        if (num < 0) {
            throw new RPNException("负数不能开平方!");
        }
        double sqrtNum = (double) Math.sqrt(num);
        numbers.push(sqrtNum);
    }

    /**
     * <B>Description:</B> 回退操作 <br>
     * <B>Create on:</B> 2020/3/20 上午11:38 <br>
     *
     * @author leewake
     */
    public static void undo(Stack<Double> numbers, Stack<List<Double>> logList, String operator) throws RPNException {

        // 将栈内数据清空
        //TODO 是否可以使用clear
        while (!numbers.isEmpty()) {
            numbers.pop();
        }

        // 将上一步的操作数据存入操作数栈中
        if (!logList.isEmpty()) {
            logList.pop();// 弹出计算结果的日志
            List<Double> numbersLog = logList.peek();// 获取计算前的栈数据
            for (Double d : numbersLog) {
                if (d != null) {
                    numbers.push(d);
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
    public static void clear(Stack<Double> numbers, Stack<List<Double>> logList, String operator) throws RPNException {
        // 清理栈里的数据
        while (!numbers.isEmpty()) {
            numbers.pop();
        }
        // 清理动作在日志栈里存入null，用于回退时区分
        List<Double> list = new ArrayList<>();
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


