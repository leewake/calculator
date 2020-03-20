package com.leewake;

import com.leewake.rpn.RPNCalculator;
import com.leewake.rpn.RPNException;

import java.util.Scanner;

/**
 * @description: 计算器
 * @author: leewake
 * @create: 2020-03-19 11:04
 **/

public class Main {

    // 批测试用的表达式
    static String[] testExpression = new String[] { "5 2", "2 sqrt", "clear 9 sqrt", "5 2 -", "-", "clear", "5 4 3 2",
            "undo undo *", "5 *", "undo", "7 12 2 /", "*", "4 /", "1 2 3 4 5 *", "clear 3 4 -", "1 2 3 4 5", "* * * *",
            "1 2 3 * 5 + * * 6 5" };

    public static void main(String args[]) {
        RPNCalculator cl = new RPNCalculator();
        boolean flag = true;// 批测开关
        try {
            while (true) {
                // 使用批量表达式测试
                if (flag) {
                    for (String d : testExpression) {
                        System.out.println("Input expression: " + d);
                        cl.calByRPN(d);
                    }
                    flag = false;
                }
                // 手动输入表达式测试
                System.out.println("Please enter an expression: ");
                Scanner scanner = new Scanner(System.in);
                String rpn = scanner.nextLine();
                System.out.println("Input expression: " + rpn);
                cl.calByRPN(rpn);
            }

        } catch (RPNException e) {
            e.printStackTrace();
        }
    }
}
