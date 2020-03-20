package com.leewake;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AuxiliaryUtil {

    /**
     * <B>Description:</B> 记录操作日志,便于回滚 <br>
     * <B>Create on:</B> 2020/3/19 20:14 <br>
     *
     * @author leewake
     */
    public static void addLogList(Stack<Double> numbers, Stack<List<Double>> logList) {
        List<Double> numbersList = new ArrayList<>();
        for (Double d : numbers) {
            numbersList.add(d);
        }
        logList.push(numbersList);
    }

    /**
     * <B>Description:</B> 打印栈中信息 <br>
     * <B>Create on:</B> 2020/3/20 上午10:15 <br>
     *
     * @author leewake
     */
    public static  void printStack(Stack<Double> numbers) {
        System.out.print("stack: ");
        if(!numbers.isEmpty()) {
            for(double d : numbers) {
                System.out.print(numberFormat(d) + " ");
            }
        }
        System.out.println();
    }

    public static  void printInsufficientParameters(String token, int index) {
        StringBuilder stringBuilder = new StringBuilder("token ");
        stringBuilder.append(token).append(" (position: ").append(index * 2 + 1).append("): insufficient parameters ");
        System.out.println(stringBuilder.toString());
//        System.out.print("token " + token + " (position: " + (index * 2 + 1) + "): insufficient parameters ");

    }

    /**
     * <B>Description:</B> 格式化精度 <br>
     *     精度至少为15位小数,显示10位小数
     * <B>Create on:</B> 2020/3/20 上午10:16 <br>
     *
     * @author leewake
     */
    public static String numberFormat(double number) {
        DecimalFormat numFormat = new DecimalFormat("##########.##########");
        numFormat.setRoundingMode(RoundingMode.DOWN);// 舍去末尾
        String output = numFormat.format(number);
        return output;
    }

}


