import com.leewake.rpn.RPNCalculator;
import org.junit.Test;

/**
 * @description:
 * @author: leewake
 * @create: 2020-03-19 11:05
 **/

public class CalculatorTest {

    @Test
    public void exampleOneTest(){
        String[] testExpression = new String[] { "5 2", "2 sqrt", "clear 9 sqrt", "5 2 -", "-", "clear", "5 4 3 2",
                "undo undo *", "5 *", "undo", "7 12 2 /", "*", "4 /", "1 2 3 4 5 *", "clear 3 4 -", "1 2 3 4 5", "* * * *",
                "clear 1 2 3 * 5 + * * 6 5" };
        commonForTest(testExpression);
    }

    @Test
    public void exampleEightTest(){
        String[] testExpression = new String[] {"1 2 3 * 5 + * * 6 5" };
        commonForTest(testExpression);
    }

    /**
     * <B>Description:</B> 测试公共部分 <br>
     * <B>Create on:</B> 2020/3/20 下午3:07 <br>
     *
     * @author leewake
     */
    private void commonForTest(String[] testExpression) {
        RPNCalculator calculator = new RPNCalculator();
        try {
            for (String expression : testExpression) {
                System.out.println("Input expression: ");
                System.out.println(expression);
                calculator.calByRPN(expression);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
