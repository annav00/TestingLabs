package hw1;

import org.testng.annotations.DataProvider;

public abstract class DataProviderClass {

    @DataProvider(name = "longData")
    public static Object[][] createLongData() {
        return new Object[][]{
                {0L, 0L},
                {0L, 7l},
                {6L, 0l},
                {23L, 22L},
                {-23L, -22L},
                {-23L, 22L},
                {Long.MAX_VALUE, 0L},
                {Long.MAX_VALUE, 2L},
                {Long.MIN_VALUE, 0L},
                {Long.MIN_VALUE, -2L},
        };
    }

    @DataProvider(name = "doubleData")
    public static Object[][] createDoubleData() {
        return new Object[][]{
                {0.0, 0.0},
                {0.0, 7.0},
                {6.0, 0.0},
                {23.1, 22.1},
                {-23.1, -22.1},
                {-23.1, 22.1},
                {Double.MAX_VALUE, 0.0},
                {Double.MAX_VALUE, 2.0},
                {Double.MIN_VALUE, 0.0},
                {Double.MIN_VALUE, -2.0},
        };
    }
}