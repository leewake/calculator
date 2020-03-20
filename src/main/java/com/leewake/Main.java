package com.leewake;

import com.leewake.rpn.RPNCalculator;
import com.leewake.rpn.RPNException;

import java.util.Scanner;

/**
 * <B>Description:</B> 项目入口 <br>
 * <B>Create on:</B> 2020/3/20 下午3:35 <br>
 *
 * @author leewake
 */
public class Main {

    public static void main(String args[]) {
        RPNCalculator calculator = new RPNCalculator();
        Scanner scanner = new Scanner(System.in);
        try {
            while (scanner.hasNext()) {
                // 手动输入表达式
//                System.out.println("Please enter an expression: ");
                String rpn = scanner.nextLine();
//                System.out.println("Input expression: " + rpn);
                calculator.calByRPN(rpn);
            }
        } catch (RPNException e) {
            e.printStackTrace();
        }
    }

}
