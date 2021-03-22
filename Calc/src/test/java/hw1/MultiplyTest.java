package hw1;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MultiplyTest {
    protected Calculator calculator = new Calculator();

    @Test(dataProvider = "longData", dataProviderClass = DataProviderClass.class, groups = "DivideMultiply")
    public void multLongTest(long a, long b) {
        Assert.assertEquals(calculator.mult(a,b), a * b);
    }

    @Test(dataProvider = "doubleData", dataProviderClass = DataProviderClass.class, groups = "DivideMultiply")
    public void multDoubleTest(double a, double b) {
        Assert.assertEquals(calculator.mult(a,b), a * b);
    }
}
