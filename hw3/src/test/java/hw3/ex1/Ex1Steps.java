package hw3.ex1;

import hw3.MainPage;
import hw3.SecondPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Pattern;

public class Ex1Steps {
    WebDriver webDriver;
    Properties properties;
    SoftAssert softAssert;
    MainPage mainpage;
    SecondPage secondpage;
    String mainHeader;
    String[] mainText;
    Pattern patternWater, patternWind, patternSelen, patternYellow, patternWaterFalse, patternWindFalse;


    @Before
    public void initTest() throws IOException {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        softAssert = new SoftAssert();
        properties = new Properties();
        properties.load(new FileInputStream("Data.properties"));
        mainpage = new MainPage(webDriver);
        mainHeader = "EPAM FRAMEWORK WISHES…";
        mainText = new String[]{"To include good practices\n" + "and ideas from successful\n" + "EPAM project",
                "To be flexible and\n" + "customizable",
                "To be multiplatform",
                "Already have good base\n" + "(about 20 internal and\n" +
                        "some external projects),\n" + "wish to get more…"

        };
        patternWater = Pattern.compile("[0-9]+:[0-9]+:[0-9]+ Water: condition changed to true");
        patternWind = Pattern.compile("[0-9]+:[0-9]+:[0-9]+ Wind: condition changed to true");
        patternSelen = Pattern.compile("[0-9]+:[0-9]+:[0-9]+ metal: value changed to Selen");
        patternYellow = Pattern.compile("[0-9]+:[0-9]+:[0-9]+ Colors: value changed to Yellow");
        patternWaterFalse = Pattern.compile("[0-9]+:[0-9]+:[0-9]+ Water: condition changed to false");
        patternWindFalse = Pattern.compile("[0-9]+:[0-9]+:[0-9]+ Wind: condition changed to false");
    }

    @After
    public void afterTest() {
        webDriver.quit();
    }

    // Step 1: Open test site by URL
    @Given("I open the page")
    public void openPage() {
        webDriver.get(properties.getProperty("ex1.url"));
        mainpage.assertBrowserTitle(webDriver, properties.getProperty("ex1.pageTitle"));
        webDriver.manage().window().maximize();
    }

    // Step 3: Perform login
    @And("I log as {string} - {string}")
    public void login(String username, String password) {
        mainpage.login(username, password);
    }

    // Step 4: Assert User name in the left-top side of screen that user is loggined
    @Then("Username is {string}")
    public void assertUsername(String str) {
        mainpage.assertText(mainpage.user_name, str);
    }

    // Step 2, 5: Assert Browser title
    @Then("Browser title is {string}")
    public void assertBrowserTitle(String title) {
        Assert.assertEquals(webDriver.getTitle(), title);
    }

    // Step 6: Assert that there are 4 items on the header section.
    @Then("4 items of header section have proper texts")
    public void HeaderSectionContext() {
        mainpage.assertTextOfElements(mainpage.headerSection,
                properties.getProperty("ex1.heads").split(",  "));
    }

    // Step 7: Assert that there are 4 images
    @Then("Images are displayed")
    public void assertImages() {
        mainpage.assertSizeOfElements(mainpage.images, 4);
        mainpage.assertElementsAreDisplayed(mainpage.images);
    }

    // Step 8: Assert that there are 4 texts below images
    @Then("Texts blow images are displayed")
    public void assertTextsBelowImages() {
        mainpage.assertSizeOfElements(mainpage.textUnderImages, 4);
    }

    @And("They have proper text")
    public void assetContentOfTexts() {
        mainpage.assertTextOfElements(mainpage.textUnderImages, mainText);
    }

    // Step 9: Assert a text of the main headers
    @Then("First paragraph of main header has proper text")
    public void assertMainHeader1() {
        mainpage.assertText(mainpage.mainHeader, mainHeader);
    }

    @Then("Second paragraph of main header has proper text")
    public void assertMainHeader2() {
        mainpage.assertText(mainpage.mainText, properties.getProperty("ex1.mainText"));
    }

    // Step 10: Assert that there is the iframe in the center of mainpage
    @Then("The iframe exists")
    public void assertIframe() {
        mainpage.assertElementIsDisplayed(mainpage.second_frame);
    }

    // Step 11: Switch to the iframe and check EPAM logo
    @When("Switch to the iframe")
    public void sweitchToFrame() {
        mainpage.switchToFrame(webDriver, properties.getProperty("ex1.secondframe"));
    }

    @Then("Check EPAM logo")
    public void checkLogo() {
        mainpage.assertElementIsDisplayed(mainpage.frameLogo);
    }

    // Step 12: Switch to original window back
    @And("Switch to home page")
    public void backToHomePage() {
        mainpage.switchToDefFrame(webDriver);
    }

    // Step 13: Assert a text of the sub header
    @Then("Text has proper value")
    public void assertSubHeader() {
        mainpage.assertText(mainpage.subHeader, properties.getProperty("ex1.subHeader"));
    }

    // Step 14: Assert that JDI GITHUB is a link and has a proper URL
    @Then("JDI GITHUB is a Link")
    public void assertJDI() {
        mainpage.assertLink(mainpage.jdiLink);
    }

    @And("It has proper value")
    public void assertJDILink() {
        mainpage.assertHref(mainpage.jdiLink, properties.getProperty("ex1.jdiUrl"));
    }

    // Step 15: Assert that there is Left Section
    @Then("Left Section exists")
    public void checkLeftSection() {
        mainpage.assertElementIsDisplayed(mainpage.leftSection);
    }

    // Step 16: Assert that there is Footer
    @Then("Footer exists")
    public void checkFooter() {
        mainpage.assertElementIsDisplayed(mainpage.footer);
    }

    // Step 17: Close Browser
    @Then("Close browser")
    public void closeBrowser() {
        mainpage.closeBrowser(webDriver);
    }
}
