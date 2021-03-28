package hw2.ex1;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

import java.util.List;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.asserts.SoftAssert;

public class Ex1Test {
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
        //3. Perform login
        driver.findElement((By.cssSelector("li.dropdown.uui-profile-menu"))).click();
        driver.findElement(By.id("name")).sendKeys("Roman");
        driver.findElement(By.id("password")).sendKeys("Jdi1234");
        driver.findElement(By.id("login-button")).click();

        //4. Assert User name in the left-top side of screen that user is loggined
        WebElement userName = driver.findElement(By.id("user-name"));
        Assert.assertTrue(userName.isDisplayed());
        Assert.assertEquals(userName.getText(), "ROMAN IOVLEV");
    }

    @Test (dependsOnMethods="loginTest")
    public void titleNameTest(){
        //5. Assert Browser title
        Assert.assertEquals(driver.getTitle(), "Home Page");
    }

    @Test (dependsOnMethods="loginTest")
    public void headerTest(){
        //6. Assert that there are 4 items on the header section are
        // displayed and they have proper texts
        List<WebElement> elements= driver.findElements(By.xpath("//header/div/nav/ul[1]/li/a"));
        Assert.assertEquals(elements.size(), 4);

        final String[] titleNames = {"HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS"};
        for (int i = 0; i < 4; i++) {
            Assert.assertTrue(elements.get(i).isDisplayed());
            Assert.assertEquals(elements.get(i).getText(), titleNames[i]);
        }
    }

    @Test (dependsOnMethods="loginTest")
    public void indexPageImagesTest(){
        //7. Assert that there are 4 images on the Index Page and
        // they are displayed
        List<WebElement> elements= driver.findElements(By.className("benefit-icon"));
        Assert.assertEquals(elements.size(), 4);

        for (int i = 0; i < 4; i++) {
            Assert.assertTrue(elements.get(i).isDisplayed());
        }
    }

    @Test (dependsOnMethods="loginTest")
    public void indexPageTextTest(){
        //8. Assert that there are 4 texts on the Index Page under icons and
        // they have proper text
        List<WebElement> elements= driver.findElements(By.className("benefit-txt"));
        Assert.assertEquals(elements.size(), 4);

        final String[] expectedText = {
                "To include good practices\n" +
                        "and ideas from successful\n" +
                        "EPAM project",
                "To be flexible and\n" +
                        "customizable",
                "To be multiplatform",
                "Already have good base\n" +
                        "(about 20 internal and\n" +
                        "some external projects),\n" +
                        "wish to get more…"
        };
        for (int i = 0; i < 4; i++) {
            Assert.assertTrue(elements.get(i).isDisplayed());
            Assert.assertEquals(elements.get(i).getText(), expectedText[i]);
        }
    }

    @Test (dependsOnMethods="loginTest")
    public void mainHeadersTextTest(){
        //9. Assert a text of the main headers
        WebElement mainTitle = driver.findElement(By.cssSelector("h3.main-title.text-center"));
        WebElement mainTxt = driver.findElement(By.cssSelector("p.main-txt.text-center"));

        Assert.assertTrue(mainTitle.isDisplayed());
        Assert.assertTrue(mainTxt.isDisplayed());

        Assert.assertEquals(mainTitle.getText(), "EPAM FRAMEWORK WISHES…");
        Assert.assertEquals(mainTxt.getText(), "LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT, SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET " +
                "DOLORE MAGNA ALIQUA. UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI UT ALIQUIP EX EA " +
                "COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN REPREHENDERIT IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT " +
                "NULLA PARIATUR.");
    }

    @Test (dependsOnMethods="loginTest")
    public void iframeTest(){
        //10. Assert that there is the iframe in the center of page
        List<WebElement> elements= driver.findElements(By.cssSelector("iframe"));
        Assert.assertFalse(elements.isEmpty());
    }

    @Test (dependsOnMethods="loginTest")
    public void logoTest(){
        //11. Switch to the iframe and check that there is Epam logo
        // in the left top conner of iframe
        driver.switchTo().frame("second_frame");

        List<WebElement> elements= driver.findElements(By.id("epam-logo"));
        Assert.assertFalse(elements.isEmpty());
        Assert.assertTrue(elements.get(0).isDisplayed());

        //12. Switch to original window back
        driver.switchTo().parentFrame();
    }

    @Test (dependsOnMethods="loginTest")
    public void subHeaderTextTest(){
        //13. Assert a text of the sub header
        WebElement element = driver.findElement(By.cssSelector("h3.text-center>a[target=_blank]"));
        Assert.assertTrue(element.isDisplayed());
        Assert.assertEquals(element.getText(), "JDI GITHUB");
    }

    @Test (dependsOnMethods="subHeaderTextTest")
    public void subHeaderUrlTest(){
        //14. Assert that JDI GITHUB is a link and has a proper URL
        WebElement element = driver.findElement(By.cssSelector("h3.text-center>a[target=_blank]"));
        Assert.assertEquals(element.getAttribute("href"),"https://github.com/epam/JDI");
    }

    @Test (dependsOnMethods="loginTest")
    public void leftSectionTest(){
        //15. Assert that there is Left Section
        List <WebElement> elements = driver.findElements(By.id("mCSB_1"));
        Assert.assertFalse(elements.isEmpty());
        Assert.assertTrue(elements.get(0).isDisplayed());
    }

    @Test (dependsOnMethods="loginTest")
    public void footerSectionTest(){
        //16. Assert that there is Footer
        List <WebElement> elements = driver.findElements(By.tagName("footer"));
        Assert.assertFalse(elements.isEmpty());
        Assert.assertTrue(elements.get(0).isDisplayed());
    }

    @AfterClass
    public void quitWebDriver() {
        driver.quit();
    }

}
