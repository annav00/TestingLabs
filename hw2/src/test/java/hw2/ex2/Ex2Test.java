package hw2.ex2;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

import java.util.List;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Ex2Test {
    private WebDriver driver;

    @BeforeClass
    public void initWebDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void openPageTest(){
        //1. Open test site by URL
        driver.navigate().to("https://jdi-testing.github.io/jdi-light/index.html");

        //2. Assert Browser title
        Assert.assertEquals(driver.getTitle(), "Home Page");
    }

    @Test (dependsOnMethods="openPageTest")
    public void loginTest(){
        SoftAssert softAssert = new SoftAssert();

        //3. Perform login
        driver.findElement((By.cssSelector("li.dropdown.uui-profile-menu"))).click();
        driver.findElement(By.id("name")).sendKeys("Roman");
        driver.findElement(By.id("password")).sendKeys("Jdi1234");
        driver.findElement(By.id("login-button")).click();

        //4. Assert User name in the left-top side of screen that user is loggined
        WebElement userName = driver.findElement(By.id("user-name"));

        softAssert.assertTrue(userName.isDisplayed());
        softAssert.assertEquals(userName.getText(), "ROMAN IOVLEV");
        softAssert.assertAll();
    }

    @Test (dependsOnMethods="loginTest")
    public void serviceHeaderTest(){
        SoftAssert softAssert = new SoftAssert();

        //5. Click on "Service" subcategory in the header and
        // check that drop down contains options
        WebElement service = driver.findElement(By.className("dropdown"));
        service.click();
        List <WebElement> serviceElements = service.findElements(By.tagName("li"));
        Assert.assertFalse(serviceElements.isEmpty());

        final String[] elements = { "SUPPORT", "DATES", "SEARCH", "COMPLEX TABLE",
                "SIMPLE TABLE", "USER TABLE", "TABLE WITH PAGES", "DIFFERENT ELEMENTS", "PERFORMANCE"};

        for(int i = 0; i < elements.length; i++)
        {
            softAssert.assertTrue(serviceElements.get(i).isDisplayed());
            softAssert.assertEquals(serviceElements.get(i).getText(), elements[i]);
        }
        softAssert.assertAll();
    }

    @Test (dependsOnMethods="loginTest")
    public void serviceLeftTest(){
        SoftAssert softAssert = new SoftAssert();

        //6. Click on Service subcategory in the left section and
        // check that drop down contains options
        WebElement service = driver.findElement(By.className("menu-title"));
        service.click();
        List <WebElement> serviceElements = service.findElements(By.tagName("li"));
        Assert.assertFalse(serviceElements.isEmpty());

        final String[] elements = { "Support", "Dates", "Complex Table", "Simple Table",
                "Search",  "User Table", "Table with pages", "Different elements", "Performance"};

        for(int i = 0; i < elements.length; i++)
        {
            softAssert.assertTrue(serviceElements.get(i).isDisplayed());
            softAssert.assertEquals(serviceElements.get(i).getText(), elements[i]);
        }
        softAssert.assertAll();
    }

    @Test (dependsOnMethods="serviceLeftTest")
    public void servicetDifferentTest() {
        //7. Open through the header menu Service -> Different Elements Page
        WebElement dropDown = driver.findElement(By.className("dropdown-toggle"));
        dropDown.click();
        dropDown.findElement(By.xpath("//li[8]")).click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://jdi-testing.github.io/jdi-light/different-elements.html");
    }

    @Test (dependsOnMethods="servicetDifferentTest")
    public void differentElementsPageTest(){
        SoftAssert softAssert = new SoftAssert();
        //8. Check interface on Different elements page, it contains all needed elements
        List <WebElement> checkbox = driver.findElements(By.cssSelector("input[type='checkbox']"));
        List <WebElement> radio = driver.findElements(By.cssSelector("input[type='radio']"));
        List <WebElement> dropdown = driver.findElements(By.cssSelector("select.uui-form-element"));
        List <WebElement> buttons = driver.findElements(By.xpath("//*[@class='uui-button']"));

        softAssert.assertEquals(checkbox.size(), 4);
        softAssert.assertEquals(radio.size(), 4);
        softAssert.assertEquals(dropdown.size(), 1);
        softAssert.assertEquals(buttons.size(), 2);
        softAssert.assertAll();
    }

    @Test (dependsOnMethods="differentElementsPageTest")
    public void sectionTest(){
        SoftAssert softAssert = new SoftAssert();

        //9. Assert that there is Right Section
        WebElement rightSection = driver.findElement(By.cssSelector("#mCSB_2"));
        softAssert.assertTrue(rightSection.isDisplayed());

        //10. Assert that there is Left Section
        WebElement  leftSection = driver.findElement(By.cssSelector("#mCSB_1"));
        softAssert.assertTrue(leftSection.isDisplayed());

        softAssert.assertAll();
    }

    @Test (dependsOnMethods="differentElementsPageTest")
    public void checkboxesTest(){
        SoftAssert softAssert = new SoftAssert();

        //11. Select checkboxes
        List <WebElement> checkbox = driver.findElements(By.cssSelector("input[type='checkbox']"));
        checkbox.get(0).click();
        checkbox.get(2).click();
        softAssert.assertTrue(checkbox.get(0).isSelected());
        softAssert.assertTrue(checkbox.get(2).isSelected());

        //12. Assert that for each checkbox there is an individual log row and
        // value is corresponded to the status of checkbox.
        List<WebElement> log = driver.findElements(By.cssSelector("ul.panel-body-list.logs li"));
        Assert.assertFalse(log.isEmpty());
        final String[] logStr = {"Wind: condition changed to true",
                "Water: condition changed to true"};
        for(int i = 0; i < 2; i++)
        {
            Assert.assertTrue(log.get(i).isDisplayed());
            Assert.assertTrue(log.get(i).getText().contains(logStr[i]));
        }

        softAssert.assertAll();
    }

    @Test (dependsOnMethods="checkboxesTest")
    public void radioTest(){
        SoftAssert softAssert = new SoftAssert();

        //13. Select radio
        List <WebElement> radio = driver.findElements(By.cssSelector("input[type='radio']"));
        radio.get(3).click();
        softAssert.assertTrue(radio.get(3).isSelected());

        //14. Assert that for radiobutton there is a log row and
        // value is corresponded to the status of radiobutton.

        List<WebElement> log = driver.findElements(By.cssSelector("ul.panel-body-list.logs li"));
        Assert.assertFalse(log.isEmpty());
        final String logStr = "metal: value changed to Selen";
        WebElement selenLog = log.get(0);
        softAssert.assertTrue(selenLog.isDisplayed());
        softAssert.assertTrue(selenLog.getText().contains(logStr));

        softAssert.assertAll();
    }

    @Test (dependsOnMethods="radioTest")
    public void dropdownTest(){
        SoftAssert softAssert = new SoftAssert();

        //15. Select in dropdown
        List <WebElement> dropdown = driver.findElements(By.cssSelector("select.uui-form-element>option"));
        dropdown.get(3).click();
        softAssert.assertTrue(dropdown.get(3).isSelected());

        //16. Assert that for dropdown there is a log row and
        // value is corresponded to the selected value.

        List<WebElement> log = driver.findElements(By.cssSelector("ul.panel-body-list.logs li"));
        Assert.assertFalse(log.isEmpty());
        final String logStr = "Colors: value changed to Yellow";
        WebElement yellowLog = log.get(0);
        softAssert.assertTrue(yellowLog.isDisplayed());
        softAssert.assertTrue(yellowLog.getText().contains(logStr));

        softAssert.assertAll();
    }

    @Test (dependsOnMethods="dropdownTest")
    public void checkboxes2Test(){
        SoftAssert softAssert = new SoftAssert();

        //17. Unselect and assert checkboxes
        List <WebElement> checkbox = driver.findElements(By.cssSelector("input[type='checkbox']"));
        checkbox.get(0).click();
        checkbox.get(2).click();
        softAssert.assertFalse(checkbox.get(0).isSelected());
        softAssert.assertFalse(checkbox.get(2).isSelected());

        //18. Assert that for each checkbox there is an individual log row and
        // value is corresponded to the status of checkbox.
        List<WebElement> log = driver.findElements(By.cssSelector("ul.panel-body-list.logs li"));
        Assert.assertFalse(log.isEmpty());
        final String[] logStr = {"Wind: condition changed to false",
                "Water: condition changed to false"};
        for(int i = 0; i < 2; i++)
        {
            softAssert.assertTrue(log.get(i).isDisplayed());
            softAssert.assertTrue(log.get(i).getText().contains(logStr[i]));
        }

        softAssert.assertAll();
    }

    @AfterClass
    public void closeSession(){
        driver.quit();
    }
}

