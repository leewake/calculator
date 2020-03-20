package com.leewake;

import com.leewake.operator.OperatorEnum;
import com.leewake.operator.OperatorUtil;

import java.util.List;
import java.util.Stack;

/**
 * @description: 计算器
 * @author: leewake
 * @create: 2020-03-19 11:04
 **/
public class RPNCalculator {

    // 保存当前的数据栈
    private Stack<Double> numbers = new Stack<>();
    // 报错栈数据的操作日志
    private Stack<List<Double>> logList = new Stack<>();

    /**
     * <B>Description:</B> 计算逻辑入口 <br>
     * <B>Create on:</B> 2020/3/20 上午11:30 <br>
     *
     * @author leewake
     */
    public void calByRPN(String expression) throws RPNException {
        //空格分隔切分输入表达式
        String[] input = expression.split(" ");
        int inputLength = input.length;

        for (int i = 0; i < inputLength; i++) {
            String token = input[i];

            // 待入栈的是数字,直接入数据栈,并记录操作日志
            if (OperatorUtil.isNumber(token)) {
                numbers.push(Double.valueOf(token));
                AuxiliaryUtil.addLogList(numbers, logList);
                continue;
            }

            //如果待入栈的是操作符
            OperatorEnum operatorEnum = OperatorEnum.getOperatorEnum(token);

            //封装操作符处理
            switch (operatorEnum) {
                case ADD:
                case SUB:
                case MUL:
                case DIV:
                    if (numbers.size() > 1) {
                        OperatorUtil.baseCalculate(numbers, token);
                        AuxiliaryUtil.addLogList(numbers, logList);
                    } else {
                        AuxiliaryUtil.printInsufficientParameters(token, i);
                    }
                    break;
                case SQRT:
                    if (numbers.size() > 0) {
                        OperatorUtil.sqrt(numbers, token);
                        AuxiliaryUtil.addLogList(numbers, logList);
                    } else {
                        AuxiliaryUtil.printInsufficientParameters(token, i);
                    }
                    break;
                case UNDO:
                    OperatorUtil.undo(numbers, logList, token);
                    break;
                case CLEAR:
                    OperatorUtil.clear(numbers, logList, token);
                    break;
                default:
                    throw new RPNException("输入的RPN表达式错误!");
            }

            // 判断是加减乘除操作，则进行相应的计算，计算完成记录日志
           /* if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                if (numbers.size() > 1) {
                    OperatorUtil.baseCalculate(numbers, token);
                    AuxiliaryUtil.addLogList(numbers, logList);
                } else {
                    System.out.print("token " + token + " (position: " + (i * 2 + 1) + "): insufficient parameters ");// 判断如果操作数不足则退出循环，提示位置； （i*2+1）位置需要加上空格
                    break;
                }
            } else if ("sqrt".equals(token)) {// 判断如果是开平方，则进入
                if (numbers.size() > 0) {
                    OperatorUtil.sqrt(numbers, token);
                    AuxiliaryUtil.addLogList(numbers, logList);
                } else {
                    System.out.print("token " + token + " (position: " + (i * 2 + 1) + "): insufficient parameters ");
                    break;
                }
            } else if ("undo".equals(token)) {// 判断如果是回退，则进入
                OperatorUtil.undo(numbers, logList, token);
            } else if ("clear".equals(token)) {// 判断是清除，则进入
                OperatorUtil.clear(numbers, logList, token);
            } else {
                throw new RPNException("输入的RPN表达式错误!");
            }*/
        }

        AuxiliaryUtil.printStack(numbers);
    }

}
