package com.leewake.rpn;

import com.leewake.AuxiliaryUtil;
import com.leewake.operator.OperatorEnum;
import com.leewake.operator.OperatorUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.Stack;

/**
 * @description: 计算器
 * @author: leewake
 * @create: 2020-03-19 11:04
 **/
public class RPNCalculator {

    // 保存当前的数据栈
    private Stack<BigDecimal> numberStack = new Stack<>();
    // 报错栈数据的操作日志
    private Stack<List<BigDecimal>> logList = new Stack<>();

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
                numberStack.push(new BigDecimal(token));
                AuxiliaryUtil.addLogList(numberStack, logList);
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
                    if (numberStack.size() > 1) {
                        OperatorUtil.baseCalculate(numberStack, token);
                        AuxiliaryUtil.addLogList(numberStack, logList);
                    } else {
                        AuxiliaryUtil.printInsufficientParameters(token, i);
                    }
                    break;
                case SQRT:
                    if (numberStack.size() > 0) {
                        OperatorUtil.sqrt(numberStack, token);
                        AuxiliaryUtil.addLogList(numberStack, logList);
                    } else {
                        AuxiliaryUtil.printInsufficientParameters(token, i);
                    }
                    break;
                case UNDO:
                    OperatorUtil.undo(numberStack, logList, token);
                    break;
                case CLEAR:
                    OperatorUtil.clear(numberStack, logList, token);
                    break;
                default:
                    throw new RPNException("输入的RPN表达式错误!");
            }
        }

        AuxiliaryUtil.printStack(numberStack);
    }

}
