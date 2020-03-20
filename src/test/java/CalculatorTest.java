import com.leewake.rpn.RPNCalculator;
import org.junit.Test;

/**
 * @description:
 * @author: leewake
 * @create: 2020-03-19 11:05
 **/

public class CalculatorTest {

    /*@Test
    public void exampleTest() {
        System.out.println("All Example");
        String[] testExpression = new String[]{"5 2", "2 sqrt", "clear 9 sqrt", "5 2 -", "3 -", "clear", "5 4 3 2",
                "undo undo *", "5 *", "undo", "7 12 2 /", "*", "4 /", "1 2 3 4 5", "*", "clear 3 4 -", "1 2 3 4 5", "* * * *",
                "clear 1 2 3 * 5 + * * 6 5"};
        commonForTest(testExpression);
    }*/

    @Test
    public void exampleOneTest() {
        System.out.println("Example 1");
        String[] testExpression = new String[]{"5 2"};
        commonForTest(testExpression);
    }

    @Test
    public void exampleTwoTest() {
        System.out.println("Example 2");
        String[] testExpression = new String[]{"2 sqrt", "clear 9 sqrt"};
        commonForTest(testExpression);
    }

    @Test
    public void exampleThreeTest() {
        System.out.println("Example 3");
        String[] testExpression = new String[]{"5 2 -", "3 -", "clear"};
        commonForTest(testExpression);
    }

    @Test
    public void exampleFourTest() {
        System.out.println("Example 4");
        String[] testExpression = new String[]{"5 4 3 2",
                "undo undo *", "5 *", "undo"};
        commonForTest(testExpression);
    }

    @Test
    public void exampleFiveTest() {
        System.out.println("Example 5");
        String[] testExpression = new String[]{"7 12 2 /", "*", "4 /"};
        commonForTest(testExpression);
    }

    @Test
    public void exampleSixTest() {
        System.out.println("Example 6");
        String[] testExpression = new String[]{"1 2 3 4 5", "*", "clear 3 4 -"};
        commonForTest(testExpression);
    }

    @Test
    public void exampleSevenTest() {
        System.out.println("Example 7");
        String[] testExpression = new String[]{"1 2 3 4 5", "* * * *"};
        commonForTest(testExpression);
    }

    @Test
    public void exampleEightTest() {
        System.out.println("Example 8");
        String[] testExpression = new String[]{"1 2 3 * 5 + * * 6 5"};
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
//                System.out.println("Input expression: ");
                System.out.println(expression);
                calculator.calByRPN(expression);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
