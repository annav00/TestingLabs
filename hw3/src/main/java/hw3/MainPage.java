package hw3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class MainPage {
    @FindBy(css = "li.dropdown.uui-profile-menu")
    public WebElement loginIcon;
    @FindBy(css = "#name")
    public WebElement username;
    @FindBy(css = "#password")
    public WebElement password;
    @FindBy(css = "#login-button")
    public WebElement loginButton;
    @FindBy(css = "#user-name")
    public WebElement user_name;
    @FindBy(css = "ul.uui-navigation.nav.navbar-nav.m-l8 a")
    public List<WebElement> headerSection;
    @FindBy(css = ".benefit-icon")
    public List<WebElement> images;
    @FindBy(css = ".benefit-icon+.benefit-txt")
    public List<WebElement> textUnderImages;
    @FindBy(css = "h3.main-title.text-center")
    public WebElement mainHeader;
    @FindBy(css = "p.main-txt.text-center")
    public WebElement mainText;
    @FindBy(css = "#second_frame")
    public WebElement second_frame;
    @FindBy(css = "img#epam-logo")
    public WebElement frameLogo;
    @FindBy(css = "h3.text-center>a")
    public WebElement subHeader;
    @FindBy(css = "h3.text-center>a[target=_blank]")
    public  WebElement jdiLink;
    @FindBy(css = "ul.sidebar-menu")
    public WebElement leftSection;
    @FindBy(css = "footer")
    public WebElement footer;
    @FindBy(className = "dropdown")
    public WebElement topService;
    @FindBy(css = "ul.dropdown-menu>li>a")
    public List<WebElement> topServiceContents;
    @FindBy(css = "li.menu-title[index='3']")
    public  WebElement leftService;
    @FindBy(css = "li.menu-title[index='3'] li[index]>a")
    public List<WebElement> leftServiceContents;
    @FindBy(css = "ul.sub>li[index='8']")
    public WebElement diffElePage;

    public MainPage(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
    }

    public void login(String username, String password){
        this.loginIcon.click();
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        this.loginButton.click();
    }

    public void assertText(WebElement we, String str){
        Assert.assertEquals(we.getText(), str);
    }

    public void assertTextOfElements(List<WebElement> list, String[] strArray) {
        int i = 0;
        for (WebElement webElement : list) {
            if (webElement.isEnabled() && webElement.isDisplayed()) {
                Assert.assertEquals(webElement.getText(), strArray[i++]);
            }
        }
    }

    public void assertSizeOfElements(List<WebElement> list, int number){
        Assert.assertEquals(list.size(), number);
    }

    public void assertElementsAreExisted(List<WebElement> list){
        for (WebElement webElement : list) {
            Assert.assertTrue(webElement.isDisplayed());
        }
    }

    public void assertElementIsDisplayed(WebElement webElement){
        Assert.assertTrue(webElement.isDisplayed());
    }

    public void assertElementsAreDisplayed(List<WebElement> list){
        for (WebElement webElement : list) {
            Assert.assertTrue(webElement.isDisplayed());
        }
    }

    public void switchToFrame(WebDriver webDriver, String str){
        webDriver.switchTo().frame(str);
    }

    public void switchToDefFrame(WebDriver webDriver){
        webDriver.switchTo().defaultContent();
    }

    public void assertHref(WebElement webElement, String link){
        Assert.assertEquals(webElement.getAttribute("href"), link);
    }

    public void assertLink(WebElement webElement){
        Assert.assertEquals(webElement.getAttribute("ui"), "link");
    }

    public void assertBrowserTitle(WebDriver webDriver, String str){
        Assert.assertEquals(webDriver.getTitle(), str);
    }

    public void closeBrowser(WebDriver webDriver){
        webDriver.close();
    }
}