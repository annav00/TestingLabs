package hw1;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DivideTest {
    protected Calculator calculator = new Calculator();

    @Test(dataProvider = "longData", dataProviderClass = DataProviderClass.class, groups = "DivideMultiply")
    public void divLongTest(long a, long b) {
        if (b == 0L || b == -0L) {
            try {
                calculator.div(a,b);
            }
            catch (NumberFormatException ex) {
            }
        }
        else {
            Assert.assertEquals(calculator.div(a,b), a / b);
        }
    }

    @Test(dataProvider = "doubleData", dataProviderClass = DataProviderClass.class, groups = "DivideMultiply")
    public void divDoubleTest(double a, double b) {
            Assert.assertEquals(calculator.div(a,b), a / b);
    }
}
