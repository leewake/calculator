import com.leewake.rpn.RPNCalculator;
import org.junit.Test;

/**
 * @description:
 * @author: leewake
 * @create: 2020-03-19 11:05
 **/

public class CalculatorTest {

    @Test
    public void ExampleOneTest(){
        RPNCalculator calculator = new RPNCalculator();
        String[] testExpression = new String[] { "5 2", "2 sqrt", "clear 9 sqrt", "5 2 -", "-", "clear", "5 4 3 2",
                "undo undo *", "5 *", "undo", "7 12 2 /", "*", "4 /", "1 2 3 4 5 *", "clear 3 4 -", "1 2 3 4 5", "* * * *",
                "clear 1 2 3 * 5 + * * 6 5" };
        // 使用批量表达式测试
        try {
            for (String expression : testExpression) {
                System.out.println("Input expression: " + expression);
                calculator.calByRPN(expression);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
