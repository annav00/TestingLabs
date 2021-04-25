package hw3.ex2;

import hw3.MainPage;
import hw3.SecondPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Pattern;

import static org.testng.Assert.*;


public class Ex2Steps {
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

    // Step 2: Assert Browser title
    @Then("Browser title is {string}")
    public void assertBrowserTitle(String title) {
        Assert.assertEquals(webDriver.getTitle(), title);
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

    // Step 5: Click on "Service" subcategory in the header and check that drop down contains options
    @Then("Drop down options of top Service have proper values")
    public void checkTopService() throws InterruptedException {
        mainpage.topService.click();
        String[] topServiceText = properties.getProperty("ex2.serviceOptionsTop").split(",  ");
        mainpage.assertTextOfElements(mainpage.topServiceContents, topServiceText);
    }

    // Step 6: Click on Service subcategory in the left section and check that drop down contains options
    @Then("Drop down options of left Service have proper values")
    public void checkLeftService() throws InterruptedException {
        mainpage.leftService.click();
        String[] topService = properties.getProperty("ex2.serviceOptionsSide").split(",  ");
        mainpage.assertTextOfElements(mainpage.leftServiceContents, topService);
    }

    // Step 7: Open through the header menu Service -> Different Elements Page
    @Given("Open \"Different Elements\" Page")
    public void openDiffElePage() {
        mainpage.leftSection.click();
        mainpage.diffElePage.click();
        secondpage = new SecondPage(webDriver);
    }

    // Step 8: Check interface on Different elements page, it contains all needed elements
    @Then("There are 4 checkboxes")
    public void assertCheckboxes() {
        Assert.assertEquals(secondpage.getCheckboxNum(), 4);
    }

    @Then("There are 4 radios")
    public void assertRadios() {
        Assert.assertEquals(secondpage.getRadioNum(), 4);
    }

    @Then("There are 1 dropdown")
    public void assertDropdown() {
        Assert.assertEquals(secondpage.getdropdownNum(), 1);
    }

    @Then("There are 2 buttons")
    public void assertButtons() {
        Assert.assertEquals(secondpage.getbuttonNum(), 2);
    }

    // Step 9: Assert that there is Right Section
    @Then("Right Section displayed")
    public void assertRightSection() {
        softAssert.assertTrue(secondpage.rightSection.isDisplayed());
        softAssert.assertAll();
    }

    // Step 10: Assert that there is Left Section
    @And("Left Section displayed")
    public void assertLeftSection() {
        softAssert.assertTrue(secondpage.rightSection.isDisplayed());
        softAssert.assertAll();
    }

    // Step 11: Select checkboxes
    @Then("Checkboxes {string} and {string} are selected")
    public void selectCheckbox(String str1, String str2) throws InterruptedException {
        secondpage.clickCheckbox(str1);
        secondpage.clickCheckbox(str2);
    }

    // Step 12: Assert that for each checkbox there is an individual log row and value is corresponded to the status of checkbox.
    @And("Status of checkboxes in Log row are displayed and corresponding")
    public void assertCheckboxLog() throws InterruptedException {
        secondpage.checkInfoPanel(patternWater, 1);
        secondpage.checkInfoPanel(patternWind, 0);
    }

    // Step 13: Select radio
    @Then("Radio {string} is selected")
    public void selectRadio(String str) {
        secondpage.clickRadio(str);
    }

    // Step 14: Assert that for radiobutton there is a log row and value is corresponded to the status of radiobutton.
    @And("Status of radios in Log row is displayed and corresponding")
    public void assertRadioLog() throws InterruptedException {
        secondpage.checkInfoPanel(patternSelen, 0);
    }

    // Step 15: Select in dropdown
    @Then("{string} in dropdown is selected")
    public void selectDropdown(String str) {
        secondpage.clickDropdown(str);
    }

    // Step 16: Assert that for dropdown there is a log row and value is corresponded to the selected value.
    @And("Status of dropdown in Log row is displayed and corresponding")
    public void assertDropdownLog() {
        secondpage.checkInfoPanel(patternYellow, 0);
    }

    // Step 17: Unselect and assert checkboxes
    @Then("Checkboxes {string} and {string} are unselected")
    public void unselectCheckbox(String str1, String str2) {
        secondpage.clickCheckbox(str1);
        secondpage.clickCheckbox(str2);
    }

    // Step 18: Assert that for each checkbox there is an individual log row and value is corresponded to the status of checkbox.
    @And("Status of checkboxes are displayed and corresponding")
    public void assertCheckboxLog2() throws InterruptedException {
        secondpage.checkInfoPanel(patternWaterFalse, 1);
        secondpage.checkInfoPanel(patternWindFalse, 0);
    }

    // Step 19: Close Browser
    @Then("Close browser")
    public void closeBrowser() {
        mainpage.closeBrowser(webDriver);
    }
}