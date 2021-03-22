package hw1;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SubtractTest {
    protected Calculator calculator = new Calculator();

    @Test(dataProvider = "longData", dataProviderClass = DataProviderClass.class, groups = "AddSubtract")
    public void addLongTest(long a, long b) {
        Assert.assertEquals(calculator.sub(a,b), a - b);
    }

    @Test(dataProvider = "doubleData", dataProviderClass = DataProviderClass.class, groups = "AddSubtract")
    public void addDoubleTest(double a, double b) {
        Assert.assertEquals(calculator.sub(a,b), a - b);
    }
}
