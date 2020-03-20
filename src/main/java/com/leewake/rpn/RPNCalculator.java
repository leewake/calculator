package com.leewake.rpn;

import com.leewake.AuxiliaryUtil;
import com.leewake.operator.OperatorEnum;
import com.leewake.operator.OperatorUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @description: RPN计算器
 * @author: leewake
 * @create: 2020-03-19 11:04
 **/
public class RPNCalculator {

    // 保存当前的数据栈
    private Stack<BigDecimal> numberStack = new Stack<>();
    // 保存栈数据的操作日志
    private Stack<List<BigDecimal>> logList = new Stack<>();
    // 保存未进栈的数据
    private List<String> notPushStrList = new ArrayList<>();

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

        //用于非法参数后记录剩余的数字与操作符
        boolean exceptionFlag = false;

        for (int i = 0; i < inputLength; i++) {
            String token = input[i];
            if (exceptionFlag) {
                notPushStrList.add(token);
                continue;
            }
            // 待入栈的是数字,直接入数据栈,并记录操作日志
            if (OperatorUtil.isNumber(token)) {
                numberStack.push(new BigDecimal(token));
                AuxiliaryUtil.addLogList(numberStack, logList);
                continue;
            }
            // 待入栈的是操作符
            exceptionFlag = this.execIfOperator(token, i, exceptionFlag);
        }
        //打印计算结果及异常信息
        AuxiliaryUtil.printStack(numberStack);
        AuxiliaryUtil.printAfterMeetException(notPushStrList);
    }

    /**
     * <B>Description:</B> 如果待入栈的是操作符,处理逻辑 <br>
     * <B>Create on:</B> 2020/3/20 下午3:28 <br>
     *
     * @author leewake
     */
    protected boolean execIfOperator(String token, int index, boolean exceptionFlag) throws RPNException {
        //如果待入栈的是操作符
        OperatorEnum operatorEnum = OperatorEnum.getOperatorEnum(token);
        //封装操作符处理
        switch (operatorEnum) {
            case ADD:
            case SUB:
            case MUL:
            case DIV:
                if (numberStack.size() > 1) {
                    OperatorUtil.baseCalculate(numberStack, operatorEnum);
                    AuxiliaryUtil.addLogList(numberStack, logList);
                } else {
                    AuxiliaryUtil.printInsufficientParameters(token, index);
                    exceptionFlag = true;
                }
                break;
            case SQRT:
                if (numberStack.size() > 0) {
                    OperatorUtil.sqrt(numberStack);
                    AuxiliaryUtil.addLogList(numberStack, logList);
                } else {
                    AuxiliaryUtil.printInsufficientParameters(token, index);
                    exceptionFlag = true;
                }
                break;
            case UNDO:
                OperatorUtil.undo(numberStack, logList);
                break;
            case CLEAR:
                OperatorUtil.clear(numberStack, logList);
                break;
            default:
                throw new RPNException("input RPN expression has error!");
        }
        return exceptionFlag;
    }

}
