package hw3.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.security.PublicKey;
import java.util.List;

public class InfoPanel {

    @FindBy(css = "ul.panel-body-list.logs li")
    List<WebElement> infoPanel;
    public void initElement(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public String getRecord(int number){
        Assert.assertFalse(infoPanel.isEmpty());
        return infoPanel.get(number).getText();
    }

    public String getText() {
        return null;
    }

    public boolean isDisplayed(String str) {
        return false;
    }
}