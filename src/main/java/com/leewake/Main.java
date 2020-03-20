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
        System.out.println("<--- Welcome to using rpn calculator --->");
        System.out.println("<--- When you type exit, the calculator will close --->");
        System.out.println("<--- Please enter an expression:  --->");

        RPNCalculator calculator = new RPNCalculator();
        Scanner scanner = new Scanner(System.in);
        try {
            while (scanner.hasNext()) {
                // 手动输入表达式
                String rpn = scanner.nextLine();
                if ("exit".equals(rpn)) {
                    scanner.close();
                    System.out.println("<--- rpn calculator has exited  --->");
                    return;
                }
//                System.out.println("Input expression: " + rpn);
                calculator.calByRPN(rpn);
            }
        } catch (RPNException e) {
            e.printStackTrace();
        }
    }

}
