import org.junit.Test;

/**
 * @description:
 * @author: leewake
 * @create: 2020-03-19 11:05
 **/

public class CaculateTest {
    @Test
    public void plusTest(){

        Caculate caculate = new Caculate();
        System.out.println(caculate.plus(3,2));
    }

    @Test
    public void minusTest(){

        Caculate caculate = new Caculate();
        System.out.println(caculate.minus(3,2));
    }
}
