package hw1;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddTest {
    protected Calculator calculator = new Calculator();

    @Test(dataProvider = "longData", dataProviderClass = DataProviderClass.class, groups = "AddSubtract")
    public void sumLongTest(long a, long b) {
        Assert.assertEquals(calculator.sum(a,b), a + b);
    }

    @Test(dataProvider = "doubleData", dataProviderClass = DataProviderClass.class, groups = "AddSubtract")
    public void sumDoubleTest(double a, double b) {
        Assert.assertEquals(calculator.sum(a,b), a + b);
    }
}