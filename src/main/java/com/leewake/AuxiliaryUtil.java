package com.leewake;

import cn.hutool.core.collection.CollectionUtil;

import java.math.BigDecimal;
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
    public static void addLogList(Stack<BigDecimal> numbers, Stack<List<BigDecimal>> logList) {
        List<BigDecimal> numbersList = new ArrayList<>();
        for (BigDecimal num : numbers) {
            numbersList.add(num);
        }
        logList.push(numbersList);
    }

    /**
     * <B>Description:</B> 打印栈中信息 <br>
     * <B>Create on:</B> 2020/3/19 19:00 <br>
     *
     * @author leewake
     */
    public static  void printStack(Stack<BigDecimal> numbers) {
        System.out.print("stack: ");
        if(!numbers.isEmpty()) {
            for(BigDecimal num : numbers) {
                System.out.print(numberFormat(num) + " ");
            }
        }
        System.out.println();
    }

    /**
     * <B>Description:</B> 打印非参数 <br>
     * <B>Create on:</B> 2020/3/19 19:16 <br>
     *
     * @author leewake
     */
    public static  void printInsufficientParameters(String token, int index) {
        StringBuilder stringBuilder = new StringBuilder("operator ");
        stringBuilder.append(token).append(" (position: ").append(index * 2 + 1).append("): insufficient parameters ");
        System.out.println(stringBuilder.toString());

    }

    /**
     * <B>Description:</B> 打印非参数后异常情况 <br>
     * <B>Create on:</B> 2020/3/20 1:07 <br>
     *
     * @author leewake
     */
    public static  void printAfterMeetException(List<String> strList) {
        if (CollectionUtil.isEmpty(strList))
            return;

        int size = strList.size();
        StringBuilder stringBuilder = new StringBuilder("(the ");
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), " and ").append(strList.get(i));
            } else {
                stringBuilder.append(strList.get(i)).append(", ");
            }
        }
        stringBuilder.append(" were not pushed on to the stack due to the previous error)");
        System.out.println(stringBuilder.toString());
    }

    /**
     * <B>Description:</B> 格式化精度 <br>
     *     精度至少为15位小数,显示10位小数
     * <B>Create on:</B> 2020/3/20 1:22 <br>
     *
     * @author leewake
     */
    public static String numberFormat(BigDecimal number) {
        DecimalFormat numFormat = new DecimalFormat("##########.##########");
        numFormat.setRoundingMode(RoundingMode.DOWN);// 舍去末尾
        String output = numFormat.format(number);
        return output;
    }

}


